package com.tj.academy.dto;

import java.sql.Date;

public class OrdersDto {
	private int oNo;
	private String mId;
	private String lCode;
	private String oTel;
	private Date oDate;
	private String lName;
	private String lTeacherName;
	private String lDate;
	private Date startDate;
	private Date endDate;
	public OrdersDto() {}
	public OrdersDto(int oNo, String mId, String lCode, String oTel, Date oDate, String lName, String lTeacherName,
			String lDate, Date startDate, Date endDate) {
		this.oNo = oNo;
		this.mId = mId;
		this.lCode = lCode;
		this.oTel = oTel;
		this.oDate = oDate;
		this.lName = lName;
		this.lTeacherName = lTeacherName;
		this.lDate = lDate;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public int getoNo() {
		return oNo;
	}
	public void setoNo(int oNo) {
		this.oNo = oNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getlCode() {
		return lCode;
	}
	public void setlCode(String lCode) {
		this.lCode = lCode;
	}
	public String getoTel() {
		return oTel;
	}
	public void setoTel(String oTel) {
		this.oTel = oTel;
	}
	public Date getoDate() {
		return oDate;
	}
	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getlTeacherName() {
		return lTeacherName;
	}
	public void setlTeacherName(String lTeacherName) {
		this.lTeacherName = lTeacherName;
	}
	public String getlDate() {
		return lDate;
	}
	public void setlDate(String lDate) {
		this.lDate = lDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "OrdersDto [oNo=" + oNo + ", mId=" + mId + ", lCode=" + lCode + ", oTel=" + oTel + ", oDate=" + oDate
				+ ", lName=" + lName + ", lTeacherName=" + lTeacherName + ", lDate=" + lDate + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
}
