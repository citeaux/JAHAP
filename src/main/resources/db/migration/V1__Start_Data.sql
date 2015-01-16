


-- BEGIN TABLE ACCOUNTS
CREATE TABLE  ACCOUNTS
(
   ID            BIGINT        NOT NULL,
   BALANCE       DECIMAL(5),
   CHECKOUT      BOOLEAN,
   CHECKINDATE   VARCHAR(50),
   CHECKOUTDATE  VARCHAR(50),
   CSCSERVICE    BIGINT,
   ADDRESS       BIGINT,
   RESERVATION   BIGINT
);

ALTER TABLE  ACCOUNTS
   ADD CONSTRAINT ACCOUNTS_PK
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205175351790
   ON  ACCOUNTS (RESERVATION ASC);

CREATE INDEX  SQL140205174658710
   ON  ACCOUNTS (ADDRESS ASC);

-- END TABLE ACCOUNTS

-- BEGIN TABLE ACCOUNT_POSITION
CREATE TABLE  ACCOUNT_POSITION
(
   ID                BIGINT         NOT NULL,
   BILL              BIGINT,
   VAT               BIGINT,
   PAYMENT           BIGINT,
   BILLED            BOOLEAN,
   AMOUNT            INTEGER,
   PRICE             DECIMAL(8,2),
   DEBIT             BOOLEAN,
   RATE              BIGINT,
   CANCELEDPOSITION  BIGINT,
   CANCELED          BOOLEAN,
   RATEDATE          DATE,
   ACCOUNT           BIGINT,
   POSITIONNAME      VARCHAR(255)
);

ALTER TABLE  ACCOUNT_POSITION
   ADD CONSTRAINT ACCOUNT_POSITION_PK
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205175454250
   ON  ACCOUNT_POSITION (VAT ASC);

CREATE INDEX  SQL140205175226320
   ON  ACCOUNT_POSITION (ACCOUNT ASC);

CREATE INDEX  SQL140205175226250
   ON  ACCOUNT_POSITION (RATE ASC);

CREATE INDEX  SQL140205175512650
   ON  ACCOUNT_POSITION (PAYMENT ASC);

-- END TABLE ACCOUNT_POSITION

-- BEGIN TABLE ADDRESS
CREATE TABLE  ADDRESS
(
   ID             BIGINT         NOT NULL,
   CHRISTIANNAME  VARCHAR(255),
   CITY           VARCHAR(255),
   EMAIL          VARCHAR(255),
   NAME           VARCHAR(255),
   PHONE          VARCHAR(255),
   STREET         VARCHAR(255),
   ZIPCODE        VARCHAR(255),
   COUNTRY        INTEGER,
   CURRENCY       INTEGER,
   LANGUAGE       INTEGER,
   HOMEPAGE       VARCHAR(100),
   ADDRESSTYPE    VARCHAR(100),
   REMARKS        VARCHAR(200),
   GREETING       VARCHAR(50),
   SALUTATION     VARCHAR(50),
   TITLE          VARCHAR(50)
);

ALTER TABLE  ADDRESS
   ADD CONSTRAINT SQL130103114019040
   PRIMARY KEY (ID);

CREATE INDEX  ADDRESS_IDX
   ON  ADDRESS (NAME ASC);

-- END TABLE ADDRESS

-- BEGIN TABLE BILL
CREATE TABLE  BILL
(
   ID            BIGINT         NOT NULL,
   BILLNO        BIGINT,
   ADDRESS       BIGINT,
   BILLDATE      DATE,
   BILLNAME      VARCHAR(100),
   CANCELED      BOOLEAN,
   CANCELEDBILL  BIGINT,
   TOTAL         DECIMAL(8,2),
   BILLNOSTRING  VARCHAR(200),
   UUID          VARCHAR(200),
   BILLCHANGE    TIMESTAMP,
   TEMP_BILL     BOOLEAN
);

ALTER TABLE  BILL
   ADD CONSTRAINT BILL_PK
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205175555720
   ON  BILL (ADDRESS ASC);

-- END TABLE BILL

-- BEGIN TABLE BILL_NO
CREATE TABLE  BILL_NO
(
   BILLNO  BIGINT   NOT NULL
);

ALTER TABLE  BILL_NO
   ADD CONSTRAINT SQL140724165609830
   PRIMARY KEY (BILLNO);
-- END TABLE BILL_NO

-- BEGIN TABLE CHOICE
CREATE TABLE  CHOICE
(
   ID           BIGINT         NOT NULL,
   GROUPID      INTEGER,
   GROUPCODE    VARCHAR(5),
   GROUPNAME    VARCHAR(50),
   CHOICECODE   VARCHAR(5),
   CHOICETEXT   VARCHAR(100),
   CHOICEINT    INTEGER,
   CHOICEFLOAT  DECIMAL(8,2),
   LANGUAGE     INTEGER
);

ALTER TABLE  CHOICE
   ADD CONSTRAINT SQL140919175027360
   PRIMARY KEY (ID);
-- END TABLE CHOICE

-- BEGIN TABLE COUNTRY
CREATE TABLE  COUNTRY
(
   ID            INTEGER        NOT NULL,
   COUNTRY_CODE  VARCHAR(10),
   COUNTRY_NAME  VARCHAR(100),
   LANGUAGE      INTEGER,
   CURRENCY      INTEGER
);

ALTER TABLE  COUNTRY
   ADD CONSTRAINT SQL140908163644310
   PRIMARY KEY (ID);
-- END TABLE COUNTRY

-- BEGIN TABLE CSC
CREATE TABLE  CSC
(
   ID        BIGINT          NOT NULL,
   RATE      BIGINT,
   FROMDATE  DATE,
   TODATE    DATE,
   ACCOUNT   BIGINT,
   AMOUNT    SMALLINT,
   PRICE     DECIMAL(10,2),
   SERVICE   VARCHAR(100)
);

ALTER TABLE  CSC
   ADD CONSTRAINT CSC_PK
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205174658020
   ON  CSC (RATE ASC);

CREATE INDEX  SQL140205174658110
   ON  CSC (ACCOUNT ASC);

-- END TABLE CSC

-- BEGIN TABLE CURRENCY
CREATE TABLE  CURRENCY
(
   ID               INTEGER        NOT NULL,
   CURRENCY_CODE    VARCHAR(255),
   CURRENCY_NAME    VARCHAR(255),
   CURRENCY_SYMBOL  CHAR(1)
);

ALTER TABLE  CURRENCY
   ADD CONSTRAINT SQL140916154841950
   PRIMARY KEY (ID);
-- END TABLE CURRENCY

-- BEGIN TABLE HOTEL
CREATE TABLE  HOTEL
(
   ID                      INTEGER        NOT NULL,
   HOTEL_CODE              VARCHAR(10),
   HOTEL_NAME              VARCHAR(100),
   HOTEL_ADRESS            BIGINT,
   HOTEL_BANKACCOUNTDATA1  VARCHAR(200),
   HOTEL_BANKACCOUNTDATA2  VARCHAR(200),
   HOTEL_LANGUAGE          INTEGER,
   HOTEL_COUNTRY           INTEGER,
   HOTEL_CURRENCY          INTEGER,
   HOTEL_FOOTERTEXT        VARCHAR(200)
   operationdate           date,
);

ALTER TABLE  HOTEL
   ADD CONSTRAINT SQL140909113600460
   PRIMARY KEY (ID);
-- END TABLE HOTEL

-- BEGIN TABLE LANGUAGE
CREATE TABLE  LANGUAGE
(
   ID             INTEGER       NOT NULL,
   LANGUAGE_CODE  VARCHAR(5),
   LANGUAGE_NAME  VARCHAR(50)
);

ALTER TABLE  LANGUAGE
   ADD CONSTRAINT SQL140908162828940
   PRIMARY KEY (ID);
-- END TABLE LANGUAGE

-- BEGIN TABLE LOG_ACCOUNTING
CREATE TABLE  LOG_ACCOUNTING
(
   ID                BIGINT         NOT NULL,
   AMOUNT            DECIMAL(5),
   DATE              VARCHAR(50),
   TIME              VARCHAR(50),
   ACCOUNT           VARCHAR(50),
   ACCOUNT_POSITION  BIGINT,
   POSITIONNAME      VARCHAR(255)
);

ALTER TABLE  LOG_ACCOUNTING
   ADD CONSTRAINT LOG_ACCOUNTING_PK
   PRIMARY KEY (ID);
-- END TABLE LOG_ACCOUNTING

-- BEGIN TABLE OCC
CREATE TABLE  OCC
(
   ID             BIGINT   NOT NULL,
   ARRIVALTIME    TIME,
   DEPARTURETIME  TIME,
   ARRIVALDATE    DATE,
   GUEST          BIGINT   NOT NULL,
   DEPARTUREDATE  DATE,
   ROOM           BIGINT,
   RES            BIGINT,
   ACCOUNT        BIGINT
);

ALTER TABLE  OCC
   ADD CONSTRAINT OCC_ID
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205174658290
   ON  OCC (RES ASC);

CREATE INDEX  SQL140205174658530
   ON  OCC (ROOM ASC);

CREATE INDEX  SQL140205174658450
   ON  OCC (ACCOUNT ASC);

CREATE INDEX  SQL140205174658380
   ON  OCC (GUEST ASC);

-- END TABLE OCC

-- BEGIN TABLE PAYED
CREATE TABLE  PAYED
(
   ID               BIGINT         NOT NULL,
   DEBIT            BOOLEAN,
   PAYMENTTYPE      BIGINT,
   OPENPOS          BOOLEAN,
   TOTAL            DECIMAL(8,2),
   CANCELED         BOOLEAN,
   CANCELEDPAYMENT  BIGINT
);

ALTER TABLE  PAYED
   ADD CONSTRAINT PAYED_PK
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205175612080
   ON  PAYED (PAYMENTTYPE ASC);

-- END TABLE PAYED

-- BEGIN TABLE PAYMENTTYPES
CREATE TABLE  PAYMENTTYPES
(
   ID          BIGINT        NOT NULL,
   NAME        VARCHAR(50),
   RECEIVABLE  BOOLEAN
);

ALTER TABLE  PAYMENTTYPES
   ADD CONSTRAINT PAYMENTTYPES_PK
   PRIMARY KEY (ID);
-- END TABLE PAYMENTTYPES

-- BEGIN TABLE RATES
CREATE TABLE  RATES
(
   ID          BIGINT         NOT NULL,
   NAME        VARCHAR(255),
   PRICE       DECIMAL(8,2),
   CODE        VARCHAR(50),
   REVACCOUNT  BIGINT,
   OVERNIGHT   BOOLEAN,
   VATTYPE     BIGINT,
   NET         BOOLEAN
);

ALTER TABLE  RATES
   ADD CONSTRAINT RATES_PK
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205175411420
   ON  RATES (VATTYPE ASC);

CREATE INDEX  SQL140205175634700
   ON  RATES (REVACCOUNT ASC);

-- END TABLE RATES

-- BEGIN TABLE RECEIVABLES
CREATE TABLE  RECEIVABLES
(
   ID           BIGINT   NOT NULL,
   DEBIT        BOOLEAN,
   PAYMENTTYPE  BIGINT
);

ALTER TABLE  RECEIVABLES
   ADD CONSTRAINT SQL140207111503680
   PRIMARY KEY (ID);

CREATE INDEX  SQL140207111504760
   ON  RECEIVABLES (PAYMENTTYPE ASC);

-- END TABLE RECEIVABLES

-- BEGIN TABLE REPORTS
CREATE TABLE  REPORTS
(
   ID             INTEGER        NOT NULL,
   NAME           VARCHAR(50),
   DESCRIPTION    VARCHAR(200),
   REPORT_GROUP   VARCHAR(50),
   REPORT         BYTEA,
   REPORT_LAYOUT  BYTEA,
   LANGUAGE       INTEGER
);

ALTER TABLE  REPORTS
   ADD CONSTRAINT SQL140901163435920
   PRIMARY KEY (ID);
-- END TABLE REPORTS

-- BEGIN TABLE RES
CREATE TABLE  RES
(
   ID             BIGINT        NOT NULL,
   ARRIVALTIME    VARCHAR(50),
   ARRIVALDATE    VARCHAR(50),
   DEPARTURETIME  VARCHAR(50),
   RESNO          VARCHAR(50),
   ADDRESSID      BIGINT,
   DEPARTUREDATE  VARCHAR(50)
);

ALTER TABLE  RES
   ADD CONSTRAINT RES_ID
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205174658200
   ON  RES (ADDRESSID ASC);

CREATE INDEX  RES_IDX
   ON  RES (ARRIVALDATE ASC, RESNO ASC);

-- END TABLE RES

-- BEGIN TABLE REVACCOUNTS
CREATE TABLE  REVACCOUNTS
(
   ID                BIGINT         NOT NULL,
   REVACCOUNTNUMBER  BIGINT,
   NAME              VARCHAR(255)
);

ALTER TABLE  REVACCOUNTS
   ADD CONSTRAINT REVACCOUNT_PK
   PRIMARY KEY (ID);
-- END TABLE REVACCOUNTS

-- BEGIN TABLE REVENUE
CREATE TABLE  REVENUE
(
   ID               BIGINT         NOT NULL,
   AMOUNT           DECIMAL(8,2),
   DEBIT            BOOLEAN,
   ACCOUNTPOSITION  BIGINT,
   REVACCOUNT       BIGINT,
   REVDATE          DATE
);

ALTER TABLE  REVENUE
   ADD CONSTRAINT REVENUE_PK
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205174658600
   ON  REVENUE (REVACCOUNT ASC);

CREATE INDEX  SQL140205175246840
   ON  REVENUE (ACCOUNTPOSITION ASC);

-- END TABLE REVENUE

-- BEGIN TABLE ROOMS
CREATE TABLE  ROOMS
(
   ID        BIGINT         NOT NULL,
   CATEGORY  VARCHAR(255),
   CODE      VARCHAR(255),
   NAME      VARCHAR(255)
);

ALTER TABLE  ROOMS
   ADD CONSTRAINT SQL130111115648290
   PRIMARY KEY (ID);

CREATE INDEX  ROOMS_IDX
   ON  ROOMS (CODE ASC);

-- END TABLE ROOMS

-- BEGIN TABLE SEQUENCE
CREATE TABLE  SEQUENCE
(
   SEQ_NAME   VARCHAR(50)   NOT NULL,
   SEQ_COUNT  DECIMAL(15)
);

ALTER TABLE  SEQUENCE
   ADD CONSTRAINT SQL140207111504890
   PRIMARY KEY (SEQ_NAME);
-- END TABLE SEQUENCE

-- BEGIN TABLE SEQ_STORE
CREATE TABLE  SEQ_STORE
(
   TABLE_NAME  VARCHAR(255)   NOT NULL,
   VALUE       BIGINT
);

ALTER TABLE  SEQ_STORE
   ADD CONSTRAINT SQL140724165609650
   PRIMARY KEY (TABLE_NAME);
-- END TABLE SEQ_STORE

-- BEGIN TABLE VAT
CREATE TABLE  VAT
(
   ID               BIGINT         NOT NULL,
   DEBIT            BOOLEAN,
   DATE             DATE,
   VATTYPE          BIGINT,
   ACCOUNTPOSITION  BIGINT,
   AMOUNT           DECIMAL(8,2)
);

ALTER TABLE  VAT
   ADD CONSTRAINT VAT_PK
   PRIMARY KEY (ID);

CREATE INDEX  SQL140205175429540
   ON  VAT (VATTYPE ASC);

-- END TABLE VAT

-- BEGIN TABLE VATTYPE
CREATE TABLE  VATTYPE
(
   ID          BIGINT         NOT NULL,
   NAME        VARCHAR(100),
   DATEVAT     DATE,
   PERCENTAGE  DECIMAL(5)
);

ALTER TABLE  VATTYPE
   ADD CONSTRAINT VATTYPE_PK
   PRIMARY KEY (ID);
-- END TABLE VATTYPE

-- BEGIN FOREIGN KEYS --
ALTER TABLE  ACCOUNTS
  ADD CONSTRAINT ADDRESS_ACCOUNTS_FK FOREIGN KEY (ADDRESS)
  REFERENCES  ADDRESS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  ACCOUNTS
  ADD CONSTRAINT RES_ACCOUNTS_FK FOREIGN KEY (RESERVATION)
  REFERENCES  RES (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  ACCOUNT_POSITION
  ADD CONSTRAINT ACCOUNTS_ACCOUNT_POSITION_FK FOREIGN KEY (ACCOUNT)
  REFERENCES  ACCOUNTS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  ACCOUNT_POSITION
  ADD CONSTRAINT PAYED_ACCOUNT_POSITION_FK FOREIGN KEY (PAYMENT)
  REFERENCES  PAYED (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  ACCOUNT_POSITION
  ADD CONSTRAINT RATES_ACCOUNT_POSITION_FK FOREIGN KEY (RATE)
  REFERENCES  RATES (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  ACCOUNT_POSITION
  ADD CONSTRAINT VAT_ACCOUNT_POSITION_FK FOREIGN KEY (VAT)
  REFERENCES  VAT (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  BILL
  ADD CONSTRAINT ADDRESS_BILL_FK FOREIGN KEY (ADDRESS)
  REFERENCES  ADDRESS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  CSC
  ADD CONSTRAINT ACCOUNTS_CSC_FK FOREIGN KEY (ACCOUNT)
  REFERENCES  ACCOUNTS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  CSC
  ADD CONSTRAINT RATES_CSC_FK FOREIGN KEY (RATE)
  REFERENCES  RATES (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  OCC
  ADD CONSTRAINT ACCOUNTS_OCC_FK FOREIGN KEY (ACCOUNT)
  REFERENCES  ACCOUNTS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  OCC
  ADD CONSTRAINT ADDRESS_OCC_FK FOREIGN KEY (GUEST)
  REFERENCES  ADDRESS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  OCC
  ADD CONSTRAINT RES_OCC_FK FOREIGN KEY (RES)
  REFERENCES  RES (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  OCC
  ADD CONSTRAINT ROOMS_OCC_FK FOREIGN KEY (ROOM)
  REFERENCES  ROOMS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  PAYED
  ADD CONSTRAINT PAYMENTTYPES_PAYED_FK FOREIGN KEY (PAYMENTTYPE)
  REFERENCES  PAYMENTTYPES (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  RATES
  ADD CONSTRAINT REVACCOUNTS_RATES_FK FOREIGN KEY (REVACCOUNT)
  REFERENCES  REVACCOUNTS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  RATES
  ADD CONSTRAINT VATTYPE_RATES_FK FOREIGN KEY (VATTYPE)
  REFERENCES  VATTYPE (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  RECEIVABLES
  ADD CONSTRAINT RCVABLESPYMENTTYPE FOREIGN KEY (PAYMENTTYPE)
  REFERENCES  PAYMENTTYPES (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  RES
  ADD CONSTRAINT ADDRESS_RES_FK FOREIGN KEY (ADDRESSID)
  REFERENCES  ADDRESS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  REVENUE
  ADD CONSTRAINT ACCOUNT_POSITION_REVENUE_FK FOREIGN KEY (ACCOUNTPOSITION)
  REFERENCES  ACCOUNT_POSITION (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  REVENUE
  ADD CONSTRAINT REVENUE_REVENUEACC_FK FOREIGN KEY (REVACCOUNT)
  REFERENCES  REVACCOUNTS (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;

ALTER TABLE  VAT
  ADD CONSTRAINT VATTYPE_VAT_FK FOREIGN KEY (VATTYPE)
  REFERENCES  VATTYPE (ID)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
-- END FOREIGN KEYS --


COMMIT;
