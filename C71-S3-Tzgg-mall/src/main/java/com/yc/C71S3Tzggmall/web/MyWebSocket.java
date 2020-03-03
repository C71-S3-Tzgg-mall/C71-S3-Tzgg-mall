package com.yc.C71S3Tzggmall.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@ServerEndpoint(value="/websocket/{id}")
@Component
public class MyWebSocket {
	
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount;
    //实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key为用户标识
    private static Map<String,MyWebSocket> connections = new ConcurrentHashMap<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String role;
    private String socketId;
    
 
    /**
     * 连接建立成功调用的方法
     *
     * @param session
     *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("id") String param, Session session) {
        this.session = session;
       // System.out.println(param);
        String[] arr = param.split(",");
        this.role = arr[0];             //用户标识
        this.socketId = arr[1];         //会话标识
        connections.put(role,this);     //添加到map中
        addOnlineCount();               // 在线数加
        //System.out.println("有新连接加入！新用户："+role+",当前在线人数为" + getOnlineCount());
    }
 
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        connections.remove(role);  // 从map中移除
        subOnlineCount();          // 在线数减
        //System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
 
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     * @param session
     *            可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //System.out.println("来自客户端的消息:" + message);
        JSONObject json=JSONObject.fromObject(message);
        String string = null;  //需要发送的信息
        String to = null;      //发送对象的用户标识
        if(json.has("message")){
            string = (String) json.get("message");
        }
        if(json.has("role")){
            to = (String) json.get("role");
        }
        send(string,role,to,socketId);
    }
 
    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    
	//发送给指定角色
    public void send(String msg,String from,String to,String socketId){
        try {
        	
            //to指定用户
            MyWebSocket con = connections.get(to);
            if(con!=null){
                if(socketId==con.socketId||con.socketId.equals(socketId)){
                    con.session.getBasicRemote().sendText(from+"："+msg);
                   
                }
                
            }
            //from具体用户
            MyWebSocket confrom = connections.get(from);
            if(confrom!=null){
                if(socketId==confrom.socketId||confrom.socketId.equals(socketId)){
                    confrom.session.getBasicRemote().sendText(from+"："+msg);
                }
 
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }
 
    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }

}
