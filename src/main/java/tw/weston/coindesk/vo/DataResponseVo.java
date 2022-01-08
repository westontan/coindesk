/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 接口回覆用的POJO，請求成功且需要回傳資料時使用
 * @author weston.tan
 * @param <T>
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DataResponseVo<T> extends SuccessResponseVo {
    
    @Getter @Setter
    private T data;
    
    public DataResponseVo(T data) {
        super();
        this.data = data;
    }
    
}
