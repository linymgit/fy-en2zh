package com.lym.service;

import com.alibaba.fastjson.JSON;
import com.lym.bean.Web;
import com.lym.bean.YoudaoFanyiRootBean;
import com.lym.util.http.HttpUtils;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TranslateService {

    public static String translate(String input){
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        List<List<String>> post = HttpUtils.post(String.format("https://aidemo.youdao.com/trans?q=%s&from=auto&to=auto",input).replaceAll(" ", "%20"), nvps, in -> {
            YoudaoFanyiRootBean o = JSON.parseObject(in.getContent(), YoudaoFanyiRootBean.class);
            return o.getWeb().stream().map(Web::getValue).collect(Collectors.toList());
        });

        return post.get(0).toString();
    }

    public static void main(String[] args) {
        System.err.println(new TranslateService().translate("i love you"));
    }

}
