package com.power.spring.lesson3.client;

import com.sun.net.httpserver.Headers;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by shenli on 2017/1/2.
 */
public class HttpClientWrapper {

    private static HttpClient httpClient = new DefaultHttpClient();
    private static final String URL = "http://localhost:8080/spring-lesson3/";

    public static Response doRequest(Request request) {
        Response resp = null;

        String command = request.getCommand();
        HttpPost post = new HttpPost(URL + command);
        System.out.println("post.getURI() = " + post.getURI());
        try {
            Headers headers = request.getHeaders();
//            headers.add("Content-Type","application/json");
//            headers.add("Content-Length",String.valueOf(request.getReqJsonBody().length()));
            if (headers != null) {
                for (Map.Entry<String, List<String>> entries : headers.entrySet()) {
                    String key = entries.getKey();
                    List<String> value = entries.getValue();
                    post.addHeader(key, Arrays.toString(value.toArray()));
                }
            }
            System.out.println("request.getReqJsonBody() = " + request.getReqJsonBody());
            post.setEntity(new StringEntity(request.getReqJsonBody(), Charset.forName("UTF8")));
            post.addHeader("Content-Type","application/json");
//            post.addHeader("Content-Length",String.valueOf(request.getReqJsonBody().length()));
            HttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("HTTP statusCode = " + statusCode);
            String body = EntityUtils.toString(response.getEntity());

            resp = new Response();
            resp.setStatus(StatusCode.codeOf(statusCode));
            resp.setRespBody(body);

        } catch (IOException e) {
            e.printStackTrace();
            resp = new Response();
            resp.setStatus(StatusCode.SERVER_INTERNAL_ERROR);
        }
        return resp;
    }
}
