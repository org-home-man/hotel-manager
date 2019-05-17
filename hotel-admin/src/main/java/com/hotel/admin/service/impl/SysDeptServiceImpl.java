package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.SysDeptMapper;
import com.hotel.admin.service.SysDeptService;
import com.hotel.admin.model.SysDept;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpStatus;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import com.hotel.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl extends AbstractService<SysDept> implements SysDeptService {

	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Override
	public int save(SysDept record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysDeptMapper.insertSelective(record);
		}
		return sysDeptMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SysDept> findTree() {
		List<SysDept> sysDepts = new ArrayList<>();
		List<SysDept> depts = sysDeptMapper.selectAll();
		for (SysDept dept : depts) {
			if (dept.getParentId() == null || dept.getParentId() == 0) {
				dept.setLevel(0);
				sysDepts.add(dept);
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}

	@Override
	public int deleteBatch(List<SysDept> records) {
		try {
			for(SysDept record:records) {
				delete(record);
			}
		}catch (Exception e) {
			throw new GlobalException("oraException", HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}

		return 1;

	}

	private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
		for (SysDept sysDept : sysDepts) {
			List<SysDept> children = new ArrayList<>();
			for (SysDept dept : depts) {
				if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
					dept.setParentName(dept.getName());
					dept.setLevel(sysDept.getLevel() + 1);
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}

}
