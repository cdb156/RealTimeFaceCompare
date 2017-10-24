package com.hzgc.ftpserver.local;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Test2 {
    public static void main(String[] args) throws FtpException {
        FtpServerFactory ftpServerFactory = new FtpServerFactory();
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(1321);
        ftpServerFactory.addListener("default", listenerFactory.createListener());
        BaseUser user = new BaseUser();
        user.setName("admain");
        user.setPassword("123456");
        user.setHomeDirectory("E:/BaiduNetdiskDownload");
        user = new BaseUser();
        user.setName("admain");
        user.setPassword("123456");
        user.setHomeDirectory("E:/BaiduNetdiskDownload");
        ArrayList<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);
        ftpServerFactory.getUserManager().save(user);
        HashMap<String, Ftplet> ftpletHashMap = new HashMap<>();
        MyDefaultPortlet myDefaultPortlet = null;
        ftpletHashMap.put("miaFtplet", myDefaultPortlet);
        myDefaultPortlet = new MyDefaultPortlet();
        ftpServerFactory.setFtplets(ftpletHashMap);
        Map<String, Ftplet> ftplets = ftpServerFactory.getFtplets();
        System.out.println(ftplets.size());
        System.out.println("Thread #"+Thread.currentThread().getId());
        System.out.println(ftplets.toString());
        FtpServer server = ftpServerFactory.createServer();
        try{
            server.start();//Your FTP server starts listening for incoming FTP-connections, using the configuration options previously set
        }catch (FtpException ex){
            //Deal with exception as you need
        }
    }

    private static class MyDefaultPortlet extends DefaultFtplet{

            public void init(FtpletContext ftpletContext) throws FtpException {
                System.out.println("*********init*********");
                System.out.println("Thread #" + Thread.currentThread().getId());
            }

        public void destroy() {
            System.out.println("*********destroy*********");
            System.out.println("Thread #" + Thread.currentThread().getId());
        }

        public FtpletResult beforeCommand(FtpSession session, FtpRequest request) throws FtpException, IOException {
            System.out.println("*********beforeCommand*********");
            System.out.println(session.getUserArgument() + ",session:" + session.toString() + ",getArgument:" + request.getArgument() + ", getCommand:" + request.getCommand() + ",getRequestLine:" + request.getRequestLine());
            System.out.println("Thread #" + Thread.currentThread().getId());

            return FtpletResult.DEFAULT;//...or return accordingly
        }

        public FtpletResult afterCommand(FtpSession session, FtpRequest request, FtpReply reply) throws FtpException, IOException {
            System.out.println("*********afterCommand*********");
            System.out.println("afterCommand " + session.getUserArgument() + ",session:" + session.toString() + ",getArgument:" + request.getArgument() + ", getCommand:" + request.getCommand() + ",getRequestLine:" + request.getRequestLine() + ",getMessage:" + reply.getMessage() + ",reply:" + reply.toString());
            System.out.println("Thread #" + Thread.currentThread().getId());

            return FtpletResult.DEFAULT;//...or return accordingly

        }


    }

}


