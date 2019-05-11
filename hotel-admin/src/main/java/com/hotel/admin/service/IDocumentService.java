package com.hotel.admin.service;

import com.hotel.admin.dto.FileInfo;
import com.hotel.admin.model.Document;
import com.hotel.core.http.HttpResult;
import com.hotel.core.service.IService;
import org.springframework.web.multipart.MultipartFile;

public interface IDocumentService extends IService<Document> {
    HttpResult uploadFiles(MultipartFile[] files, String businessId);

    FileInfo queryFileInfo(Long documentId);

    void deleteRealFiles(String ids);


    HttpResult queryById(Long documentId);

    HttpResult queryByRelId(String relationId);
}
