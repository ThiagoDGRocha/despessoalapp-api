---------------------------------------------------------Tables---------------------------------------------------------

CREATE TABLE BANK (
    ID INTEGER,
    NAME VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    CONSTRAINT CK__BANK__NAME CHECK (NAME IS NOT NULL AND LENGTH(NAME) > 0),
    CONSTRAINT PK__BANK__ID PRIMARY KEY (ID),
    CONSTRAINT UK__BANK__NAME UNIQUE (NAME)
);

CREATE TABLE TRANSACTION (
    ID INTEGER,
    OPERATION_DATE VARCHAR(255),
    OPERATION VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    DEB_CRED VARCHAR(255),
    VALUE NUMERIC(4, 2),
    BANK_ID INTEGER,
    CONSTRAINT CK__TRANSACTION__OPERATION_DATE CHECK (OPERATION_DATE IS NOT NULL AND LENGTH(OPERATION_DATE) > 0),
    CONSTRAINT CK__TRANSACTION__OPERATION CHECK (OPERATION IS NOT NULL AND LENGTH(OPERATION) > 0),
    CONSTRAINT CK__TRANSACTION__DESCRIPTION CHECK (DESCRIPTION IS NOT NULL AND LENGTH(DESCRIPTION) > 0),
    CONSTRAINT CK__TRANSACTION__DEB_CRED CHECK (DEB_CRED IS NOT NULL AND LENGTH(DEB_CRED) > 0),
    CONSTRAINT CK__TRANSACTION__VALUE CHECK (VALUE IS NOT NULL AND LENGTH(VALUE) > 0),
    CONSTRAINT PK__TRANSACTION__ID PRIMARY KEY (ID),
    CONSTRAINT FK__TRANSACTION__BANK_ID FOREIGN KEY (BANK_ID) REFERENCES BANK (ID)
);

-------------------------------------------------------Sequences--------------------------------------------------------

CREATE SEQUENCE SEQ_BANK;
CREATE SEQUENCE SEQ_TRANSACTION;