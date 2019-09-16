package com.hokumus.jsf.model;

import java.sql.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	
	private String creater;
	private Date OpTime;
	private String updater;
	private String deleter;
	
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getOpTime() {
		return OpTime;
	}
	public void setOpTime(Date opTime) {
		OpTime = opTime;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public String getDeleter() {
		return deleter;
	}
	public void setDeleter(String deleter) {
		this.deleter = deleter;
	}
	

}
