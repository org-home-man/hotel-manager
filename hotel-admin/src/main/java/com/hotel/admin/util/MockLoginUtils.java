package com.hotel.admin.util;

import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.model.SysUser;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2018-07-30
 */
public class MockLoginUtils {
    @Autowired
    private static Logger log = LoggerFactory.getLogger(MockLoginUtils.class);


    public static void mockLogin() {
        try {
            ISysUser currentUser = UserContext.getCurrentUser();
            if(Utils.isNotEmpty(currentUser)){
                return;
            }
        } catch (RuntimeException e) {
            log.info("admin模拟登录!");
        }

        String userJson = "{\"id\":1,\"password\":\"e0e0a42cfb2d64856960193c1fa0785d\",\"status\":\"1\",\"name\":\"admin\"}";
        SysUser adminUser = JSONObject.parseObject(userJson, SysUser.class);
        UserContext.setUser(adminUser);
    }

    public static void logout(){
        UserContext.setUser(null);
    }

}
