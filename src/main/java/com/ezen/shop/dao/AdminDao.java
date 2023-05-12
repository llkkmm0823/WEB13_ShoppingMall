package com.ezen.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ezen.shop.dto.AdminVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.utill.Dbman;

public class AdminDao {
	
	private AdminDao() {}
	private static AdminDao itc = new AdminDao();
	public static AdminDao getInstance() { return itc; }

	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public AdminVO getMember(String workId) {
		AdminVO avo=null;
		String sql="select*from worker where id=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  workId);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				avo = new AdminVO();
				avo.setId( rs.getString("id") );
				avo.setPwd( rs.getString("pwd") );
				avo.setName( rs.getString("name") );
				avo.setPhone( rs.getString("phone") );
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		return avo;
	}
	
	

}
