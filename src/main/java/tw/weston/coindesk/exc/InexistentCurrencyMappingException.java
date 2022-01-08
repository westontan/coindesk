/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.exc;

import org.springframework.http.HttpStatus;

/**
 * 找不到指定的幣別對應時，拋出的例外
 * @author weston.tan
 */
public class InexistentCurrencyMappingException extends CustomizedException {
    
    public InexistentCurrencyMappingException(String eng) {
        super("INEXISTENT_CURRENCY_MAPPING", "Currency mapping " + eng + " cannot be found", HttpStatus.NOT_FOUND);
    }
    
}
