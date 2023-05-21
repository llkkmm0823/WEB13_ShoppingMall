package com.ezen.shop.dto;

import java.sql.Timestamp;

public class CartVO {

	private Integer cseq;
	private String id;
	private Integer pseq;
	private String mname;
	private String pname;
	private Integer quantity;
	private Integer price2;
	private Timestamp indate;
	private String result;

	
// integer와  int의 차이 
	
// int : 자료형(primitive type)
// 산술 연산 가능함
// null로 초기화 불가
	
// Integer : 래퍼 클래스 (Wrapper class)
// Unboxing하지 않을 시 산술 연산 불가능함
// null값 처리 가능

	public Integer getCseq() {
		return cseq;
	}
	public void setCseq(Integer cseq) {
		this.cseq = cseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPseq() {
		return pseq;
	}
	public void setPseq(Integer pseq) {
		this.pseq = pseq;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice2() {
		return price2;
	}
	public void setPrice2(Integer price2) {
		this.price2 = price2;
	}
	public Timestamp getIndate() {
		return indate;
	}
	public void setIndate(Timestamp indate) {
		this.indate = indate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
