package _03_ShoppingCart.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;


import _00_init.GlobalService;
import _00_init.bean.Member_Bean;


public class Order_DAO {
	private static final long serialVersionUID = 1L;
	private String memberId = null;
	private DataSource ds = null;
	Statement stmt;
	Member_Bean mb = new Member_Bean();
	public Order_DAO() throws NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
	}

	public int insertOrder(Order_Bean ob) throws SQLException {
		int r = 0;
//		String sqlOrder = "Insert Into orderform "
//				+ " (cm_id, ord_id, Ord_TotalPrice, Ord_BuyerName, Ord_BuyerPhone,"
//				+ " Ord_BuyerEMail, Ord_BuyerAddress, Ord_ReceiverName, Ord_ReceiverPhone,"
//				+ " Ord_ReceiverAddress,Ord_OutDate, Ord_IfOut, Ord_ArriveDate, Ord_IfArrive, Ord_IfMember) "
//				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		String sqlOrder2 = "Insert Into orderform "
				+ " (cm_id,Ord_TotalPrice , Ord_Date, ord_Destination, Ord_BuyerName, Ord_BuyerPhone,"
				+ " Ord_BuyerEMail, Ord_BuyerAddress, Ord_ReceiverName, Ord_ReceiverPhone,"
				+ " Ord_ReceiverAddress,Ord_OutDate, Ord_IfOut, Ord_ArriveDate, Ord_IfArrive, Ord_IfMember) "
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet generatedKeys = null;
		PreparedStatement pStmt2 = null;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false); // 開啟JDBC Transaction
			
			pStmt = conn.prepareStatement(sqlOrder2, Statement.RETURN_GENERATED_KEYS);
			//Date Ord_Date = new Date(ob.getOrdDate().getTime());
//			java.sql.Date OrdDate = new java.sql.Date(ob.getOrdDate().getTime());
//			java.sql.Date OutDate = new java.sql.Date(ob.getOrdDate().getTime());
//			java.sql.Date ArriveDate = new java.sql.Date(ob.getOrdDate().getTime());
			java.sql.Timestamp OrdDate = new java.sql.Timestamp(ob.getOrdDate().getTime());
			java.sql.Timestamp OutDate = new java.sql.Timestamp(ob.getOutDate().getTime());
			java.sql.Timestamp ArriveDate = new java.sql.Timestamp(ob.getArriveDate().getTime());
			pStmt.setInt(1, ob.getcmId());
			pStmt.setInt(2, ob.getTotalPrice());
			pStmt.setString(4, ob.getOrd_Destination());
			pStmt.setString(5, ob.getBuyerName());
			pStmt.setString(6, ob.getBuyerPhone());
			pStmt.setString(7, ob.getBuyerEMail());
			pStmt.setString(8, ob.getBuyerAddress());
			pStmt.setString(9, ob.getReceiverName());
			pStmt.setString(10, ob.getReceiverPhone());
			pStmt.setString(11, ob.getReceiverAddress());
			pStmt.setTimestamp(12, OutDate);
			pStmt.setInt(13, ob.getIfOut());
			pStmt.setTimestamp(14, ArriveDate);
			pStmt.setInt(15, ob.getIfArrive());
			pStmt.setInt(16, ob.getIfMember());
			pStmt.setTimestamp(3, OrdDate);
			r = pStmt.executeUpdate();
			
			int id = 0;
			generatedKeys = pStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new SQLException(
						"Creating user failed, no generated key obtained.");
			}
			 System.out.println("oid: " + id);
			
			 String sqlItem = "Insert Into OrderDetails (Ord_ID, PD_ID, PD_NAME, OrdDetail_Quantity, unitPrice, subTotal) "
						+ " values(?, ?, ?, ?, ?, ?) ";
			 System.out.println("abc: " + "ABC");
				List<OrderDetail_Bean> items = ob.getItems();
				pStmt2 = conn.prepareStatement(sqlItem);
				int n=0;
				OrderDetail_Bean a = new OrderDetail_Bean();
				
				 
				for (OrderDetail_Bean oib : items) {
//	              下列四個敘述為交易測試而編寫	
//					n++;
//					if (n > 1) {
//						System.out.println("發生例外 n>2");
//						throw new SQLException("JDBC交易測試用");
//					}
					pStmt2.setInt(1, id);
					pStmt2.setInt(2, oib.getproductId());
					pStmt2.setString(3, oib.getProductName());
					pStmt2.setDouble(4, oib.getAmount());
					pStmt2.setDouble(5, oib.getUnitPrice());
					pStmt2.setDouble(6, oib.getSubTotal());
					
					pStmt2.executeUpdate();
					
					 System.out.println("orderNo: " + "ABC");
					pStmt2.clearParameters();
					
				}
			conn.commit();
			
		} catch (SQLException ex) {
			System.out.println("資料還原" + ex);
			if (conn != null)conn.rollback();
		} finally {

			if (pStmt != null) {
				pStmt.close();
			}
			 if (pStmt2 != null) {
			 pStmt2.close();
			 }
			if (conn != null)
				conn.setAutoCommit(true);
			if (conn != null) {
				conn.close();
			}
		}
		return r;
	}
	public Order_Bean getOrder(int orderNo) throws SQLException {
		String sqlOrder = "SELECT ord_Id, cm_id, Ord_TotalPrice, Ord_BuyerAddress, Ord_ReceiverName, Ord_receiverAddress, Ord_Date FROM orderform WHERE ord_id = ? ";
		String sqlOrderItems = "SELECT * FROM OrderDetails WHERE ord_id = ? ";
		                                                        //原本有order by
		List<OrderDetail_Bean> items = new ArrayList<OrderDetail_Bean>();
		Connection conn = null;
		PreparedStatement pStmt = null;
		PreparedStatement pStmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		Order_Bean ob = null;
		try {
			conn = ds.getConnection();
			pStmt = conn.prepareStatement(sqlOrder);
			pStmt.setInt(1, orderNo);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				ob = new Order_Bean();
				ob.setOrdId(rs.getInt(1));
				ob.setcmId(rs.getInt(2));
				ob.setTotalPrice(rs.getInt(3));
				ob.setBuyerAddress(rs.getString(4));
				ob.setReceiverName(rs.getString(5));
				ob.setReceiverAddress(rs.getString(6));
				ob.setOrdDate(rs.getDate(7));
			}
			if (ob == null) {
				throw new SQLException("資料庫邏輯錯誤：無此紀錄, 訂單編號=" + orderNo);
			} else {
				pStmt2 = conn.prepareStatement(sqlOrderItems);
				pStmt2.setInt(1, orderNo);
				rs2 = pStmt2.executeQuery();
				OrderDetail_Bean oib = null;
				while (rs2.next()) {
					oib = new OrderDetail_Bean();
					oib.setOrdId(rs2.getInt(1));
					oib.setproductId(rs2.getInt(2));
					oib.setProductName(rs2.getString(3));
					oib.setAmount(rs2.getInt(4));
					oib.setUnitPrice(rs2.getDouble(5));
					oib.setSubTotal(rs2.getDouble(6));
					items.add(oib);
				}
			}
			ob.setItems(items);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (pStmt2 != null) {
				pStmt2.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return ob;
	}

	public Collection<Order_Bean> getAllOrders() throws SQLException {
		Collection<Order_Bean> coll = new ArrayList<Order_Bean>();
		String sqlOrder = "SELECT ord_Id, cm_id, Ord_TotalPrice, Ord_BuyerAddress, Ord_ReceiverName,Ord_ReceiverPhone, Ord_ReceiverAddress, Ord_Destination, Ord_Date FROM orderform Order by Ord_Date desc, ord_id desc ";
		PreparedStatement pStmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Order_Bean ob = null;
		try {
			conn = ds.getConnection();
			pStmt = conn.prepareStatement(sqlOrder);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				ob = new Order_Bean();
				ob.setOrdId(rs.getInt(1));
				ob.setcmId(rs.getInt(2));
				ob.setTotalPrice(rs.getInt(3));
				ob.setBuyerAddress(rs.getString(4));
				ob.setReceiverName(rs.getString(5));
				ob.setReceiverPhone(rs.getString(6));
				ob.setReceiverAddress(rs.getString(7));
				ob.setOrd_Destination(rs.getString(8));
				ob.setOrdDate(rs.getDate(9));
			
				coll.add(ob);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		//System.out.println("Order_DAO   coll.size()=" + coll.size());
		return coll;
	}
	
	public Collection<Order_Bean> getMemberOrders() throws SQLException {
		Collection<Order_Bean> coll = new ArrayList<Order_Bean>();
		String sqlOrder = "SELECT ord_Id, cm_id, Ord_TotalPrice, Ord_BuyerAddress, Ord_ReceiverName, Ord_Destination, Ord_Date FROM orderform Order by Ord_Date desc where cm_id = ?";
		PreparedStatement pStmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Order_Bean ob = null;
		try {
			conn = ds.getConnection();
			pStmt = conn.prepareStatement(sqlOrder);
			pStmt.setString(1, memberId);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				ob = new Order_Bean();
				ob.setOrdId(rs.getInt(1));
				ob.setcmId(rs.getInt(2));
				ob.setTotalPrice(rs.getInt(3));
				ob.setBuyerAddress(rs.getString(4));
				ob.setReceiverName(rs.getString(5));
				ob.setOrd_Destination(rs.getString(6));
				ob.setOrdDate(rs.getDate(7));
				
				coll.add(ob);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pStmt != null) {
				pStmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return coll;
	}
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}