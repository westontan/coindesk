/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.ctrl;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.weston.coindesk.bo.CurrencyMappingBo;
import tw.weston.coindesk.dto.CurrencyMappingDto;
import tw.weston.coindesk.vo.DataResponseVo;
import tw.weston.coindesk.vo.SuccessResponseVo;

/**
 * 幣別對應 接口類別
 * @author weston.tan
 */
@RestController
@RequestMapping("currencyMapping")
public class CurrencyMappingCtrl {
    
    @Autowired
    private CurrencyMappingBo currencyMappingBo;
    
    /**
     * 取得所有幣別對應資料的接口
     * @return 
     */
    @GetMapping
    public DataResponseVo<List<CurrencyMappingDto>> findAll() {
        List<CurrencyMappingDto> currencyMappingDtoList = currencyMappingBo.findAll();
        return new DataResponseVo(currencyMappingDtoList);
    }
    
    /**
     * 查詢指定的幣別對應資料的接口
     * @param eng 幣別英文名稱
     * @return 
     */
    @GetMapping("{eng}")
    public DataResponseVo<CurrencyMappingDto> findById(@PathVariable String eng) {
        CurrencyMappingDto currencyMappingDto = currencyMappingBo.findById(eng);
        return new DataResponseVo(currencyMappingDto);
    }
    
    /**
     * 新增幣別對應資料的接口
     * @param currencyMappingDto 幣別對應資料的 POJO
     * @return 
     */
    @PostMapping
    public SuccessResponseVo insert(@Valid @RequestBody CurrencyMappingDto currencyMappingDto) {
        currencyMappingBo.insert(currencyMappingDto);
        return new SuccessResponseVo();
    }
    
    /**
     * 更新幣別對應資料的接口
     * @param currencyMappingDto 幣別對應資料的 POJO
     * @return 
     */
    @PutMapping
    public DataResponseVo<CurrencyMappingDto> update(@Valid @RequestBody CurrencyMappingDto currencyMappingDto) {
        currencyMappingDto = currencyMappingBo.update(currencyMappingDto);
        return new DataResponseVo(currencyMappingDto);
    }
    
    /**
     * 刪除幣別對應資料的接口
     * @param eng 幣別英文名稱
     * @return 
     */
    @DeleteMapping("{eng}")
    public SuccessResponseVo delete(@PathVariable String eng) {
        currencyMappingBo.delete(eng);
        return new SuccessResponseVo();
    }
    
}
