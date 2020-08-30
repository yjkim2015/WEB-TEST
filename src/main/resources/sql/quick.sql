CREATE TABLE TB_USER (
	USER_ID VARCHAR(30) NOT NULL,
	PASSWD VARCHAR(512) NOT NULL,
	USER_NAME VARCHAR(20) NOT NULL,
	BRAND_NAME VARCHAR(30),
	PHONE VARCHAR(512) NOT NULL,
	DRIVER_NUM INT DEFAULT 0,
	ROLE  ENUM('ADMIN','DRIVER','WAREHOUSE'),
	PRIMARY KEY (USER_ID)
) ENGINE=InnoDB DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI;

CREATE TABLE TB_ORDER (
	ORDER_NUM INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	LOGIN_ID VARCHAR(30) NOT NULL,
	PICKUP_DEST VARCHAR(30) NOT NULL,
	PICKUP		ENUM('N','Y') DEFAULT 'N',
	DEST VARCHAR(30) NOT NULL,
	ITEM VARCHAR(30) NOT NULL,
	REPLACE_ITEM ENUM('N','Y') DEFAULT 'N' NOT NULL,
	ORDER_TIME VARCHAR(30) NOT NULL,
	DRIVER_NUM INT	DEFAULT 0,
	PRICE	INT DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI;

CREATE TABLE persistent_logins (
  username varchar(64) DEFAULT NULL,
  series varchar(64) NOT NULL,
  token varchar(64) DEFAULT NULL,
  last_used timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI;






INSERT INTO TB_USER(USER_ID,PASSWD,USER_NAME,PHONE,ROLE) VALUES('root',password('ghkdeotns'),'황대순',password('010-6822-0010'),'ADMIN');

INSERT INTO TB_USER(USER_ID,PASSWD,USER_NAME,PHONE,DRIVER_NUM,ROLE) VALUES('driver1',sha2('driver1',512),'1번기사',sha2('010-1111-2222',512),1,'DRIVER');
