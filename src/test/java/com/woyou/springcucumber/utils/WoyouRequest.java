package com.woyou.springcucumber.utils;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import org.springframework.stereotype.Component;

import java.util.Map;

public class WoyouRequest {

    public static Response DoPost(String url, Map<String, Object> headers, String jsonBody){
         return SerenityRest
                .given()
                .headers(headers)
                .body(jsonBody)
                .post(url);
    }

    public static Response DoPost(String url, Map<String, Object> headers){
        return SerenityRest
                .given()
                .headers(headers)
                .post(url);
    }

    public static Response DoPost(String url){
        return SerenityRest
                .given()
                .post(url);
    }

    public static Response DoGet(String url){
        return SerenityRest
                .given()
                .get(url);
    }

}
