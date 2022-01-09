/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.bo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tw.weston.coindesk.dao.CurrencyMappingDao;
import tw.weston.coindesk.dto.CoinDeskDto;
import tw.weston.coindesk.dto.OrigCoinDeskDto;
import tw.weston.coindesk.po.CurrencyMappingEntity;

/**
 * 新的 CoinDesk API 業務邏輯實作類別
 * @author weston.tan
 */
@Service
public class CoinDeskBoImpl extends AbstractBo implements CoinDeskBo {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CurrencyMappingDao currencyMappingDao;
    @Value("${coindesk.url}")
    private String coindeskUrl;

    @PostConstruct
    private void init() {
        //取得幣別對應的中文名稱
        Map<String, String> currencyMap = currencyMappingDao.findAll()
                .stream()
                .collect(Collectors.toMap(CurrencyMappingEntity::getEng, CurrencyMappingEntity::getCht));
        //幣別資料轉換規則設定
        modelMapper.createTypeMap(OrigCoinDeskDto.Bpi.class, CoinDeskDto.Currency.class)
                .addMappings(mapper -> {
                    mapper.using(ctx -> currencyMap.get(ctx.getSource().toString()))
                            .map(OrigCoinDeskDto.Bpi::getCode, CoinDeskDto.Currency::setCht);
                    mapper.map(OrigCoinDeskDto.Bpi::getRateFloat, CoinDeskDto.Currency::setRate);
                });
        //外層資料轉換規則設定
        modelMapper.createTypeMap(OrigCoinDeskDto.class, CoinDeskDto.class)
                .addMappings(mapper -> {
                    mapper.using(ctx -> OffsetDateTime.parse(ctx.getSource().toString()).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime())
                            .map(source -> source.getTime().getUpdatedISO(), CoinDeskDto::setTime);
                    mapper.map(OrigCoinDeskDto::getBpi, CoinDeskDto::setCurrencies);
                });
    }

    @Override
    @SneakyThrows
    public CoinDeskDto query() {
        RestTemplate restTemplate = new RestTemplate();
        //因為Content-Type是application/javascript，無法透過RestTemplate直接幫你轉成POJO，所以先用字串接
        String content = restTemplate.getForObject(coindeskUrl, String.class);
        //透過ObjectMapper把json字串轉成POJO
        OrigCoinDeskDto origCoinDeskDto = objectMapper.readValue(content, OrigCoinDeskDto.class);
        //資料轉換
        return modelMapper.map(origCoinDeskDto, CoinDeskDto.class);
    }

}
