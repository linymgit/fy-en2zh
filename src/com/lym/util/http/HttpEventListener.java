package com.lym.util.http;

import org.apache.http.HttpEntity;

import java.io.IOException;

@FunctionalInterface
public interface HttpEventListener<V> {

    V success(HttpEntity in) throws IOException;

}
