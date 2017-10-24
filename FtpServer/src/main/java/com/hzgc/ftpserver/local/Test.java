package com.hzgc.ftpserver.local;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;


public class Test {
    public static void main(String[] args) throws FtpException {
        FtpServerFactory ftpServerFactory= new FtpServerFactory();
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(2221);
        ftpServerFactory.addListener("default",listenerFactory.createListener());
        BaseUser user = new BaseUser();
        user.setName("admain");
        user.setPassword("123456");
        user.setHomeDirectory("E:/BaiduNetdiskDownload");
        ArrayList<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);
        ftpServerFactory.getUserManager().save(user);
        FtpServer server = ftpServerFactory.createServer();
        server.start();

    }

}
