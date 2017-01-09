package com.power.spring.lesson3.client;


import com.power.spring.lesson3.model.User;
import com.power.spring.lesson3.protocol.Request;
import com.power.spring.lesson3.protocol.Response;
import com.power.spring.lesson3.protocol.StatusCode;
import com.power.spring.lesson3.utils.HexUtils;
import com.power.spring.lesson3.utils.JSONUtils;
import com.power.spring.lesson3.utils.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenli on 2016/12/31.
 */
public class UserClient {

//    private static SimpleHttpServer server ;

    public static void main(String[] args) {

//        doQueryUser("zhangsan",false);
        doQueryAll();
        doDelUser(1);
        doQueryAll();
        doDelUser(3);
        doDelUser(4);
        doQueryAll();
        doDelUser(2);
        doQueryAll();

//        doCreateUser();
//        doDisableUser();

//        server.destory();

    }

    public static void doCreateUser(){

        //构造用户对象
        User user = new User();
        user.setEnabled(true);
        user.setUserName("Rose7");
        user.setPassword(HexUtils.byte2hex(MD5Utils.getMD5("1234567")));
        //序列化
        String reqJsonBody = JSONUtils.toJSON(user);
        //创建请求对象
        Request req = new Request("user/create",reqJsonBody);
        //调用服务
        Response resp = HttpClientWrapper.doRequest(req);
        System.out.println("resp.getStatus() = " + resp.getStatus());
        System.out.println("resp.getRespBody() = " + resp.getRespBody());


//        user.setUserName("Jack");
//        user.setPassword(HexUtils.byte2hex(MD5Utils.getMD5("1234567")));
//        reqJsonBody = JSONUtils.toJSON(user);
//        req = new Request("user/create", reqJsonBody);
//        resp = HttpClientWrapper.doRequest(req);
//        System.out.println("resp.getStatus() = " + resp.getStatus());
//        System.out.println("resp.getRespBody() = " + resp.getRespBody());


    }

    public static void doDisableUser(long userId){
        String reqJsonBody = JSONUtils.toJSON(userId);
        Request req = new Request("user/disable", reqJsonBody);
//        Response resp = server.handle(req);
        Response resp = HttpClientWrapper.doRequest(req);
        System.out.println("resp.getStatus() = " + resp.getStatus());

    }

    public static void doDelUser(long userId) {
        String reqJsonBody = JSONUtils.toJSON(userId);
        Request req = new Request("user/delete", reqJsonBody);
        Response resp = HttpClientWrapper.doRequest(req);
        System.out.println("resp.getStatus() = " + resp.getStatus());
        System.out.println("resp.getRespBody() = " + resp.getRespBody());

    }

    public static void doQueryUser(String userNamePrex, boolean onlyValidUser){
        Map<String, Object> map = new HashMap<>();
        map.put("userNamePrex", userNamePrex);
        map.put("onlyValidUser", onlyValidUser);
        String reqJsonBody = JSONUtils.toJSON(map);//JSONUtils.toJSON(userName, onlyValidUser);
        System.out.println("reqJsonBody = " + reqJsonBody);
        Request req = new Request("user/queryUsers", reqJsonBody);
//        Response resp = server.handle(req);
        Response resp = HttpClientWrapper.doRequest(req);
        System.out.println("resp.getStatus() = " + resp.getStatus());
        if (resp.getStatus().equals(StatusCode.NORMAL)) {
            String respBody = resp.getRespBody();
            System.out.println("respBody = " + respBody);
        }
    }

    public static void doQueryAll(){
        Map<String, Object> map = new HashMap<>();
        String reqJsonBody = JSONUtils.toJSON(map);//JSONUtils.toJSON(userName, onlyValidUser);
        System.out.println("reqJsonBody = " + reqJsonBody);
        Request req = new Request("user/queryAll", reqJsonBody);
//        Response resp = server.handle(req);
        Response resp = HttpClientWrapper.doRequest(req);
        System.out.println("resp.getStatus() = " + resp.getStatus());
        if (resp.getStatus().equals(StatusCode.NORMAL)) {
            String respBody = resp.getRespBody();
            System.out.println("respBody = " + respBody);
        }
    }


}
