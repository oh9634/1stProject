package com.tj.academy.dto;

import java.sql.Date;

public class BoardDto {
	private int fNo;
	private String mId;
	private String mName;
	private String aId;
	private String aName;
	private String fTitle;
	private String fCategory;
	private String fContent;
	private String fFilename;
	private Date fRdate;
	private int fHit;
	private int fGroup;
	private int fStep;
	private int fIndent;
	private int fImportance;
	private String fIp;
	public BoardDto() {}
	public BoardDto(int fNo, String mId, String mName, String aId, String aName, String fTitle, String fCategory,
			String fContent, String fFilename, Date fRdate, int fHit, int fGroup, int fStep, int fIndent,
			int fImportance, String fIp) {
		this.fNo = fNo;
		this.mId = mId;
		this.mName = mName;
		this.aId = aId;
		this.aName = aName;
		this.fTitle = fTitle;
		this.fCategory = fCategory;
		this.fContent = fContent;
		this.fFilename = fFilename;
		this.fRdate = fRdate;
		this.fHit = fHit;
		this.fGroup = fGroup;
		this.fStep = fStep;
		this.fIndent = fIndent;
		this.fImportance = fImportance;
		this.fIp = fIp;
	}
	
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfCategory() {
		return fCategory;
	}
	public void setfCategory(String fCategory) {
		this.fCategory = fCategory;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}
	public String getfFilename() {
		return fFilename;
	}
	public void setfFilename(String fFilename) {
		this.fFilename = fFilename;
	}
	public Date getfRdate() {
		return fRdate;
	}
	public void setfRdate(Date fRdate) {
		this.fRdate = fRdate;
	}
	public int getfHit() {
		return fHit;
	}
	public void setfHit(int fHit) {
		this.fHit = fHit;
	}
	public int getfGroup() {
		return fGroup;
	}
	public void setfGroup(int fGroup) {
		this.fGroup = fGroup;
	}
	public int getfStep() {
		return fStep;
	}
	public void setfStep(int fStep) {
		this.fStep = fStep;
	}
	public int getfIndent() {
		return fIndent;
	}
	public void setfIndent(int fIndent) {
		this.fIndent = fIndent;
	}
	public int getfImportance() {
		return fImportance;
	}
	public void setfImportance(int fImportance) {
		this.fImportance = fImportance;
	}
	public String getfIp() {
		return fIp;
	}
	public void setfIp(String fIp) {
		this.fIp = fIp;
	}
	@Override
	public String toString() {
		return "BoardDto [fNo=" + fNo + ", mId=" + mId + ", mName=" + mName + ", aId=" + aId + ", aName=" + aName
				+ ", fTitle=" + fTitle + ", fCategory=" + fCategory + ", fContent=" + fContent + ", fFilename="
				+ fFilename + ", fRdate=" + fRdate + ", fHit=" + fHit + ", fGroup=" + fGroup + ", fStep=" + fStep
				+ ", fIndent=" + fIndent + ", fImportance=" + fImportance + ", fIp=" + fIp + "]";
	}
	
}
