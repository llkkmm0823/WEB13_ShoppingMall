package com.ezen.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.shop.dto.AdminVO;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.utill.Dbman;
import com.ezen.shop.utill.Paging;

public class AdminDao {

	private AdminDao() {}
	private static AdminDao itc = new AdminDao();
	public static AdminDao getInstance() { return itc; }

	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public AdminVO workerCheck(String workId) {
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

	public ArrayList<ProductVO> adminProductList(Paging paging, String key) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		con = Dbman.getConnection();
		String sql="select * from ( "
				+ " select * from ( "
				+ " select rownum as rn,p.*from "
				+ " ((select*from product where name like'%'||?||'%' order by pseq desc) p)"
				+ " ) where rn>=? "
				+ " ) where rn<=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(  rs.getInt("pseq") );
				pvo.setIndate(  rs.getTimestamp("indate") );
				pvo.setName(  rs.getString("name") );
				pvo.setPrice1(  rs.getInt("price1") ); // 원가
				pvo.setPrice2(  rs.getInt("price2") ); // 판매가
				pvo.setPrice3(  rs.getInt("price3") ); // 마진(판매이익)
				pvo.setImage(  rs.getString("image") );
				pvo.setUseyn(  rs.getString("useyn") );
				pvo.setBestyn(  rs.getString("bestyn") );
				list.add(pvo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		return list;
	}

	public int getAllcount(String key) {
		int count=0;
		String sql="select count(*) as cnt from product where name like'%'||?||'%'";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt("cnt");
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		return count;
	}

	public void insertProduct(ProductVO pvo) {
		String sql = "insert into product(pseq, kind,name,price1,price2,price3,content,image) "
				+ " values( product_seq.nextVal, ? , ? , ? , ? , ? , ? , ? )";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind());
			pstmt.setString(2, pvo.getName());
			pstmt.setInt(3, pvo.getPrice1());
			pstmt.setInt(4, pvo.getPrice2());
			pstmt.setInt(5, pvo.getPrice3());
			pstmt.setString(6, pvo.getContent());
			pstmt.setString(7, pvo.getImage());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
	}

	public void updateProduct(ProductVO pvo) {
		String sql = "update product set kind=?, useyn=?,name=?,price1=?,price2=?,price3=?, "
				+ " content=?,image=?,bestyn=? where pseq=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind());
			pstmt.setString(2, pvo.getUseyn());
			pstmt.setString(3, pvo.getName());
			pstmt.setInt(4, pvo.getPrice1());
			pstmt.setInt(5, pvo.getPrice2());
			pstmt.setInt(6, pvo.getPrice3());
			pstmt.setString(7, pvo.getContent());
			pstmt.setString(8, pvo.getImage());
			pstmt.setString(9, pvo.getBestyn());
			pstmt.setInt(10, pvo.getPseq());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
	}
	}
}
