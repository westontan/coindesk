/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import tw.weston.coindesk.dto.CurrencyMappingDto;

/**
 * 單元測試類別
 * @author weston.tan
 */
@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    
    /**
     * 1. 測試呼叫查詢幣別對應表資料API，並顯示其內容。
     */
    @Test
    @Order(1)
    @SneakyThrows
    void testRead() {
        String findAllResult = mockMvc.perform(MockMvcRequestBuilders.get("/currencyMapping").contentType(MediaType.APPLICATION_JSON))
                //檢查是否回傳200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //檢查是否回傳json
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        log.info("All currency mappings => {}", findAllResult);
        String findUSDResult = mockMvc.perform(MockMvcRequestBuilders.get("/currencyMapping/USD").contentType(MediaType.APPLICATION_JSON))
                //檢查是否回傳200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //檢查是否回傳json
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                //檢查是否回傳正確的值
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.cht").value("美金"))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        log.info("USD currency mappings => {}", findUSDResult);
    }
    
    /**
     * 2. 測試呼叫新增幣別對應表資料API。
     */
    @Test
    @Order(2)
    @SneakyThrows
    void testCreate() {
        CurrencyMappingDto currencyMappingDto = new CurrencyMappingDto("JPY", "日幣");
        String requestBody = objectMapper.writeValueAsString(currencyMappingDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/currencyMapping").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                //檢查是否回傳200
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    /**
     * 3. 測試呼叫更新幣別對應表資料API，並顯示其內容。
     */
    @Test
    @Order(3)
    @SneakyThrows
    void testUpdate() {
        CurrencyMappingDto currencyMappingDto = new CurrencyMappingDto("JPY", "日圓");
        String requestBody = objectMapper.writeValueAsString(currencyMappingDto);
        String updateJPYResult = mockMvc.perform(MockMvcRequestBuilders.put("/currencyMapping").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                //檢查是否回傳200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //檢查是否回傳json
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                //檢查是否回傳正確的值
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.eng").value("JPY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.cht").value("日圓"))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        log.info("JPY currency mappings => {}", updateJPYResult);
    }
    
    /**
     * 4. 測試呼叫刪除幣別對應表資料API。
     */
    @Test
    @Order(4)
    @SneakyThrows
    void testDelete() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/currencyMapping/JPY").contentType(MediaType.APPLICATION_JSON))
                //檢查是否回傳200
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * 5. 測試呼叫coindesk API，並顯示其內容。
     */
    @Test
    @Order(5)
    void testCoinDeskAPI() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
        //檢查是否回傳200
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        log.info("Response from CoinDesk API => {}", responseEntity.getBody());
    }
    
    /**
     * 6. 測試呼叫資料轉換的API，並顯示其內容。
     */
    @Test
    @Order(6)
    @SneakyThrows
    void testNewCoinDeskAPI() {
        String coindeskResult = mockMvc.perform(MockMvcRequestBuilders.get("/coindesk").contentType(MediaType.APPLICATION_JSON))
                //檢查是否回傳200
                .andExpect(MockMvcResultMatchers.status().isOk())
                //檢查是否回傳json
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        log.info("Response from New CoinDesk API => {}", coindeskResult);
    }

}
