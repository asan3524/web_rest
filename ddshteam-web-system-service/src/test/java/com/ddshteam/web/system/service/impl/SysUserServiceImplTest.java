package com.ddshteam.web.system.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ddshteam.web.system.service.SysApplication;
import com.ddshteam.web.system.service.api.SysUserService;
import com.ddshteam.web.system.service.api.model.SysUser;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SysApplication.class)
public class SysUserServiceImplTest {

	@Autowired
	private SysUserService sysUserService;

	@Test
	public void test() {
		PageInfo<SysUser> users = sysUserService.getUserList(1, 10, "", "");
		List<SysUser> list = users.getList();
		System.out.println(list.size());
	}

}
