package com.cyrstal.wx.web.servlet;

import com.crystal.wx.common.CheckSignUtil;
import com.crystal.wx.util.MessageHandlerUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by xieql2 on 2016/11/7.
 */
public class WxServlet extends HttpServlet {

    private final String TOKEN = "crystal";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("开始校验签名");
        String signature=req.getParameter("signature");
        String timestamp=req.getParameter("timestamp");
        String nonce=req.getParameter("nonce");
        String echostr=req.getParameter("echostr");

       boolean isSuccess= CheckSignUtil.Check(TOKEN, signature, timestamp, nonce, echostr);
        if(isSuccess) {
            System.out.println("签名校验通过");
            resp.getWriter().write(echostr);
        }else {
            System.out.println("签名校验失败");
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("请求进入");
        String result = "";

        try {
            Map<String,String> map= MessageHandlerUtil.parseXml(req);
            System.out.println("开始构造消息");
            result=MessageHandlerUtil.buildResponseMessage(map);
            if(result.equals("")){
                result="未正确响应";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生异常："+ e.getMessage());
        }

        resp.getWriter().write(result);
    }



}
