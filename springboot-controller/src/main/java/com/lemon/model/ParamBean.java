package com.lemon.model;

/**
 * @author Shuai.Jing
 * @date 2020/11/14
 */
public class ParamBean {
    public String requestHead;
    public String requestBody;
    public String url;

    public String getRequestHead() {
        return requestHead;
    }

    public void setRequestHead(String requestHead) {
        this.requestHead = requestHead;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ParamBean{" +
                "requestHead='" + requestHead + '\'' +
                ", requestBody='" + requestBody + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
