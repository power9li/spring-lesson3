package com.power.spring.converter;

import com.google.gson.Gson;
import com.power.spring.lession3.model.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shenli on 2017/1/9.
 */
public class UserHttpMessageConverter implements HttpMessageConverter<User> {

    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(new MediaType[]{MediaType.ALL});
    }

    @Override
    public User read(Class<? extends User> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream is = httpInputMessage.getBody();
        String body = getInputContent(is);
        User user = new Gson().fromJson(body, User.class);
        return user;
    }

    @Override
    public void write(User user, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }

    private static String getInputContent(InputStream is){
        String body = null;
        try {
            byte[] bytes = new byte[1024 * 1024];

            int nRead = 1;
            int nTotalRead = 0;
            while (nRead > 0) {
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0)
                    nTotalRead = nTotalRead + nRead;
            }
            body = new String(bytes, 0, nTotalRead, "utf-8");
            System.out.println("body = " + body);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return body;
    }
}
