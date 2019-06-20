package com.hotel.admin.service.impl;

import com.hotel.admin.constants.Constant;
import com.hotel.admin.mapper.MrSummaryMapper;
import com.hotel.admin.mapper.SysRoleMapper;
import com.hotel.admin.mapper.SysUserRoleMapper;
import com.hotel.admin.mapper.WrSummaryMapper;
import com.hotel.admin.model.MrSummary;
import com.hotel.admin.model.SysRole;
import com.hotel.admin.model.SysUserRole;
import com.hotel.admin.model.WrSummary;
import com.hotel.admin.service.MrSummaryService;
import com.hotel.admin.service.WrSummaryService;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.core.context.PageContext;
import com.hotel.core.context.UserContext;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MrSummaryServiceImpl implements MrSummaryService {

    @Autowired
    private MrSummaryMapper mrSummaryMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Page findPage(MrSummary record) {
        PageContext.setPagination(false);
//        String username = UserContext.getCurrentUser().getName();
//        SysUser sysUser = sysUserService.findByName(username);
        ISysUser currentUser = UserContext.getCurrentUser();
        if (!"admin".equals(currentUser.getName())) {
            int i = 1;
            List<SysUserRole> userRoles = sysUserRoleMapper.findUserRoles(currentUser.getId());
            for (SysUserRole userRole : userRoles) {
                SysRole role = new SysRole();
                role.setId(userRole.getRoleId());

                role = sysRoleMapper.selectByPrimaryKey(role);
                if(Constant.MANAGER_ROLE.equals(role.getIsManager())){
                    i=2;
                    break;
                }
            }
            if (i == 1) {
                record.setReportId("R0001");
            }
        }

        PageContext.setPagination(true);
        List<MrSummary> li = mrSummaryMapper.findPage(record);
        return PageContext.getPage();
    }

    @Override
    public int save(MrSummary record) {
        return 0;
    }

    @Override
    public int delete(MrSummary record) {
        return 0;
    }

    @Override
    public int delete(List<MrSummary> records) {
        return 0;
    }

    @Override
    public MrSummary findById(Long id) {
        return null;
    }
}
