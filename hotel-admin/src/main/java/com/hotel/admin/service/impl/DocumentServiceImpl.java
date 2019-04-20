package com.hotel.admin.service.impl;

import com.hotel.admin.constants.Constant;
import com.hotel.admin.context.InsertContext;
import com.hotel.admin.dto.FileInfo;
import com.hotel.admin.mapper.DocumentMapper;
import com.hotel.admin.model.Document;
import com.hotel.admin.service.IDocumentService;
import com.hotel.admin.util.IdUtil;
import com.hotel.core.utils.SimpleUtils;
import com.hotel.common.utils.SystemUtil;
import com.hotel.common.utils.Utils;
import com.hotel.core.model.ResultInfo;
import com.hotel.core.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

@Service
@PropertySource(value = "classpath:application-sys.properties", ignoreResourceNotFound = true)
public class DocumentServiceImpl extends AbstractService<Document> implements IDocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);

    @Autowired
    DocumentMapper documentMapper;

    @Value("${linux.store.path}")
    private String linuxFileStorePath;

    @Value("${windows.store.path}")
    private String windowFileStorePath;


    private String fileStoreBasePath;


    @PostConstruct
    public void initFileStorePath() {
        if (SystemUtil.isLinux()) {
            fileStoreBasePath = linuxFileStorePath;
        } else if (SystemUtil.isWin()) {
            fileStoreBasePath = windowFileStorePath;
        } else {
            fileStoreBasePath = "/";
        }
    }


    @Override
    public List<String> uploadFiles(MultipartFile[] files) {
        if (files == null) {
            return Collections.emptyList();
        }
        List<String> documentIds = new ArrayList<>();
        for (MultipartFile file : files) {
            Document document = new Document();
            writeFile2Dist(file, document);
            InsertContext.setInsertion(false);
            document.setId(IdUtil.nextId());
            document.setStatus(Constant.BOOL_NO);
            //开始保存文件
            documentMapper.insertSelective(document);
            InsertContext.setInsertion(true);
            documentIds.add(document.getId() + "");
        }
        return documentIds;
    }

    @Override
    public FileInfo queryFileInfo(Long documentId) {
        SimpleUtils.assertNotEmpty(documentId, "文档id不能为空");
        Document document = documentMapper.selectByPrimaryKey(documentId);
        SimpleUtils.assertNotEmpty(document, "该文档可能已经被删除，请联系管理员");
        SimpleUtils.trueAndThrows(Constant.BOOL_YES.equals(document.getStatus()), "该文档可能已经被删除，请联系管理员");
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(document.getName());
        fileInfo.setFileSize(document.getSize());
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(document.getStorePath()));
            byteArrayOutputStream = new ByteArrayOutputStream();
            SimpleUtils.inWrite2Out(fileInputStream, byteArrayOutputStream, false);
            fileInfo.setContent(byteArrayOutputStream.toByteArray());
            return fileInfo;
        } catch (FileNotFoundException e) {
            LOGGER.error("文件读取失败", e);
            //throw new BusinessException("读取文件失败");
            return null;
        } finally {
            SimpleUtils.closeStream(byteArrayOutputStream, fileInputStream);
        }
    }

    @Override
    public void deleteRealFiles(String ids) {
        if (Utils.isEmpty(ids)) {
            return;
        }
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            //删除数据
            documentMapper.deleteByPrimaryKey(Long.parseLong(id));
            //删除对应的文件
            deleteDistFile(Long.valueOf(id));
        }
    }


    private void deleteDistFile(Long id) {
        Document document = documentMapper.selectByPrimaryKey(id);
        if (document == null) {
            return;
        }
        //得到文件的真实路劲
        String storePath = document.getStorePath();
        //删除文件
        SimpleUtils.tryDeleteFile(storePath);
    }

    private void writeFile2Dist(MultipartFile file, Document document) {
        //生成随机文件夹的序号
        int subDirNum = generateLessThan100Int();
        String subDirPath = SimpleUtils.pathJoin(fileStoreBasePath, subDirNum + "/");
        File subDir = new File(subDirPath);
        if (!subDir.exists()) {
            SimpleUtils.trueAndThrows(!subDir.mkdirs(), "子目录创建失败:"+subDir);
        }
        String fileName = file.getOriginalFilename();
        document.setName(fileName);
        //文件大小
        document.setSize(file.getSize());
        //上传用户
        //fileUpload.setUpdateUser(UserContext.getCurrentUser().getId());
        String storePath = SimpleUtils.pathJoin(subDirPath, generate32BitUUID() + fileName);
        document.setStorePath(storePath);
        File newFile = new File(storePath);
        SimpleUtils.trueAndThrows(newFile.exists(), "文件写入磁盘异常");
        try {
            SimpleUtils.trueAndThrows(!newFile.createNewFile(), "新文件创建失败");
            FileOutputStream outputStream = new FileOutputStream(newFile);
            SimpleUtils.inWrite2Out(file.getInputStream(), outputStream, true);
        } catch (IOException e) {
            SimpleUtils.trueAndThrows(true, "系统异常");
        }
    }

    /**
     * 随机产生1~100的数
     *
     * @return 随机产生的数
     */
    private int generateLessThan100Int() {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(100) + 1;
    }

    public ResultInfo queryById(Long documentId) {
        Document document = documentMapper.selectByPrimaryKey(documentId);
        return SimpleUtils.success(document);
    }


    private String generate32BitUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
