package com.codeon.codeserver.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lgh
 * @date 2020-11-20 18:04
 * @desc
 **/
@Component
@Slf4j
public class IOServer implements ApplicationRunner {

    public static void BIOServer(){

        try {
            ServerSocket serverSocket = new ServerSocket(1234);

            new Thread(()->{
                while (true){
                    try {
                        Socket accept = serverSocket.accept();

                        new Thread(()->{
                            try {
                                InputStream inputStream = accept.getInputStream();

                                int len;
                                byte[] data = new byte[1024];
                                while ((len = inputStream.read(data)) != -1) {
                                    log.info("msg info :{}",new String(data, 0, len));
                                }
                            } catch (IOException e) {
                                log.error(">> e:{}",e);
                            }

                        }).start();

                    } catch (IOException e) {
                        log.error(">> e:{}",e);
                    }
                }
            }).start();

        } catch (IOException e) {
            log.error(">> e:{}",e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BIOServer();
    }
}
