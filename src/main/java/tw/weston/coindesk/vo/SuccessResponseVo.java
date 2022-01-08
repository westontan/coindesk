/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.vo;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 接口回覆用的POJO，請求成功且不須回傳資料時使用
 * @author weston.tan
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SuccessResponseVo extends CommonResponseVo {
    
    public SuccessResponseVo() {
        super("SUCCESS");
    }
    
}
