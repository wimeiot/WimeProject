DROP DATABASE IF EXISTS wime;
CREATE DATABASE wime CHARACTER SET utf8 COLLATE utf8_general_ci; 
USE wime;

/*會員Customer Table*/ 
DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer
(
 CM_ID            INT AUTO_INCREMENT NOT NULL ,      /*PK FK使用者ID*/
 CM_RegistDate    DATETIME NOT NULL DEFAULT NOW(),	 /*註冊日期*/
 CM_Password      VARCHAR(32) NOT NULL,         	 /*密碼*/
 CM_Name          VARCHAR(30) NOT NULL,	        	 /*姓名*/
 CM_Mail          VARCHAR(30) NOT NULL,	 	         /*e-mail*/
 CM_Phone         VARCHAR(10),		      	         /*室內電話*/
 CM_Mobile        VARCHAR(10),		      			 /*手機*/
 CM_Address       VARCHAR(50),		      			 /*地址*/
 CM_BirthDay      DATE,			          			 /*生日*/
 CM_Gender        CHAR(1),	              			 /*性別*/
 CM_Verified      CHAR(1) DEFAULT 'F',     			 /*驗證信*/
 PRIMARY KEY(CM_ID),
 UNIQUE KEY(CM_Mail)
)ENGINE = INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;


/*客戶服務Respond Table*/ 
DROP TABLE IF EXISTS Respond;
CREATE TABLE Respond
(
 RP_ID            INT AUTO_INCREMENT NOT NULL ,      /*PK 訊息ID*/
 RP_QuestionType  INT(2) NOT NULL,    	             /*問題種類*/
 RP_Name          VARCHAR(30) NOT NULL,	             /*姓名*/
 RP_Mail          VARCHAR(30) NOT NULL,	             /*e-mail*/
 RP_Message       TEXT NOT NULL,	      	         /*留言*/
 PRIMARY KEY(RP_ID)
)ENGINE = INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;

/*產品Product Table*/ 
DROP TABLE IF EXISTS Product;
CREATE TABLE Product
(
PD_ID            INT AUTO_INCREMENT NOT NULL , /* PK FK 產品料號*/
PD_Number        INT(3) NOT NULL,              /*產品數量*/        
PD_Name          VARCHAR(30) NOT NULL,		    /*產品名稱*/
PD_Stock         INT(5) NOT NULL,		  	   /*庫存數量*/
PD_Desc          VARCHAR(100) NOT NULL,		   /*產品料號*/
PD_Price         DECIMAL(8,2) NOT NULL ,	   /*FK產品價格*/
PD_Pic           VARCHAR(50) NOT NULL,   	   /*產品圖片*/
PD_Software      VARCHAR(50),   	           /*QRcode軟體下載*/
PD_SoftDesc      TEXT,		   	   	   		   /*軟體說明*/
PD_Image         MEDIUMBLOB,
 PRIMARY KEY(PD_ID),
 UNIQUE KEY(PD_Name)
)ENGINE = INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;

/*通路商Distributor Table*/ 
DROP TABLE IF EXISTS Distributor;
CREATE TABLE Distributor
(
 DTB_ID           INT(5) AUTO_INCREMENT NOT NULL ,	  /*PK 公司編號*/     
 DTB_Name         VARCHAR(30) NOT NULL,      		  /*公司名稱*/
 DTB_UniNo        INT(10),      			  		  /*統編*/
 DTB_Capital      INT(10) NOT NULL,      		 	  /*資本額*/
 DTB_President    VARCHAR(10) NOT NULL,               /*負責人*/
 DTB_Gender       INT(1),      		  		  		  /*負責人性別*/
 DTB_Contact      VARCHAR(10) NOT NULL,         	  /*聯絡人*/
 DTB_Phone        VARCHAR(10) NOT NULL,       		  /*聯絡電話*/
 DTB_Intro        BLOB NOT NULL,      			  	  /*公司介紹*/
 DTB_Mail         VARCHAR(30),        		    	  /*聯絡信箱*/
 DTB_Message      TEXT,      		   	         	  /*留言板*/
 PRIMARY KEY(DTB_ID)
)ENGINE = INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;
 
/*廠商Factory Table*/ 
DROP TABLE IF EXISTS Factory;
CREATE TABLE Factory
(
 FT_ID           INT(5) AUTO_INCREMENT NOT NULL , 	     /*PK 公司編號*/ 
 FT_Name         VARCHAR(30) NOT NULL,      		     /*公司名稱*/      
 FT_UniNo        INT(10),				     			 /*統編*/
 FT_Capital      INT(10) NOT NULL,          	         /*資本額*/
 FT_President    VARCHAR(10) NOT NULL,			     	 /*負責人*/
 FT_Gender       INT(1),      		  	             	 /*負責人性別*/
 FT_Contact      VARCHAR(10) NOT NULL,     	    	     /*聯絡人*/  
 FT_Phone        VARCHAR(10) NOT NULL,       		     /*聯絡電話*/
 FT_Intro        BLOB NOT NULL,      		             /*公司介紹*/
 FT_Mail         VARCHAR(30),        		    	     /*聯絡信箱*/ 
 FT_Message      TEXT,      		   		     		 /*留言板*/
 PRIMARY KEY(FT_ID)
 )ENGINE = INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;

 /*人才募集Applicant Table*/ 
DROP TABLE IF EXISTS Applicant;
CREATE TABLE Applicant
(
 AP_ID           INT(5) AUTO_INCREMENT NOT NULL , 	 /*PK 求職編號*/
 AP_Name         VARCHAR(30) NOT NULL,             	 /*求職姓名*/
 AP_Gender       INT(1), 							 /*求職性別*/
 AP_BirthDay     DATE NOT NULL,    					 /*求職生日*/
 AP_Phone        VARCHAR(10) NOT NULL,  			 /*求職電話*/
 AP_Resume       BLOB NOT NULL, 				 	 /*求職履歷?*/
 AP_Mail         VARCHAR(30) NOT NULL, 				 /*求職信箱*/
 AP_Message      TEXT, 								 /*求職留言板*/
 PRIMARY KEY(AP_ID)
 )ENGINE = INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;
 
/*訂單Order Table */ 
DROP TABLE IF EXISTS Orderform;
CREATE TABLE Orderform
(
 Ord_ID                 INT AUTO_INCREMENT NOT NULL , /*PK訂單編號*/
 CM_ID                  INT  NOT NULL,  			  /*FK 使用者ID*/
 Ord_Date               DATETIME   NOT NULL,          /*下單日期*/
 Ord_TotalPrice         DECIMAL(8,2) NOT NULL,        /*總金額*/
 Ord_Destination        VARCHAR(50) NOT NULL,         /*目的地收貨地址*/
 Ord_BuyerName          VARCHAR(30) NOT NULL,         /*購買人姓名*/
 Ord_BuyerPhone         VARCHAR(10) NOT NULL,         /*購買人電話*/
 Ord_BuyerEMail         VARCHAR(30) NOT NULL,         /*購買人email*/
 Ord_BuyerAddress       VARCHAR(50) NOT NULL,         /*購買人地址*/
 Ord_ReceiverName       VARCHAR(30) NOT NULL,         /*收件人姓名*/
 Ord_ReceiverPhone      VARCHAR(10) NOT NULL,         /*收件人電話*/
 Ord_ReceiverAddress    VARCHAR(50) NOT NULL,         /*收件人地址*/
 Ord_OutDate            DATETIME ,		     		  /*出貨日期*/
 Ord_IfOut              INT(1) NOT NULL,	          /*是否出貨*/
 Ord_ArriveDate         DATETIME ,		    		  /*到貨日期*/
 Ord_IfArrive           INT(1) NOT NULL,	     	  /*是否到貨*/
 Ord_IfMember           INT(1) NOT NULL,              /*是否會員*/
 PRIMARY KEY(Ord_ID),
 FOREIGN KEY(CM_ID) REFERENCES Customer(CM_ID) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;
 
/*訂單明細OrderDetails Table */ 
DROP TABLE IF EXISTS OrderDetails;
CREATE TABLE OrderDetails
(
 Ord_ID                   INT        NOT NULL,   /*FK 訂單編號*/
 PD_ID                    INT        NOT NULL,   /*FK 產品料號*/
 PD_Name           VARCHAR(30)       NOT NULL,   /*FK 產品名稱*/       
 OrdDetail_Quantity       INT(5)     NOT NULL,   /*產品數量?*/
 unitPrice		  INT        NOT NULL,   /*FK 單價*/
 subTotal		  INT        NOT NULL,   /*FK 總價*/
 FOREIGN KEY(Ord_ID) REFERENCES Orderform(Ord_ID) ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY(PD_ID) REFERENCES Product(PD_ID) ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY(PD_Name) REFERENCES Product(PD_Name) ON DELETE CASCADE ON UPDATE CASCADE,
 PRIMARY KEY(Ord_ID,PD_ID)
)ENGINE = INNODB CHARACTER SET UTF8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS vacancies;
CREATE TABLE vacancies (
v_id int(4) NOT NULL AUTO_INCREMENT,
v_content varchar(300) NOT NULL,
v_time varchar(15) NOT NULL,
v_locate varchar(25) NOT NULL,
v_describe varchar(300) NOT NULL,
v_salary varchar(10) DEFAULT NULL,
v_name varchar(30) NOT NULL,
v_welfare varchar(300) NOT NULL,
PRIMARY KEY (v_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS news;
CREATE TABLE `news` (
`ARTICLE_ID` INT(5) NOT NULL AUTO_INCREMENT,
`TITLE` VARCHAR(50) NOT NULL,
`ARTICLE` VARCHAR(200) NOT NULL,
`CREATE_TIME` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`CREATOR` VARCHAR(10) NOT NULL,
`PIC` BLOB,
`PICLOC` VARCHAR(100) DEFAULT NULL,
`ARTICLE_LOC` VARCHAR(100) DEFAULT NULL,
PRIMARY KEY (`ARTICLE_ID`)
) ENGINE=INNODB AUTO_INCREMENT=98769 DEFAULT CHARSET=utf8;

/*後台管理系統表格*/
DROP TABLE IF EXISTS wb_product;
create table `wb_product` (
	`PDid` int (11),
	`price` int (11),
	`pdname` varchar (30)
); 

DROP TABLE IF EXISTS wb_order_list;
create table `wb_order_list` (
	`ODid` int (11),
	`orderDate` datetime ,
	`total` int (11)
); 

DROP TABLE IF EXISTS wb_order_detail;
create table `wb_order_detail` (
	`amount` int (11),
	`fk_product_id` int (11),
	`fk_orderform_id` int (11),
	`totalprice` int (11)
); 

/*====================以下為插入資料============================
====================以下為插入資料============================
====================以下為插入資料============================
====================以下為插入資料============================
====================以下為插入資料============================
====================以下為插入資料============================
====================以下為插入資料============================*/



INSERT INTO news (TITLE, ARTICLE, CREATOR, PIC, PICLOC , ARTICLE_LOC ) VALUES 
("貓咪的左右撇子原因是？你不知道的8個貓咪小知識", 
"愛貓成痴的你對於貓咪的知識了解多少呢？時而冷酷時而親近，貓咪優雅的姿態還有令人捉摸不定的性格不知道讓多少貓奴甘願拜倒在他們的魅力之下呢～今天妞編輯要來跟大家分享幾個關於貓咪的小知識，喜歡貓咪的妞妞千萬別錯過喔！", 
"WIME特派員 啦啦", 
LOAD_FILE('D:\Desktop\wimi\img\02_1.jpg'), 
'../images/article1_1.jpg', 
'news1.html' 
), 

("《貓後腳寫真集》繼貓手手之後又一特寫喵部位的毛呼呼寫真集", 
"每天跟家裡的毛小孩相處在一起，相信對於所有飼主來說一定有特別偏好牠們身上的某個部位吧？像如夢也特別喜歡手手（前腳）輕輕摸有Q彈肉墊、拿起來聞還有動物特有讓人想一聞再聞的氣味(*´∀`)~♥或許正因為毛孩身體的每個部就出版了那麼本完全特寫前腳的《貓手手寫真集》", 
"WIME特派員 如夢", 
LOAD_FILE('D:\Desktop\wimi\img\02_1.jpg'), 
'../images/article2_1.jpg', 
'news2.html'), 

("貓奴發現《我家貓咪一定是來自迪士尼世界》因為總是有動", "說到跟想到迪士尼公主不外乎就是夢幻跟夢幻還有夢幻！如果問公主們有什麼特殊能力，我想應該有不少卡友會跟我一樣，覺得最不可思議的就是他們有辦法唱唱歌就飛來或跑來一群可愛小動物！還可以跟動物溝通，而且動物們還都聽得懂公主在說什麼，簡直可以列入不可思議事件名冊了！不過因為是童話故事，所以只要夠夢幻都可以被合理化！", 
"WIME特派員 神技", 
LOAD_FILE('D:\Desktop\wimi\img\01_2.jpg'), 
'../images/article2_2.jpg', 
'news3.html'), 


("便盆上用《JoJo姿拉屎的貓》這姿勢未免也太神了XD", "貓咪真的是太難以理解的生物了XD 像是看起來明明是有骨骼的動物才對但是《貓根本是液體啊》還有《貓會跟人搶地方睡》各種微妙神奇的行為都讓我們這些貓奴們覺得黑人三問號啊！", 
"WIME特派員 多多", 
LOAD_FILE('D:\Desktop\wimi\img\01_3.jpg'), 
'../images/article2_3.jpg', 
'news4.html');



INSERT INTO vacancies(
v_name, v_content, v_time, v_locate, v_describe, v_salary, v_welfare
)VALUES(
'鏟屎官', '需要有耐心，喜歡貓，對貓不過敏，動物相關科系畢業者尤佳。', '日班，9:00~18:00', '台北市大安區仁愛路四段68號6樓之1', '完成主管交辦事項，內勤業務工作。', '面議', '三節禮金，年終獎金'
),(
'工程師', '資訊相關科系畢業，精通MYSQL，JAVA，及前端語言。', '日班，9:00~18:00', '台北市大安區仁愛路四段68號6樓之1', '完成主管交辦事項，內勤業務工作。', '面議', '三節禮金，年終獎金'
),(
'設計師', '視覺設計相關科系畢業，精通平面設計，有相關經驗二年以上。', '日班，9:00~18:00', '台北市大安區仁愛路四段68號6樓之1', '完成主管交辦事項，內勤業務工作，與印刷廠聯繫。', '面議', '三節禮金，年終獎金'
);

/*後台管理系統假資料*/
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('243','1','1','1457757');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('546','2','1','1359540');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('890','3','1','1334110');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('312','4','1','1244880');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('31','5','1','123690');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('243','6','1','359640');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('888','7','1','399600');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('124','8','1','308760');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('121','9','1','302379');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('108','10','1','70092');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('975','11','1','584025');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('671','12','1','798490');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('784','13','1','391216');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('621','14','1','291249');
insert into `wb_order_detail` (`amount`, `fk_product_id`, `fk_orderform_id`, `totalprice`) values('563','15','1','205495');

insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('1','2016-01-01 00:00:01','61690');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('2','2016-02-01 00:00:01','60000');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('3','2016-03-01 00:00:01','89123');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('4','2016-04-01 00:00:01','98012');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('5','2016-05-01 00:00:01','102981');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('6','2016-06-01 00:00:01','398276');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('7','2016-07-01 00:00:01','459102');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('8','2016-08-01 00:00:01','808637');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('9','2016-09-01 00:00:01','1092876');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('10','2016-10-01 00:00:01','1457757');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('11','2016-11-01 00:00:01','1623387');
insert into `wb_order_list` (`ODid`, `orderDate`, `total`) values('12','2016-12-01 00:00:01','1457757');

insert into `wb_product` (`PDid`, `price`, `pdname`) values('1','5999','陪伴機器人');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('2','2490','自動飼料機');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('3','1499','寵物沙發床');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('4','3990','抓板溜滑梯');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('5','3990','木製貓跳台');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('6','1480','貓咪九宮格');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('7','450','貓咪玩具鼠');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('8','2490','雙層貓砂盆');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('9','2499','迷你鐵貓籠');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('10','649','小貓蔬菜糧');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('11','599','綜合貓糧食');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('12','1190','寵物攜帶包');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('13','499','貓咪維他命');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('14','469','發光玩具球');
insert into `wb_product` (`PDid`, `price`, `pdname`) values('15','365','金槍魚貓糧');


