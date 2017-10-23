package _05_respond.model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _05_respond.model.MemberBean;
import _05_respond.model.respond_Service;

public class respond_DAO implements respond_DAO_Interface {
	@Override
	public int saveMember(MemberBean mb)
			throws SQLException, AddressException, IOException, MessagingException, NamingException {
		int r = 0;
		String sql = "INSERT INTO Respond " + " (RP_QuestionType, RP_Name, RP_Mail, RP_Message )"
				+ " values (?, ?, ?, ? )";
		// =======================連線物件========================
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, mb.getQuestion());
			pstmt.setString(2, mb.getName());
			pstmt.setString(3, mb.getEmail());
			pstmt.setString(4, mb.getDescription());
			r = pstmt.executeUpdate();

			return r;
		}
	}

	@Override
	public MemberBean selectOrdno(String ordno) throws NamingException, SQLException {
		String sql = "SELECT * FROM Orderform WHERE Ord_ID = ?";
		// =======================連線物件========================
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, ordno);
			try (ResultSet rs = pstmt.executeQuery()) {
				System.out.println("selectOrdno = " + ordno);

				// ==============把撈到的會員資料放到memberList裡=============
				while (rs.next()) {

					String ordno2 = rs.getString(1);
					MemberBean mb = new MemberBean(ordno2);
					respond_Service rs1 = new respond_Service();
					rs1.addNewMember(mb);
					System.out.println("memberList.add(mb) = " + sql);

					return mb;
				}
			}
		}
		return null;
	}

}
