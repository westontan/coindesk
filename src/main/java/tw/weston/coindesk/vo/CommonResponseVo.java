/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

/**
 * 接口回覆用的POJO，需指定result屬性
 * @author weston.tan
 */
@Data
public class CommonResponseVo {
    
    @NonNull
    @Setter(AccessLevel.NONE)
    protected String result;
    
}
