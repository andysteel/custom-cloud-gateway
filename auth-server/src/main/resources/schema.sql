CREATE SCHEMA IF NOT EXISTS "AUTH_SCHEMA";

DROP TABLE IF EXISTS AUTH_SCHEMA.USER;
DROP TABLE IF EXISTS AUTH_SCHEMA.APPLICATION;
DROP TABLE IF EXISTS AUTH_SCHEMA.ROLE;
DROP TABLE IF EXISTS AUTH_SCHEMA.PRIVILEGE;
DROP TABLE IF EXISTS AUTH_SCHEMA.ACCESS;

CREATE TABLE IF NOT EXISTS AUTH_SCHEMA.USER (
    ID NUMBER(10) NOT NULL,
	NAME VARCHAR2(200) NOT NULL,
	LOGIN VARCHAR2(100) NOT NULL,
	PASSWORD VARCHAR2(200) NOT NULL,
	IS_BLOCKED CHAR(1) NOT NULL,
	CREATED_AT TIMESTAMP NOT NULL,
	UPDATED_AT TIMESTAMP NOT NULL,
	IS_PASS_EXPIRED CHAR(1) NOT NULL,
	PASS_EXPIRE_DATE DATE NOT NULL,
    IS_ENABLE CHAR(1) NOT NULL,
    CONSTRAINT USER_PK PRIMARY KEY (ID),
    CONSTRAINT USER_UN_LOGIN UNIQUE (LOGIN)
);

ALTER TABLE AUTH_SCHEMA."USER" ADD CONSTRAINT USER_IS_BLOCKED CHECK ("IS_BLOCKED" IN ('Y','N'));
ALTER TABLE AUTH_SCHEMA."USER" ADD CONSTRAINT USER_IS_PASS_EXPIRED CHECK ("IS_PASS_EXPIRED" IN ('Y','N'));
ALTER TABLE AUTH_SCHEMA."USER" ADD CONSTRAINT USER_IS_ENABLE CHECK ("IS_ENABLE" IN ('Y','N'));
CREATE SEQUENCE IF NOT EXISTS AUTH_SCHEMA.SEQ_USER;

CREATE TABLE IF NOT EXISTS AUTH_SCHEMA.APPLICATION (
    ID NUMBER(10) NOT NULL,
	NAME VARCHAR2(200) NOT NULL,
	CREATED_AT TIMESTAMP NOT NULL,
	UPDATED_AT TIMESTAMP NOT NULL,
	DESCRIPTION VARCHAR2(200) NOT NULL,
	APP_ID VARCHAR2(200) NOT NULL,
	APP_SECRET VARCHAR2(200) NOT NULL,
	URL VARCHAR2(200) NOT NULL,
	WEB_CONTEXT VARCHAR2(200) NOT NULL,
	PORT NUMBER(5) NOT NULL,
	URI VARCHAR2(1000) NOT NULL,
    IS_ENABLE CHAR(1) NOT NULL,
    CONSTRAINT APP_PK PRIMARY KEY (ID),
    CONSTRAINT APP_UN_APP_ID UNIQUE (APP_ID)
);
ALTER TABLE AUTH_SCHEMA."APPLICATION" ADD CONSTRAINT APP_IS_ENABLE CHECK ("IS_ENABLE" IN ('Y','N'));
CREATE SEQUENCE IF NOT EXISTS AUTH_SCHEMA.SEQ_APPLICATION;

CREATE TABLE IF NOT EXISTS AUTH_SCHEMA.ROLE (
    ID NUMBER(10) NOT NULL,
	NAME VARCHAR2(200) NOT NULL,
	CREATED_AT TIMESTAMP NOT NULL,
	UPDATED_AT TIMESTAMP NOT NULL,
	DESCRIPTION VARCHAR2(200) NOT NULL,
    IS_ENABLE CHAR(1) NOT NULL,
    CONSTRAINT ROLE_PK PRIMARY KEY (ID)
);
ALTER TABLE AUTH_SCHEMA."ROLE" ADD CONSTRAINT ROLE_IS_ENABLE CHECK ("IS_ENABLE" IN ('Y','N'));
CREATE SEQUENCE IF NOT EXISTS AUTH_SCHEMA.SEQ_ROLE;

CREATE TABLE IF NOT EXISTS AUTH_SCHEMA.PRIVILEGE (
    ID NUMBER(10) NOT NULL,
	NAME VARCHAR2(200) NOT NULL,
	CREATED_AT TIMESTAMP NOT NULL,
	UPDATED_AT TIMESTAMP NOT NULL,
    IS_ENABLE CHAR(1) NOT NULL,
	ROLE_ID NUMBER(10) NOT NULL,
    CONSTRAINT PRIVILEGE_PK PRIMARY KEY (ID),
	CONSTRAINT PRIVILEGE_ROLE_FK FOREIGN KEY (ROLE_ID) REFERENCES AUTH_SCHEMA.ROLE (ID)
);
ALTER TABLE AUTH_SCHEMA."PRIVILEGE" ADD CONSTRAINT PRIVILEGE_IS_ENABLE CHECK ("IS_ENABLE" IN ('Y','N'));
CREATE SEQUENCE IF NOT EXISTS AUTH_SCHEMA.SEQ_PRIVILEGE;

CREATE TABLE IF NOT EXISTS AUTH_SCHEMA.ACCESS (
    ID NUMBER(10) NOT NULL,
	APP_ID NUMBER(10) NOT NULL,
	ROLE_ID NUMBER(10) NOT NULL,
	USER_ID NUMBER(10) NOT NULL,
	CREATED_AT TIMESTAMP NOT NULL,
	UPDATED_AT TIMESTAMP NOT NULL,
    CONSTRAINT ACCESS_PK PRIMARY KEY (ID),
	CONSTRAINT ACCESS_APP_FK FOREIGN KEY (APP_ID) REFERENCES AUTH_SCHEMA.APPLICATION (ID),
	CONSTRAINT ACCESS_ROLE_FK FOREIGN KEY (ROLE_ID) REFERENCES AUTH_SCHEMA.ROLE (ID),
	CONSTRAINT ACCESS_USER_FK FOREIGN KEY (USER_ID) REFERENCES AUTH_SCHEMA.USER (ID),
	CONSTRAINT ACCESS_UNIQUE UNIQUE (APP_ID, ROLE_ID, USER_ID)
);
CREATE SEQUENCE IF NOT EXISTS AUTH_SCHEMA.SEQ_ACCESS;

