/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.bo;

import java.util.List;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.weston.coindesk.dao.CurrencyMappingDao;
import tw.weston.coindesk.dto.CurrencyMappingDto;
import tw.weston.coindesk.exc.DuplicateCurrencyMappinigException;
import tw.weston.coindesk.exc.InexistentCurrencyMappingException;
import tw.weston.coindesk.po.CurrencyMappingEntity;

/**
 * 幣別對應 業務邏輯實作類別
 * @author weston.tan
 */
@Service
public class CurrencyMappingBoImpl extends AbstractBo implements CurrencyMappingBo {
    
    @Autowired
    private CurrencyMappingDao currencyMappingDao;
    
    @Override
    public List<CurrencyMappingDto> findAll() {
        List<CurrencyMappingEntity> currencyMappingEntityList = currencyMappingDao.findAll();
        return modelMapper.map(currencyMappingEntityList, new TypeToken<List<CurrencyMappingDto>>() {}.getType());
    }
    
    @Override
    public CurrencyMappingDto findById(String eng) {
        CurrencyMappingEntity currencyMappingEntity = currencyMappingDao.findOneOrThrow(eng, InexistentCurrencyMappingException::new);
        return modelMapper.map(currencyMappingEntity, CurrencyMappingDto.class);
    }
    
    @Override
    public void insert(CurrencyMappingDto currencyMappingDto) {
        if (currencyMappingDao.existsById(currencyMappingDto.getEng())) {
            throw new DuplicateCurrencyMappinigException(currencyMappingDto.getEng());
        }
        CurrencyMappingEntity currencyMappingEntity = modelMapper.map(currencyMappingDto, CurrencyMappingEntity.class);
        currencyMappingDao.save(currencyMappingEntity);
    }
    
    @Override
    public CurrencyMappingDto update(CurrencyMappingDto currencyMappingDto) {
        if (!currencyMappingDao.existsById(currencyMappingDto.getEng())) {
            throw new InexistentCurrencyMappingException(currencyMappingDto.getEng());
        }
        CurrencyMappingEntity currencyMappingEntity = modelMapper.map(currencyMappingDto, CurrencyMappingEntity.class);
        currencyMappingEntity = currencyMappingDao.save(currencyMappingEntity);
        return modelMapper.map(currencyMappingEntity, CurrencyMappingDto.class);
    }
    
    @Override
    public void delete(String eng) {
        currencyMappingDao.deleteById(eng);
    }
    
}
