package _06_query.model;

import java.sql.SQLException;

import _00_init.bean.Member_Bean;

public interface Update_DAO_Interface {
	public int updateMember(Member_Bean mb) throws SQLException ;
//	public boolean idExists(String id) throws IOException;

}
