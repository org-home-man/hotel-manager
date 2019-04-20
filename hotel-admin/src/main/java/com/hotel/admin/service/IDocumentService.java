package com.hotel.admin.service;

import com.hotel.admin.dto.FileInfo;
import com.hotel.admin.model.Document;
import com.hotel.core.model.ResultInfo;
import com.hotel.core.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDocumentService extends IService<Document> {
    List<String> uploadFiles(MultipartFile[] files);

    FileInfo queryFileInfo(Long documentId);

    void deleteRealFiles(String ids);


    ResultInfo queryById(Long documentId);
}
