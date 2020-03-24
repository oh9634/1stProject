package com.tj.academy.dto;

import java.sql.Date;

public class LectureDto {
	private String lCode;
	private String lName;
	private String lDate;
	private Date startDate;
	private Date endDate;
	private String lTeacherName;
	private int lPrice;
	private int lNum;
	private int lStock;
	public LectureDto() {}
	public LectureDto(String lCode, String lName, String lDate, Date startDate, Date endDate, String lTeacherName,
			int lPrice, int lNum, int lStock) {
		this.lCode = lCode;
		this.lName = lName;
		this.lDate = lDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lTeacherName = lTeacherName;
		this.lPrice = lPrice;
		this.lNum = lNum;
		this.lStock = lStock;
	}
	public String getlCode() {
		return lCode;
	}
	public void setlCode(String lCode) {
		this.lCode = lCode;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
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
	public String getlTeacherName() {
		return lTeacherName;
	}
	public void setlTeacherName(String lTeacherName) {
		this.lTeacherName = lTeacherName;
	}
	public int getlPrice() {
		return lPrice;
	}
	public void setlPrice(int lPrice) {
		this.lPrice = lPrice;
	}
	public int getlNum() {
		return lNum;
	}
	public void setlNum(int lNum) {
		this.lNum = lNum;
	}
	public int getlStock() {
		return lStock;
	}
	public void setlStock(int lStock) {
		this.lStock = lStock;
	}
	@Override
	public String toString() {
		return "LectureDto [lCode=" + lCode + ", lName=" + lName + ", lDate=" + lDate + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", lTeacherName=" + lTeacherName + ", lPrice=" + lPrice + ", lNum=" + lNum
				+ ", lStock=" + lStock + "]";
	}
	
}
