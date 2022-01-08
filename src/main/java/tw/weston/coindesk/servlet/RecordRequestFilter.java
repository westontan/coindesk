/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.servlet;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 用來記錄所有的請求
 * @author weston.tan
 */
@Log4j2
@Component
public class RecordRequestFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentReusableRequestWrapper requestWrapper = new ContentReusableRequestWrapper(request);
        String requestMethod = request.getMethod();
        String requestMediaType = request.getContentType();
        String requestUri = requestWrapper.getEndpointUriWithQueryString();
        String requestClientIP = request.getRemoteAddr();
        StringBuilder requestInfo = new StringBuilder();
        requestInfo.append(String.format("A new request %s %s was received from %s", requestMethod, requestUri, requestClientIP));
        if (requestMethod.equals(HttpMethod.POST.name()) && requestMediaType != null && !requestMediaType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
            String requestBody = requestWrapper.getContent();
            requestInfo.append(", and the content of the body is ");
            if (requestBody.length() > 0) {
                requestInfo.append(String.format("as follows: %s", requestBody.replaceAll("[\t\r\n]", "")));
            } else {
                requestInfo.append("empty");
            }
        }
        requestInfo.append(".");
        log.info(requestInfo);
        filterChain.doFilter(requestWrapper, response);
    }
    
}
