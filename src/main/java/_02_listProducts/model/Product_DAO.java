package _02_listProducts.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import _00_init.GlobalService;

public class Product_DAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private DataSource ds = null;
	private int totalPages = -1;// 查詢單筆商品會用到此代號
	private int pageNo = 0;
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 每頁三筆
	private int productId = 0;
	public Product_DAO() throws ServletException, IOException, NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
	}

	

	public int getTotalPages() throws SQLException {
		// 計算總共有幾頁
		if (totalPages == -1) {
			// 注意下一列的double型態轉換
			totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		}
		return totalPages;
	}

	public Collection<Product_Bean> getPageProduct() throws SQLException {
		PreparedStatement pStmt = null;
		Connection connection = null;
		ResultSet rs = null;
		Collection<Product_Bean> coll = new ArrayList<Product_Bean>();
		try {
			String queryPageString = "SELECT * from product " + " limit ?, ?";
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryPageString);
			int startRecordNo = (pageNo - 1) * recordsPerPage;
			// int recordsPerPage = (pageNo) * recordsPerPage;
			// PreparedStatement物件內所有的問號都要有值，否則執行pStmt.executeQuery()
			// 或pStmt.executeUpdate()時程式一定會掛掉。
			System.out.println("queryPageString==>" + queryPageString);
			pStmt.setInt(1, startRecordNo);
			pStmt.setInt(2, recordsPerPage);
			rs = pStmt.executeQuery();
			// + " b.BOOKID, b.TITLE, b.author, b.PRICE, b.discount, b.companyID,
			// b.fileName, b.bookNo, bc.name FROM eBook b JOIN eBookCompany bc ON
			// b.companyID = bc.id "
			// + " limit ?, ?";
			// 如果ResultSet內含有未讀取的紀錄
			while (rs.next()) {
				// 建立一個新的BookBean物件
				Product_Bean bean = new Product_Bean();
				// 將此紀錄內的資料放入BookBean物件
				bean.setProductID(rs.getInt(1));
				bean.setName(rs.getString(3));
				bean.setNumber(rs.getInt(2));
				bean.setPrice(rs.getInt(6));
				bean.setDesc(rs.getString(5));
//				bean.setPicture(rs.getString(5);　
				// 最後將BookBean物件放入大的容器內
				coll.add(bean);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return coll;
	}

	// 計算紀錄總筆數
	public int getRecordCounts() throws SQLException {
		String sql = "SELECT count(*) FROM product";
		PreparedStatement pStmt = null;
		Connection connection = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(sql);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public void setProductId(int ProductId) {
		this.productId = ProductId;
	}

	public Product_Bean getProduct() throws SQLException {
		Product_Bean bean = null;
		String queryProductString = "SELECT *  " + "from product  where PD_ID = ?";
		PreparedStatement pStmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryProductString);
			pStmt.setInt(1, productId);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				bean = new Product_Bean();
				bean.setProductID(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setNumber(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setPicture(rs.getString(5));

			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	public Collection<Product_Bean> getAllProducts() throws SQLException {
		Collection<Product_Bean> coll = new ArrayList<Product_Bean>();
		PreparedStatement pStmt = null;
		String queryAllProductsString = "SELECT * FROM PRODUCT";
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryAllProductsString);
			rs = pStmt.executeQuery();
			while (rs.next()) {
				Product_Bean bean = new Product_Bean();
				bean.setProductID(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setNumber(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setPicture(rs.getString(5));
				bean.setDesc(rs.getString(6));
				coll.add(bean);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return coll;
	}

	public int updateBook(Product_Bean bean, InputStream is, long sizeInBytes) throws SQLException {
		int n = 0;
		PreparedStatement pStmt = null;		
		Connection connection = null;
		String updateString = "UPDATE product SET pd_name=?,  PD_number=?,  PD_price=?,  "
				+ " PD_pic=?,  CoverImage = ?, PD_Desc = ?, WHERE PD_ID = ?";
		if (sizeInBytes == -1) { // 不修改圖片
			n = updateProduct(bean);
			return n;
		}
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(updateString);
			pStmt.clearParameters();
			pStmt.setString(1, bean.getName());
			pStmt.setInt(2, bean.getNumber());
			pStmt.setInt(3, bean.getPrice());
			pStmt.setString(4, bean.getPicture());
			pStmt.setBlob(5, bean.getImage());
			pStmt.setString(6, bean.getDesc());
			pStmt.setBinaryStream(7, is, sizeInBytes);
			pStmt.setInt(8, bean.getProductID());
			n = pStmt.executeUpdate();	
		} finally {

			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return n;
	}

	public int updateProduct(Product_Bean bean) throws SQLException {
		int n = 0;
		String updateString = "UPDATE Product SET pd_name=?, pd_number=?, pd_price=?, pd_desc=?  WHERE pd_ID = ?";
		PreparedStatement pStmt = null;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(updateString);
			pStmt.clearParameters();
			pStmt.setString(1, bean.getName());
			pStmt.setInt(2, bean.getNumber());
			pStmt.setInt(3, bean.getPrice());
			pStmt.setString(4, bean.getDesc());
			pStmt.setInt(5, bean.getProductID());
			n = pStmt.executeUpdate();
		} finally {
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return n;
	}

	// 依bookID來查詢單筆記錄
	public Product_Bean queryProduct(int productID) throws SQLException {
		Product_Bean bean = null;
		PreparedStatement pStmt = null;
		Connection connection = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM product where pd_ID = ?";
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(queryString);
			pStmt.setInt(1, productID);
			rs = pStmt.executeQuery();
			if (rs.next()) {
				bean = new Product_Bean();
				bean.setProductID(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setNumber(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setDesc(rs.getString(5));
				bean.setPicture(rs.getString(6));
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	// 依ProductID來刪除單筆記錄
	public int deleteProduct(int no) throws SQLException {
		int n = 0;

		PreparedStatement pStmt = null;
		Connection connection = null;
		String deleteString = "DELETE FROM product WHERE pd_ID = ?";
		try {
			connection = ds.getConnection();
			pStmt = connection.prepareStatement(deleteString);
			pStmt.setInt(1, no);
			n = pStmt.executeUpdate();
		} finally {
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return n;
	}
	// 新增一筆記錄
		public int insertProduct(Product_Bean bean, InputStream is, long size) 
		                                   throws SQLException {
			int n = 0;
			Connection connection = null;
			PreparedStatement pStmt = null;
			try {
				String inserteProduct = "insert into product "
						+ " (PD_name, PD_number, PD_price, PD_desc, "
						+ " PD_pic,  CoverImage) "
						+ " values (?, ?, ?, ?, ?, ?)";
 
				connection = ds.getConnection();
				pStmt = connection.prepareStatement(inserteProduct);
				pStmt.setString(1, bean.getName());
				pStmt.setInt(2, bean.getNumber());
				pStmt.setInt(3, bean.getPrice());
				pStmt.setString(4, bean.getDesc());
				pStmt.setString(5,  bean.getPicture());
				pStmt.setBinaryStream(6, is, size);
				n = pStmt.executeUpdate();				
			} finally {
				if (pStmt != null) {
					try {
						pStmt.close();
					} catch(SQLException e){
					   e.printStackTrace();
					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
			return n;
		}
		public void createProductTable() throws SQLException {
			String dropString = "Drop Table Product ";
			String createString = "Create Table Product "
					+ "(PD_ID  INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY , "
					+ " PD_Number   VARCHAR(10) NOT NULL, " 
					+ " PD_Name     VARCHAR(30) NOT NULL, "
					+ " PD_Price    VARCHAR(30) NOT NULL , " 
					+ " PD_Desc     VARCHAR(100) NOT NULL, "
					+ " PD_Stock    INT(5) NOT NULL, " 
					+ " PD_Pic  	varchar(50), "
					+ " PD_Software  VARCHAR(50), "
					+ " PD_SoftDesc    TEXT ,"
					+ " CoverImage   	PD_Pic " + " ) ";
	
			PreparedStatement pStmt;
			Connection connection = ds.getConnection();
			try {
				pStmt = connection.prepareStatement(dropString);
				pStmt.executeUpdate();
				System.out.println("表格 eBook 刪除成功");
			} catch (SQLException e) {
				System.err.println("表格 eBook 刪除失敗:" + e.getMessage());
			}
			try {
				pStmt = connection.prepareStatement(createString);
				pStmt.executeUpdate();
				System.out.println("表格 eBook 新建成功");
			} catch (SQLException e) {
				System.err.println("表格 eBook 新建失敗:" + e.getMessage());
			}
			if (connection != null)
				connection.close();
			return;
		}
}
