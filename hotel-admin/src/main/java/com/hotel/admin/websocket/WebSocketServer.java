package com.hotel.admin.websocket;

import com.alibaba.fastjson.JSONObject;
import com.hotel.admin.constants.Constant;
import com.hotel.admin.dto.SocketMessage;
import com.hotel.admin.mapper.SysLogMapper;
import com.hotel.admin.mapper.SysUserMapper;
import com.hotel.admin.model.SysLog;
import com.hotel.admin.model.SysSocketMessage;
import com.hotel.admin.model.SysUser;
import com.hotel.admin.redis.UserInfoCache;
import com.hotel.admin.service.ISysWebSocketMessageService;
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
    public static ISysWebSocketMessageService socketMessageService;

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
                session.getAsyncRemote().sendText(JSONObject.toJSONString(heartMessage));
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
                //推送指定管理员用户
                s.getAsyncRemote().sendText(message);
        });
    }

    /**
     * 管理员消息群发
     * @param message 消息
     * @param type 消息类型 1-订单 2-日报 3-周报 4-月报 参照 CONSTANT.ORDER_MES
     */
    public static void sendMessageToManager(String message,String type){
        LOGGER.info("推送消息至管理员  推送内容:"+message);
        List<SysUser> users = sysUserService.findManager();
        if(Utils.isEmpty(users)){
            return;
        }
        users.forEach( user ->{
            Long id = user.getId();
            validateSessionAndSend(id,message,type);
        });
    }

    /**
     * 消息群发给指定用户
     * @param userIds 用户ID集合
     * @param message 消息
     */
    public static void sendMessageToUsers(List<Long> userIds,String message,String type){
        LOGGER.info("推送消息至用户  推送内容:"+message);
        if(Utils.isEmpty(userIds)){
            return;
        }
        userIds.forEach( id ->{
            validateSessionAndSend(id,message,type);
        });
    }

    private static void validateSessionAndSend(Long id,String message,String type){
        Session session = webSocketMap.get(id);
        if(null == session){
            //未连接
            SysSocketMessage socketMessage = new SysSocketMessage();
            socketMessage.setStatus("1");
            socketMessage.setUserId(id);
            socketMessage.setMessageType(type);
            socketMessage.setMessage(message);
            socketMessageService.save(socketMessage);
        }else{
            //推送消息
            session.getAsyncRemote().sendText(message);
        }
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
