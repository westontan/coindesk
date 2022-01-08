/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.bo;

import tw.weston.coindesk.dto.CoinDeskDto;

/**
 * 新的 CoinDesk API 業務邏輯介面
 * @author weston.tan
 */
public interface CoinDeskBo {
    
    CoinDeskDto query();
    
}
