/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * HttpServletRequest的包裹器，主要是讓Http Body Content可以重複被讀取
 * @author weston.tan
 */
public class ContentReusableRequestWrapper extends HttpServletRequestWrapper {
    
    private byte[] bytes;
    
    public ContentReusableRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    @SneakyThrows
    public String getContent() {
        if (Objects.isNull(bytes)) {
            bytes = IOUtils.toByteArray(super.getInputStream());
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
    
    public String getEndpointUriWithQueryString() {
        return getServletPath()
                + Optional.ofNullable(getPathInfo()).orElse(StringUtils.EMPTY)
                + Optional.ofNullable(getQueryString()).map(queryString -> "?" + queryString).orElse(StringUtils.EMPTY);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return Objects.isNull(bytes) ? super.getInputStream() : new ServletInputStream() {

            private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
            
            @Override
            public int read(byte[] b) throws IOException {
                return byteArrayInputStream.read(b);
            }

            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener rl) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    
}
