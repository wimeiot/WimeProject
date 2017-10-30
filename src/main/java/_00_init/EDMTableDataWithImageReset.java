package _00_init;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：eMember, eBook, eBookCompany, Orders, OrderItems
      
(1) eMember欄位說明:
    seqNo       : 會員流水號Pri.Key  整數      IDENTITY
    memberId    : 會員編號           字串      20
    name        : 姓名               字串      32
    password    : 密碼               字串      32
    address     : 地址               字串      64
    email       : email              字串      64
    tel         : 電話日             字串      15
    userType    : 會員類別           字串      10
    experience  : 使用經驗           整數   
    register    : 註冊時間           datetime
    totalAmount : 訂購總金額         money
    memberImage : 照片               image
    fileName    : 封面圖檔檔名       字串      20
    
(2) eBook欄位說明:
    bookId      : 書籍代號Pri.Key    整數      IDENTITY
    title       : 書籍名稱           字串      50
    author      : 作者               字串      28
    price       : 價格               money
    discount    : 折扣               money
    companyId   : 出版社代號         整數      
    fileName    : 封面圖檔檔名       字串      20
    bookNo      : 書號               字串      20
    CoverImage  : 封面照片           image   

(3) eBookCompany欄位說明:
    id 		     : PrimaryKey         整數      IDENTITY
    name         : 出版社名稱         字串      60
    address      : 出版社地址         字串      60
    url          : 出版社URL          字串      120
    
(4) Orders欄位說明:
    orderNo        : 訂單編號PrimaryKey 整數      IDENTITY
    userId         : 客戶編號           字串      20
    totalAmount    : 總金額             money
    shippingAddress: 出貨地址           字串      64    
    BNO            : 統一編號           字串       8
    invoiceTitle   : 發票抬頭           字串      72
    orderDate      : 訂單日期           datetime 
    ShippingDate   : 出貨日期           datetime
    CancelTag      : 取消               字串       1
    
(5) OrderItems欄位說明:
    seqNo        : PrimaryKey            整數      IDENTITY
    orderNo      : 訂單編號              整數
    bookID       : 書籍代號              整數
    Description  : 說明                  字串      72
    amount       : 數量                  int
    unitPrice    : 單價                  money
    Discount     : 折扣                  money
 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;

public class EDMTableDataWithImageReset {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元
	String abc ="";
	public static void main(String args[]) {
		Connection con;
		PreparedStatement pstmt;
		PreparedStatement pstmt1;
		Statement stmt;
		String dropString;
		String setString;
		String setString2;		
		String createString;
		String line = "";
		String sql = "";
		String sql1 = "";
		String encrypedString = "";
		try {
			// 連上後端的資料庫
			con = DriverManager.getConnection(GlobalService.DB_URLMySQL, GlobalService.USERID, GlobalService.PASSWORD);
			// 建立Statement物件，以便傳送SQL命令到後端的資料庫
			stmt = con.createStatement();

		} catch (SQLException e) {
			System.err.println("存取資料庫有關的例外：" + e.getMessage());
			e.printStackTrace();
			return;
		}

		// 定義新建Product表格的SQL命令
//		setString = "SET FOREIGN_KEY_CHECKS=0; ";
//		dropString = "DROP Table Product ";
//		setString2 = "SET FOREIGN_KEY_CHECKS=1; ";
//		try {
////			 執行刪除Product表格的SQL命令
//			stmt.executeUpdate(setString);
//			stmt.executeUpdate(dropString);
//			stmt.executeUpdate(setString2);
//			// 印出執行成功的訊息
//			System.out.println("Product表格刪除成功");
//		} catch (SQLException e) {
//			System.err.println("刪除Product表格時發生例外: " + e.getMessage());
//		}
		// 定義新建Product表格的SQL命令
		createString = "Create Table Product " 
				+ "(PD_ID  INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY ,  "
				+ " PD_Number   VARCHAR(10) NOT NULL, "
				+ " PD_Name     VARCHAR(30) NOT NULL, "
				+ " PD_Stock    INT(5) NOT NULL, " 
				+ " PD_Desc     VARCHAR(30) NOT NULL, "
				+ " PD_Price    VARCHAR(30) NOT NULL , " 
				+ " PD_Pic      VARCHAR(50) NOT NULL, "
				+ " PD_Software  VARCHAR(50), " 
				+ " PD_SoftDesc    TEXT ," 
				+ " PD_Image  Blob "
				+ " ) CHARACTER SET utf8 COLLATE utf8_general_ci";
		try {
			// 執行新建eBook表格的SQL命令
//			stmt.executeUpdate(createString);
			// 印出執行成功的訊息
			System.out.println("Product表格產生成功");
			// 定義新增書籍記錄的SQL命令
			sql = "insert into Product "
					+ " (PD_Number,  PD_Name,  PD_Stock, PD_Desc, PD_Price, PD_Pic, PD_Software, PD_SoftDesc, PD_Image) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			// 建立新增書籍記錄的PreparedStatement物件
			pstmt = con.prepareStatement(sql);
			// 讀取eBook表格的初始資料，準備新增到eBook表格內
			BufferedReader br = new BufferedReader(new FileReader("src/main/webapp/data/product.dat"));
			while ((line = br.readLine()) != null) {
				// 去除 UTF8_BOM
				if (line.startsWith(UTF8_BOM)) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				pstmt.setString(1, token[0]);
				pstmt.setString(2, token[1]);
				pstmt.setString(3, token[2].trim());
				pstmt.setString(4, token[3].trim());
				pstmt.setString(5, token[4].trim());
				pstmt.setString(6, token[5].trim());
				pstmt.setString(7, token[6].trim());
				pstmt.setString(8, token[7].trim());
				// 讀取圖片檔
				File aFile = new File("src/main/webapp/images/petPic/" + token[5].trim());
				long size = aFile.length();
				InputStream is = new FileInputStream(aFile);
				// 設定Image欄位
				pstmt.setBlob(9, is, size);
				// 執行新增Product表格之紀錄的SQL命令
				int r = pstmt.executeUpdate();
				System.out.println("新增一筆Product紀錄是否成功=" + r);
			}
			// 印出資料新增成功的訊息
			System.out.println("product資料新增成功");
			
		} catch (SQLException e) {
			System.err.println("新建Product表格時發生SQL例外: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("新建Product表格時發生IO例外: " + e.getMessage());
		}

	}
}