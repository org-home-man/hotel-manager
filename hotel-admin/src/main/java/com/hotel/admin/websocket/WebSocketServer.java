package com.hotel.admin.websocket;

import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.constants.Constant;
import com.hotel.admin.dto.SocketMessage;
import com.hotel.admin.mapper.SysLogMapper;
import com.hotel.admin.mapper.SysUserMapper;
import com.hotel.admin.model.SysLog;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.redis.UserInfoCache;
import com.hotel.admin.service.SysLogService;
import com.hotel.admin.service.SysUserService;
import com.hotel.admin.util.WebSocketUtils;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.UserContext;
import com.hotel.core.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    public static UserInfoCache userInfoCache ;
    public static SysUserService sysUserService;
    public static SysLogService sysLogService;

    static Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
    public static ConcurrentHashMap<Long,Session> webSocketMap = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
//        ISysUser user = UserContext.getCurrentUser();
//        if(Utils.isEmpty(user)){
//            return;
//        }
//        this.session = session;
//        webSocketMap.put(user.getId(),this);     //加入set中
//        LOGGER.info("有新连接加入！用户名 [{}] 当前在线人数为 [{}] , map={}",user.getName(),webSocketMap.size(),webSocketMap);
//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            LOGGER.error("websocket IO异常");
//        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        //关闭连接记录退出日志
        saveLog(session,null,"退出系统");
        //踢出连接
        Collection<Session> values = webSocketMap.values();
        values.remove(session);
        LOGGER.info("有一连接关闭 当前在线人数为 [{}] , map={}",webSocketMap.size(),webSocketMap);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.info("来自客户端的消息:" + message);
        SocketMessage socketMessage = JSONObject.parseObject(message, SocketMessage.class);
        if(Utils.isNotEmpty(socketMessage)){
            if(Constant.SOCKET_LOGIN.equals(socketMessage.getType())){
                //登录添加连接
                SysUser user = userInfoCache.getUserByToken(socketMessage.getToken());
                if(Utils.isNotEmpty(user)){
                    //用户未被踢下线
                    webSocketMap.put(user.getId(),session);
                    LOGGER.info("有新连接加入！当前在线人数为 [{}] , map={}",webSocketMap.size(),webSocketMap);
                    //记录用户登录
                    saveLog(session,user,"登入系统");
                }
            }else if(Constant.SOCKET_HEAT_BEAT.equals(socketMessage.getType())){
                //心跳检查,响应数据
                //1.监测token是否超时
                String tk = socketMessage.getMessage();
                Boolean expired = userInfoCache.isTokenExpired(tk);
                SocketMessage heartMessage = new SocketMessage();
                if(expired){
                    heartMessage.setType(Constant.SOCKET_LOGIN_EXPIRED);
                    heartMessage.setMessage(Constant.LOGIN_EXPIRED_KEY);
                }else{
                    heartMessage.setType(Constant.SOCKET_HEAT_BEAT);
                    heartMessage.setMessage("heart report");
                }
                try {
                    session.getBasicRemote().sendText(JSONObject.toJSONString(heartMessage));
                } catch (IOException e) {
                    LOGGER.error("websocket send error");
                }
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     * */
    public static void sendMessage(String message){
        LOGGER.info("推送消息  推送内容:"+message);
        webSocketMap.forEach( (u,s)-> {
            try {
                //推送指定管理员用户
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                //
                LOGGER.info("websocket通讯异常 用户ID [{}]",u);
            }
        });
    }

    public static void saveLog(Session session, ISysUser sysUser,String message){
        if(Utils.isEmpty(sysUser)){

            //查询用户
            Long userId = null;
            Set<Map.Entry<Long, Session>> entries = webSocketMap.entrySet();
            for (Map.Entry<Long, Session> entry : entries) {
                Long id = entry.getKey();
                Session value = entry.getValue();
                if(session == value){
                    userId = id;
                    break;
                }
            }
            if(userId == null){
                return;
            }
            sysUser = sysUserService.findById(userId);
        }

        //关闭连接记录退出日志
        InetSocketAddress remoteAddress = WebSocketUtils.getRemoteAddress(session);
        SysLog sysLog = new SysLog();
        sysLog.setUserName(sysUser.getName());
        sysLog.setOperation(message);
        sysLog.setTime(System.currentTimeMillis());
        sysLog.setIp(remoteAddress.getHostString());
        try {
            UserContext.getCurrentUser();
        }catch (GlobalException e){
            UserContext.setUser(sysUser);
        }

        sysLogService.save(sysLog);
    }

}
