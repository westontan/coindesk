/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 幣別對應資料的 POJO
 * @author weston.tan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyMappingDto {
    
    @NotEmpty
    private String eng;
    @NotEmpty
    private String cht;
    
}
