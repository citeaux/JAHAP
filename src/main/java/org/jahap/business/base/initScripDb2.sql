
CREATE TABLE Paymenttypes (
                ID BIGINT ,
                Name VARCHAR(50) ,
                receivable BOOLEAN ,
                CONSTRAINT Paymenttypes_pk PRIMARY KEY (ID)
);


CREATE TABLE receivables (
                ID BIGINT ,
                debit BOOLEAN ,
                paymenttype BIGINT ,
                CONSTRAINT receivables_pk PRIMARY KEY (ID)
);


CREATE TABLE payed (
                ID BIGINT ,
                debit BOOLEAN ,
                paymenttype BIGINT,
                CONSTRAINT payed_pk PRIMARY KEY (ID)
);


CREATE TABLE rates (
                ID BIGINT NOT NULL,
                Name VARCHAR(255),
                price DECIMAL(8,2),
                Code VARCHAR(50),
                revaccount BIGINT,
                CONSTRAINT rates_pk PRIMARY KEY (ID)
);


CREATE TABLE log_accounting (
                ID BIGINT ,
                amount DECIMAL,
                Date VARCHAR(50),
                time VARCHAR(50),
                account VARCHAR(50),
                account_position BIGINT ,
                positionname VARCHAR(255),
                CONSTRAINT log_accounting_pk PRIMARY KEY (ID)
);


CREATE TABLE accounts (
                ID BIGINT ,
                balance DECIMAL,
                checkout BOOLEAN,
                checkinDate VARCHAR(50),
                checkoutDate VARCHAR(50) ,
                CONSTRAINT accounts_pk PRIMARY KEY (ID)
);


CREATE TABLE account_position (
                ID BIGINT ,
                billed BOOLEAN ,
                amount DECIMAL(8,2),
                debit BOOLEAN ,
                rate BIGINT ,
                rateDate DATE ,
                account BIGINT ,
                positioname VARCHAR(255),
                CONSTRAINT account_position_pk PRIMARY KEY (ID)
);


CREATE TABLE revenue (
                ID BIGINT ,
                amount DECIMAL(8,2),
                debit BOOLEAN ,
                accountposition BIGINT ,
                revaccount BIGINT,
                CONSTRAINT revenue_pk PRIMARY KEY (ID)
);


CREATE TABLE ROOMS (
                ID BIGINT ,
                CATEGORY VARCHAR(255),
                CODE VARCHAR(255),
                NAME VARCHAR(255),
                CONSTRAINT SQL130111115648290 PRIMARY KEY (ID)
);


CREATE INDEX ROOMS_idx
 ON ROOMS
 ( CODE ASC );

CREATE TABLE ADDRESS (
                ID BIGINT ,
                CHRISTIANNAME VARCHAR(255),
                CITY VARCHAR(255),
                EMAIL VARCHAR(255),
                NAME VARCHAR(255),
                PHONE VARCHAR(255),
                STREET VARCHAR(255),
                ZIPCODE VARCHAR(255),
                CONSTRAINT SQL130103114019040 PRIMARY KEY (ID)
);


CREATE INDEX ADDRESS_idx
 ON ADDRESS
 ( NAME ASC );

CREATE INDEX ADDRESS_idx1
 ON ADDRESS
 ( NAME ASC );

CREATE TABLE bill (
                ID BIGINT ,
                balance DECIMAL,
                billno BIGINT ,
                checkout BOOLEAN,
                checkinDate VARCHAR(50),
                checkoutDate VARCHAR(50) ,
                resno BIGINT ,
                closed BOOLEAN ,
                billdate DATE ,
                address BIGINT ,
                CONSTRAINT bill_pk PRIMARY KEY (ID)
);


CREATE INDEX accounts_idx
 ON bill
 ( resno ASC );

CREATE TABLE billPosition (
                ID BIGINT ,
                amount DECIMAL ,
                rate BIGINT ,
                billed BOOLEAN ,
                receivable BIGINT ,
                amount_1 DECIMAL,
                debit BOOLEAN ,
                bill BIGINT ,
                rateDate DATE ,
                payed BIGINT ,
                CONSTRAINT billPosition_pk PRIMARY KEY (ID)
);


CREATE TABLE RES (
                ID BIGINT ,
                arrivaltime VARCHAR(50),
                arrivaldate VARCHAR(50),
                departuretime VARCHAR(50),
                resno VARCHAR(50) ,
                account BIGINT ,
                addressid BIGINT ,
                departuredate VARCHAR(50),
                CONSTRAINT RES_ID PRIMARY KEY (ID)
);


CREATE INDEX RES_idx
 ON RES
 ( arrivaldate ASC, resno ASC );

CREATE TABLE occ (
                ID BIGINT ,
                arrivaltime TIME,
                departuretime TIME,
                arrivaldate DATE,
                departuredate DATE,
                room BIGINT ,
                res BIGINT ,
                CONSTRAINT occ_ID PRIMARY KEY (ID)
);


CREATE TABLE revaccounts (
                ID BIGINT NOT NULL,
                revaccnumber BIGINT,
                name VARCHAR(255),                               
                CONSTRAINT revaccount_pk PRIMARY KEY (ID)
);


ALTER TABLE ROOT.payed ADD CONSTRAINT Paymenttypes_payed_fk
FOREIGN KEY (paymenttype)
REFERENCES ROOT.Paymenttypes (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;


ALTER TABLE ROOT.receivables ADD CONSTRAINT Paymenttypes_receivables_fk
FOREIGN KEY (paymenttype)
REFERENCES ROOT.Paymenttypes (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.billPosition ADD CONSTRAINT receivables_billPosition_fk
FOREIGN KEY (receivable)
REFERENCES ROOT.receivables (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.billPosition ADD CONSTRAINT payed_billPosition_fk
FOREIGN KEY (payed)
REFERENCES ROOT.payed (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.account_position ADD CONSTRAINT rates_account_position_fk
FOREIGN KEY (rate)
REFERENCES ROOT.rates (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.account_position ADD CONSTRAINT accounts_account_position_fk
FOREIGN KEY (account)
REFERENCES ROOT.accounts (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.RES ADD CONSTRAINT accounts_RES_fk
FOREIGN KEY (account)
REFERENCES ROOT.accounts (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.revenue ADD CONSTRAINT account_position_revenue_fk
FOREIGN KEY (accountposition)
REFERENCES ROOT.account_position (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.occ ADD CONSTRAINT ROOMS_occ_fk
FOREIGN KEY (room)
REFERENCES ROOT.ROOMS (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.RES ADD CONSTRAINT ADDRESS_RES_fk
FOREIGN KEY (addressid)
REFERENCES ROOT.ADDRESS (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.bill ADD CONSTRAINT ADDRESS_bill_fk
FOREIGN KEY (address)
REFERENCES ROOT.ADDRESS (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.billPosition ADD CONSTRAINT bill_billPosition_fk
FOREIGN KEY (bill)
REFERENCES ROOT.bill (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.occ ADD CONSTRAINT RES_occ_fk
FOREIGN KEY (res)
REFERENCES ROOT.RES (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
;

ALTER TABLE ROOT.revenue ADD CONSTRAINT revenue_revenueacc_fk
FOREIGN KEY (revaccount)
REFERENCES ROOT.Revaccounts (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION;