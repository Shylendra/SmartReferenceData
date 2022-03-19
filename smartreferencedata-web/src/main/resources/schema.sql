DROP ALL OBJECTS;

CREATE TABLE SMART_REFERENCE_DATA (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  REF_DATA_CODE VARCHAR(100),
  REF_DATA_TYPE VARCHAR(100),
  REF_DATA_DESC VARCHAR(100),
  REF_DATA_DESCDETAIL VARCHAR(100)
  PROC_TS TIMESTAMP,
  PROC_APP_ID VARCHAR(50),
  PROC_USER_ID VARCHAR(50),
  PROC_USER_IP_ADDRESS VARCHAR(50),
  PROC_USER_LATITUDE VARCHAR(100),
  PROC_USER_LONGITUDE VARCHAR(100),
  VERS_ID smallint NOT NULL
);
