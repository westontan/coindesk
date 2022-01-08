/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.po;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 幣別中文對應表的持久化類別
 * @author weston.tan
 */
@Data
@Entity
@Table(name = "CURRENCY_MAPPING")
public class CurrencyMappingEntity implements Serializable {
    
    @Id
    @Basic(optional = false)
    @Column(name = "eng")
    private String eng;
    @Column(name = "cht")
    private String cht;
    
}
