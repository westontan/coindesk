/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;

/**
 * 存放資料轉換過的 CoinDesk API 資料的 POJO
 * @author weston.tan
 */
@Data
public class CoinDeskDto {
    
    /**
     * 更新時間
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime time;
    /**
     * 幣別相關資訊
     */
    private Map<String, Currency> currencies;
    
    @Data
    public static class Currency {
    
        /**
         * 幣別中文名稱
         */
        private String cht;
        /**
         * 匯率
         */
        private float rate;
        
    }
    
}
