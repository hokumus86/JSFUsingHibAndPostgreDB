package com.hokumus.jsf.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hokumus.jsf.dao.pr.MemberDAO;
import com.hokumus.jsf.model.Member;

@ManagedBean(name = "indexmbean")
@SessionScoped
public class IndexMBean {

	public IndexMBean() {
		System.out.println("cons" + msg);
	}

	private String username;
	private String pasword;
	private String name;
	private String msg = "Merhaba Kullanýcý";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void islem() {
		System.out.println(name + " " + username + " " + pasword);
		Member kullanici = new  Member();
		kullanici.setName(name);
		kullanici.setPasword(pasword);
		kullanici.setUsername(username);
		MemberDAO dao = new MemberDAO();
		dao.kaydet(kullanici);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
