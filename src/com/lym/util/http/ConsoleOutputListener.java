package com.lym.util.http;

import org.apache.http.HttpEntity;

import java.io.IOException;
import java.io.InputStream;

public class ConsoleOutputListener implements HttpEventListener {
    @Override
    public String success(HttpEntity in) throws IOException {
        InputStream content = in.getContent();
        byte[] bytes = new byte[1024];
        int read = content.read(bytes);
        while (read>0){
            System.out.println(new String(bytes));
            read = content.read(bytes);
        }
        return "";
    }
}
