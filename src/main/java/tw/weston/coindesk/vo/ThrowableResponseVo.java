/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 接口回覆用的POJO，請求失敗(拋出例外)時使用
 * @author weston.tan
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ThrowableResponseVo extends CommonResponseVo {
    
    @Getter
    private String errorMessage;

    public ThrowableResponseVo(String result, String errorMessage) {
        super(result);
        this.errorMessage = errorMessage;
    }
    
    public ThrowableResponseVo(String result, Throwable ex) {
        this(result, ex.getMessage());
    }
    
    public ThrowableResponseVo(String errorMessage) {
        this("UNEXPECTED_ERROR", errorMessage);
    }
    
    public ThrowableResponseVo(Throwable ex) {
        this(ex.getMessage());
    }
    
}
