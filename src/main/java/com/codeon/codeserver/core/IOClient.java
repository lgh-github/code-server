package com.codeon.codeserver.core;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.Socket;

/**
 * @author lgh
 * @date 2020-11-20 18:04
 * @desc
 **/
@RestController
@Slf4j
public class IOClient {

    @PostMapping("bio")
    public void BIOClient(String msg){
        new Thread(()->{
            try {
                Socket socket = new Socket("127.0.0.1",1234);
                socket.getOutputStream().write(msg.getBytes());
            } catch (IOException e) {
                log.error(">> e:{}",e);
            }
        }).start();
    }
}
