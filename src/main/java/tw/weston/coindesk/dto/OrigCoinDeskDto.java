/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;

/**
 * 存放原生 CoinDesk API 資料的 POJO
 * @author weston.tan
 */
@Data
public class OrigCoinDeskDto {
    
    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, Bpi> bpi;
    
    @Data
    public static class Time {
        
        private String updated;
        private String updatedISO;
        private String updateduk;
    
    }
    
    @Data
    public static class Bpi {
        
        private String code;
        private String symbol;
        private String rate;
        private String description;
        @JsonProperty("rate_float")
        private float rateFloat;
    
    }
    
}
