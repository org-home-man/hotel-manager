package com.hotel.admin.mapper;

import com.hotel.admin.model.Document;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentMapper extends AbstractMapper<Document> {
    List<Document> queryByReleationId(@Param(value = "releationId") Long releationId);

	/**
	 * @param
	 */
	void deleteByDoc(@Param(value = "docId") Long docId);

    List<String> selectByRelationId(@Param("relationId") String relationId);
}