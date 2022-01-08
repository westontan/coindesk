/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.bo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 業務邏輯類的共同父類別，用來注入一些常用的物件
 * @author weston.tan
 */
@Transactional
public abstract class AbstractBo {
    
    @Autowired
    protected ModelMapper modelMapper;
    
}
