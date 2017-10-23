package _07_aboutUs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import _00_init.GlobalService;
import _00_init.bean.JoinCowerker_Bean;

public class JoinCoworker_DAO {
	public Map<String,JoinCowerker_Bean> getSelectAll() throws NamingException, SQLException {
		String sql = "select * from vacancies";
		Map<String,JoinCowerker_Bean> jb_array = new HashMap<>();
		//=======================連線物件========================
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JoinCowerker_Bean jb = null;
	
		//=======================連線字串========================
		try {
			conn = ds.getConnection();
			System.out.println("sql = " +sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
		//=======================把撈到的會員資料放到mb裡========================
			while(rs.next()) {
				int id = rs.getInt("v_id");
				String content = rs.getString("v_content");
				String time = rs.getString("v_time");
				String locate = rs.getString("v_locate");
				String describe = rs.getString("v_describe");
				String salary = rs.getString("v_salary");
				String name = rs.getString("v_name");
				String welfare = rs.getString("v_welfare");
				jb = new JoinCowerker_Bean(content, time, locate, describe, salary, name, welfare);
				jb_array.put(name, jb);
				System.out.println(jb.getName());
			}
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
		return jb_array;
	}
}
