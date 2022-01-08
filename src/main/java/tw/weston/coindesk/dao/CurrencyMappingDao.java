/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.dao;

import org.springframework.stereotype.Repository;
import tw.weston.coindesk.po.CurrencyMappingEntity;

/**
 * 幣別對應表 資料存取介面
 * @author weston.tan
 */
@Repository
public interface CurrencyMappingDao extends BasicDao<CurrencyMappingEntity, String> {
    
}
