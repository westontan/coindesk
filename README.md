# coindesk

### 專案說明
使用 Spring Boot 及 Spring Data JPA 開發

### 功能簡述
1. 使用 H2 資料庫建立一張幣別與其中文名稱的對應表，並提供 CRUD 接口
2. 請求 coindesk API，將其內容進行資料轉換，提供新的查詢接口
    - coindesk API：https://api.coindesk.com/v1/bpi/currentprice.json
    - 回傳內容：
        - 更新時間（時間格式範例：1990/01/01 00:00:00）
        - 幣別相關資訊（幣別，幣別中文名稱，以及匯率）
	
### 單元測試
1. 測試呼叫查詢幣別對應表資料接口，並顯示其內容。
2. 測試呼叫新增幣別對應表資料接口
3. 測試呼叫更新幣別對應表資料接口，並顯示其內容
4. 測試呼叫刪除幣別對應表資料接口
5. 測試呼叫 coindesk API，並顯示其內容
6. 測試呼叫新的 coindesk 接口，並顯示其內容

### 檔案說明
|檔案|說明|
|-|-|
|src/main/external/application.properties|專案設定檔，此檔在專案打包後會與 jar 檔一同複製到 release 資料夾，方便人員修改設定|
|src/main/resources/application.properties|專案內部設定檔，此檔在專案打包後會包進 jar 檔裡|
|src/main/resources/log4j2-spring.xml|log4j2 的設定檔|
|src/main/resources/schema.sql|由 Spring Boot 於初始階段執行的 DDL 語法檔，存放建立幣別對應表的 CREATE TABLE 語法|
|src/main/resources/data.sql|由 Spring Boot 於初始階段執行的 DML 語法檔，存放幣別對應表初始資料的 INSERT 語法|
