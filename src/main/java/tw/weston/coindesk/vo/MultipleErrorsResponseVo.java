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
 * 接口回覆用的POJO，請求失敗(拋出例外)且包含多個錯誤時使用
 * @author weston.tan
 * @param <T>
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MultipleErrorsResponseVo<T> extends CommonResponseVo {
    
    @Getter
    T errors;
    
    public MultipleErrorsResponseVo(String result, T errors) {
        super(result);
        this.errors = errors;
    }
    
}
