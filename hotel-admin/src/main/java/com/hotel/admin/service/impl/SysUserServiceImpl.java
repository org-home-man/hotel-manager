package com.hotel.admin.service.impl;

import com.hotel.admin.dto.SysUserUp;
import com.hotel.admin.mapper.SysRoleMapper;
import com.hotel.admin.mapper.SysUserMapper;
import com.hotel.admin.mapper.SysUserRoleMapper;
import com.hotel.admin.model.SysMenu;
import com.hotel.admin.model.SysRole;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.model.SysUserRole;
import com.hotel.admin.qo.SysUserQuery;
import com.hotel.admin.service.SysMenuService;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.util.PasswordUtils;
import com.hotel.common.utils.StringUtils;
import com.hotel.core.annotation.SystemServiceLog;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import com.hotel.core.http.HttpStatus;
import com.hotel.core.page.ColumnFilter;
import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl  implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Transactional
	@Override
	@SystemServiceLog(description = "用户新增/编辑（业务层）")
	public int save(SysUser record) {
		if(record.getId() == null || record.getId() == 0) {
			// 新增用户
			sysUserMapper.insertSelective(record);
		} else {
			// 更新用户信息
			sysUserMapper.updateByPrimaryKeySelective(record);
			sysUserRoleMapper.deleteByUserId(record.getId());
		}

		for(SysUserRole sysUserRole:record.getUserRoles()) {
			sysUserRole.setUserId(record.getId());
			sysUserRoleMapper.insertSelective(sysUserRole);
		}
		return 1;
	}

	@Override
	@SystemServiceLog(description = "用户删除（业务层）")
	public int delete(SysUser record) {
		return sysUserMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysUser> records) {
		List<Long> ids = records.stream().map(r -> r.getId()).collect(Collectors.toList());
		//先删除用户对于角色关系
		sysUserRoleMapper.deleteByUserIds(ids);
		//删除用户
		sysUserMapper.deleteByIds(ids);
		return 1;
	}

	@Override
	public SysUser findById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public SysUser findByName(String name) {
		return sysUserMapper.findByName(name);
	}
	
	@Override
	public Page findPage(SysUserQuery qo) {
		List<SysUser> list = sysUserMapper.findPage(qo);
		Page page = PageContext.getPage();
		for (SysUser sysUser : list) {
			findUserRoles(sysUser);
		}
		page.setRows(list);
		return page;
	}

	/**
	 * 获取过滤字段的值
	 * @param filterName
	 * @return
	 */
	public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
		String value = null;
		ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
		if(columnFilter != null) {
			String v = columnFilter.getValue();
			if(!v.isEmpty()){
				value = v;
			}
		}
		return value;
	}

	/**
	 * 加载用户角色
	 * @param
	 */
	private void findUserRoles(SysUser sysUser) {
		List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
		sysUser.setUserRoles(userRoles);
		sysUser.setRoleNames(getRoleNames(userRoles));
	}

	private String getRoleNames(List<SysUserRole> userRoles) {
		StringBuilder sb = new StringBuilder();
		for(Iterator<SysUserRole> iter=userRoles.iterator(); iter.hasNext();) {
			SysUserRole userRole = iter.next();
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
			if(sysRole == null) {
				continue ;
			}
			sb.append(sysRole.getRemark());
			if(iter.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	public Set<String> findPermissions(String userName) {	
		Set<String> perms = new HashSet<>();
		List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
		for(SysMenu sysMenu:sysMenus) {
			if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
				perms.add(sysMenu.getPerms());
			}
		}
		return perms;
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		return sysUserRoleMapper.findUserRoles(userId);
	}

	@Override
	@Transactional
	public int updatePassword(SysUserUp record) {
		String username =  record.getUsername();
		String pass = record.getPass();
		String oldPass = record.getOldPass();
		String checkPass = record.getCheckPass();
		if (StringUtils.isBlank(username)) {
			throw new GlobalException("sysException",HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}
		if (StringUtils.isBlank(pass)) {
			throw new GlobalException("NotNullException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
		}
		if (StringUtils.isBlank(oldPass)) {

			throw new GlobalException("NotNullException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
		}
		if (StringUtils.isBlank(checkPass)) {

			throw new GlobalException("NotNullException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
		}
		if (!pass.equals(checkPass)) {
			throw new GlobalException("NotCorrectInfoException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
		} else {
			SysUser sysUser = sysUserMapper.findByName(username);
			if (sysUser == null) {
				throw new GlobalException("sysException",HttpStatus.SC_INTERNAL_SERVER_ERROR);
			}
			if ( !PasswordUtils.matches(sysUser.getSalt(), oldPass, sysUser.getPassword()) ) {
				throw new GlobalException("passErrException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
			}

			String salt = PasswordUtils.getSalt();
			String password = PasswordUtils.encode(pass,salt);
			SysUser user = new SysUser();
			user.setName(username);
			user.setPassword(password);
			user.setSalt(salt);
			int i = 0;
			try {
				 i = sysUserMapper.updatePassword(user);
			}catch (Exception e) {
				throw new GlobalException("oraException",HttpStatus.SC_FORBIDDEN);
			}
			if (i!=1) {
				throw new GlobalException("oraException",HttpStatus.SC_FORBIDDEN);
			}

		}

		return 1;
	}

	@Override
	@Transactional
	public int updateUserInfor(SysUserUp record) {
		String username =  record.getUsername();
		String pass = record.getPass();
		String oldPass = record.getOldPass();
		String checkPass = record.getCheckPass();
//		if (StringUtils.isBlank(username)) {
//			throw new GlobalException("sysException",HttpStatus.SC_INTERNAL_SERVER_ERROR);
//		}
//		if (StringUtils.isBlank(pass)) {
//			throw new GlobalException("NotNullException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
//		}
//		if (StringUtils.isBlank(oldPass)) {
//
//			throw new GlobalException("NotNullException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
//		}
//		if (StringUtils.isBlank(checkPass)) {
//
//			throw new GlobalException("NotNullException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
//		}
//		if (!pass.equals(checkPass)) {
//			throw new GlobalException("NotCorrectInfoException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
//		} else {
			SysUser sysUser = sysUserMapper.findByName(username);
			if (sysUser == null) {
				throw new GlobalException("sysException",HttpStatus.SC_INTERNAL_SERVER_ERROR);
			}
			if ( !PasswordUtils.matches(sysUser.getSalt(), oldPass, sysUser.getPassword()) ) {
				throw new GlobalException("passErrException",HttpStatus.SC_INSUFFICIENT_BUSINESERR);
			}

//			String salt = PasswordUtils.getSalt();
//			String password = PasswordUtils.encode(pass,salt);
			SysUser user = new SysUser();
			user.setName(username);
//			user.setPassword(password);
//			user.setSalt(salt);
			user.setRealName(record.getRealName());
			user.setAddress(record.getAddress());
			user.setBirthday(record.getBirthday());
			user.setEmail(record.getEmail());
			user.setMobile(record.getMobile());
			user.setNet(record.getNet());
			user.setPhone(record.getPhone());
			user.setRemark(record.getRemark());
			user.setSex(record.getSex());
			int i = 0;
			try {
				i = sysUserMapper.updateUserInfor(user);
			}catch (Exception e) {
				throw new GlobalException("oraException",HttpStatus.SC_FORBIDDEN);
			}
			if (i!=1) {
				throw new GlobalException("oraException",HttpStatus.SC_FORBIDDEN);
			}

//		}

		return 1;
	}

	@Override
	public List<SysUser> findLikeByName(String name) {
		return sysUserMapper.findLikeByName(name);
	}

	@Override
	public List<SysUser> findManager() {
		return sysUserMapper.selectManager();
	}
}
