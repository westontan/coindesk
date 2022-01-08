/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.exc;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 客製化例外
 * @author weston.tan
 */
@ToString
public class CustomizedException extends RuntimeException {

    @Getter
    private final String result;
    @Getter
    private final HttpStatus httpStatus;

    public CustomizedException(String result, String message, HttpStatus httpStatus) {
        super(message);
        this.result = result;
        this.httpStatus = httpStatus;
    }

}
