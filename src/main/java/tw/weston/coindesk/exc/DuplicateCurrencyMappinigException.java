/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.exc;

import org.springframework.http.HttpStatus;

/**
 * 找到重複的幣別對應時，拋出的例外
 * @author weston.tan
 */
public class DuplicateCurrencyMappinigException extends CustomizedException {

    public DuplicateCurrencyMappinigException(String eng) {
        super("DUPLICATE_CURRENCY_MAPPING", "Currency mapping " + eng + " has already existed", HttpStatus.BAD_REQUEST);
    }

}
