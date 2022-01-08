/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.weston.coindesk.bo.CoinDeskBo;
import tw.weston.coindesk.dto.CoinDeskDto;
import tw.weston.coindesk.vo.DataResponseVo;

/**
 * 新的 CoinDesk API 接口類別
 * @author weston.tan
 */
@RestController
@RequestMapping("coindesk")
public class CoinDeskCtrl {
    
    @Autowired
    private CoinDeskBo coinDeskBo;
    
    @GetMapping
    public DataResponseVo<CoinDeskDto> query() {
        CoinDeskDto coinDeskDto = coinDeskBo.query();
        return new DataResponseVo(coinDeskDto);
    }
    
}
