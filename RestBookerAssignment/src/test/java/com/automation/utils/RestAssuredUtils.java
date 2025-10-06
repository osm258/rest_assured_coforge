package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    private static final ThreadLocal<RequestSpecification> reqSpecification =
            ThreadLocal.withInitial(RestAssured::given);

    private static final ThreadLocal<Response> response = new ThreadLocal<>();

    private static final ThreadLocal<String> endPoint = new ThreadLocal<>();

    public static void setEndPoint(String endPoint) {
        RestAssuredUtils.endPoint.set(endPoint);
    }

    public static void setHeader(String key, String value) {
        getReqSpecification().header(key, value);
    }

    public static void setBody(String filePath) {
        try {
            getReqSpecification().body(getDataFromJsonFile(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setBody(Object pojo) {
        try {
            getReqSpecification().body(pojo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void get() {
        RequestSpecification spec = getReqSpecification();
        spec.log().all();
        Response res = spec.get(endPoint.get());
        response.set(res);
        res.then().log().all();
        res.getBody().asString();
    }

    public static void post() {
        RequestSpecification spec = getReqSpecification();
        spec.log().all();
        Response res = spec.post(endPoint.get());
        response.set(res);
        res.then().log().all();
        res.getBody().asString();
    }

    public static void delete() {
        RequestSpecification spec = getReqSpecification();
        spec.log().all();
        Response res = spec.delete(endPoint.get());
        response.set(res);
        res.then().log().all();
        res.getBody().asString();
    }

    public static void put() {
        RequestSpecification spec = getReqSpecification();
        spec.log().all();
        Response res = spec.put(endPoint.get());
        response.set(res);
        res.then().log().all();
        res.getBody().asString();
    }

    public static int getStatusCode() {
        Response res = response.get();
        if (res == null) {
            throw new IllegalStateException("Response is not set for the current thread.");
        }
        return res.getStatusCode();
    }

    public static String getDataFromJsonFile(String fileName) throws FileNotFoundException {
        String jsonFolderPath = ConfigReader.getProperty("json.folder.path");
        Scanner sc = new Scanner(new FileInputStream(jsonFolderPath + fileName));
        String body = sc.useDelimiter("\\Z").next();
        return body;
    }

    public static String getResponseFieldValue(String jsonPath){
        Response res = response.get();
        if (res == null) {
            throw new IllegalStateException("Response is not set for the current thread.");
        }
        return res.jsonPath().getString(jsonPath);
    }

    public static void clear() {
        reqSpecification.set(RestAssured.given());
        response.remove();
        endPoint.remove();
    }

    private static RequestSpecification getReqSpecification() {
        return reqSpecification.get();
    }

    public static Response getResponse() {
        Response res = response.get();
        if (res == null) {
            throw new IllegalStateException("Response is not set for the current thread.");
        }
        return res;
    }
}
