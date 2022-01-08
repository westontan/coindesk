/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.bo;

import java.util.List;
import tw.weston.coindesk.dto.CurrencyMappingDto;

/**
 * 幣別對應 業務邏輯介面
 * @author weston.tan
 */
public interface CurrencyMappingBo {
    
    List<CurrencyMappingDto> findAll();
    
    CurrencyMappingDto findById(String eng);
    
    void insert(CurrencyMappingDto currencyMappingDto);
    
    CurrencyMappingDto update(CurrencyMappingDto currencyMappingDto);
    
    void delete(String eng);
    
}
