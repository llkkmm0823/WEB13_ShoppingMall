package com.ezen.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.utill.Dbman;

public class OrderDao {

	
	private OrderDao() {}
	private static OrderDao itc = new OrderDao();
	public static OrderDao getInstance() { return itc; }

	Connection con=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	
	public int insertOrder(ArrayList<CartVO> list, String id) {
		int oseq=0;
		con = Dbman.getConnection();

		//1. 주문번호 ( 시퀀스 자동입력)와 구매자 아이디로 orders 테이블에 레코드 추가
			String sql = "insert into orders(oseq,id)values(orders_seq.nextVal, ?) ";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,  id);
				pstmt.executeUpdate();
				
			//2. Orders 테이블에 시퀀스로 입력된 가장 마지막 (방금 추가한) 주문 번호(max_oseq) 조회
				sql = "select max(oseq)as moseq from orders";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if( rs.next() ) oseq = rs.getInt("moseq");
			
			//3. list의 카트목록들을 orders에서 얻은 max_osq와 함께 order_detail에 추가
				sql = "insert into order_detail(odseq,oseq,pseq,quantity) values(order_detail_seq.nextVal,?,?,?)";
				
				for(CartVO cvo : list) {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, oseq);
					pstmt.setInt(2, cvo.getPseq());
					pstmt.setInt(3, cvo.getQuantity());
					pstmt.executeUpdate();
					pstmt.close();
				}
			
			//4. order_detail에 추가된 카트 내용은 cart 테이블에서 처리되었으므로 삭제 또는 result를 2로 변경 또는 삭제
				sql="delete from cart where cseq=?";
				//sql="update cart set result='2' where cseq=?"; 
				for(CartVO cvo : list) {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, cvo.getCseq());
					pstmt.executeUpdate();
					pstmt.close();
				}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		return oseq;
	}
	
	public ArrayList<OrderVO> listOrderByOseq(int oseq){
		ArrayList<OrderVO> list= new ArrayList<OrderVO>();
		String sql = "select*from order_view where oseq=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  oseq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				ovo.setOdseq(  rs.getInt("odseq") );
				ovo.setId(  rs.getString("id") );
				ovo.setMname(  rs.getString("mname") );
				ovo.setPname(  rs.getString("pname") );
				ovo.setAddress1(  rs.getString("address1") );
				ovo.setPhone(  rs.getString("phone") );
				ovo.setPrice2(  rs.getInt("price2") );
				ovo.setQuantity(  rs.getInt("quantity") );
				ovo.setIndate(  rs.getTimestamp("indate") );
				list.add(ovo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		return list;

	}

	public int insertOrderOne(int pseq, int quantity, String id) {
		int oseq=0;
		con = Dbman.getConnection();
		String sql="insert into orders(oseq,id) values(orders_seq.nextVal,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  id);
			pstmt.executeUpdate();
			sql="select max(oseq) as moseq from orders";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) oseq = rs.getInt("moseq");
			sql="insert into order_detail(odseq,oseq,pseq,quantity) values(order_detail_seq.nextVal,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  oseq);
			pstmt.setInt(1,  pseq);
			pstmt.setInt(1,  quantity);
			pstmt.executeUpdate();
	} catch (SQLException e) { e.printStackTrace();
	} finally { Dbman.close(con, pstmt, rs);
	}		
		return oseq;
	}
	
}

