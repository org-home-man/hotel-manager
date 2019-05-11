package com.hotel.admin.controller;

import com.hotel.admin.controller.base.BaseController;
import com.hotel.admin.dto.FileInfo;
import com.hotel.admin.service.IDocumentService;
import com.hotel.common.utils.Utils;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/document")
public class DocumentController extends BaseController {


    @Autowired
    IDocumentService documentService;


    @RequestMapping("/upload")
    public HttpResult fileUpload(MultipartFile[] files, String businessId) {
        return documentService.uploadFiles(files,businessId);
    }


    @RequestMapping("/download/{documentId}")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable Long documentId) {
        FileInfo fileInfo = documentService.queryFileInfo(documentId);
        try {
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode(fileInfo.getFileName(), "UTF-8");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.addHeader("Content-Length", "" + fileInfo.getFileSize());
            response.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(fileInfo.getContent());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            logger.error("回写文件失败", e);
        }
    }


    @RequestMapping("/preview/{documentId}")
    public void preview(HttpServletRequest request, HttpServletResponse response, @PathVariable Long documentId) {
        FileInfo fileInfo = documentService.queryFileInfo(documentId);
        if(Utils.isEmpty(fileInfo)){
            return;
        }
        try {
//            response.setCharacterEncoding("UTF-8");
//            response.reset();
            response.setContentType("image/png;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(fileInfo.getContent());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            logger.error("回写文件失败", e);
        }
    }

    @RequestMapping("deleteRealFiles")
    public HttpResult deleteRealFiles(String ids) {
        documentService.deleteRealFiles(ids);
        return HttpResult.ok();
    }

    @RequestMapping("/query/{documentId}")
    public HttpResult queryById(@PathVariable Long documentId) {
        return documentService.queryById(documentId);
    }

    @RequestMapping("/queryByRelId")
    public HttpResult queryByRelId(String relationId) {
        return documentService.queryByRelId(relationId);
    }


    private void writeData2Resp(HttpServletResponse response, String s) {
        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            logger.error("回写信息失败", e);
        }
    }


}
