package com.dou.kylin.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import java.sql.Timestamp;


/**
 * 用户entity
 * @author ty
 * @date 2015年1月13日
 */

public class User implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;
	private String name;
	private String password;
	private String plainPassword;
	private String salt;
	private Timestamp birthday;
	private Short gender;
	private String email;
	private String phone;
	private String icon;
	private Timestamp createDate;
	private String state;
	private String description;
	private Integer loginCount;
	private Timestamp previousVisit;
	private Timestamp lastVisit;
	private String delFlag;

	// Constructors

	/** default constructor */
	public User() {
	}
	
	public User(Integer id) {
		this.id=id;
	}

	/** minimal constructor */
	public User(String userName, String name, String password) {
		this.userName = userName;
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public User(String userName, String name, String password, String salt,
				Timestamp birthday, Short gender, String email, String phone,
				String icon, Timestamp createDate, String state, String description,
				Integer loginCount, Timestamp previousVisit, Timestamp lastVisit,
				String delFlag) {
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.salt = salt;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.icon = icon;
		this.createDate = createDate;
		this.state = state;
		this.description=description;
		this.loginCount = loginCount;
		this.previousVisit = previousVisit;
		this.lastVisit = lastVisit;
		this.delFlag = delFlag;

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Short getGender() {
		return this.gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Timestamp getPreviousVisit() {
		return this.previousVisit;
	}

	public void setPreviousVisit(Timestamp previousVisit) {
		this.previousVisit = previousVisit;
	}

	public Timestamp getLastVisit() {
		return this.lastVisit;
	}

	public void setLastVisit(Timestamp lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	// 不持久化到数据库，也不显示在Restful接口的属性.
	@Transient
	@JsonIgnore
	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	@Transient
	public String getCredentialsSalt() {
		return userName + salt;
	}

}