package com.zhibo.mainTain.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.xidu.framework.common.domain.BaseDomain;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "xd_customer")
public class Chats extends BaseDomain implements Serializable {

	private Long id;
	private String content; // 内容
	private String userName; // 发言人昵称
	private Long userId; // 发言人id
	private Date date; // 发言时间
	private String toUser; // 接收方名称
	private Integer valid; // 是否审核 0没审核 1 已审核 DEFAULT 0
	private Integer isRobot; // 发言是否是机器人0不是 1是 DEFAULT 0
	private String roomNo; // 房间号
	private String validUser; // 审核人
	private String faceImg;
	private Integer type; // 发言类型 0群聊，1私聊，2置顶，3滚动 DEFAULT 0

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="userId")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="toUser")
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	@Column(name="valid")
	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	@Column(name="isRobot")
	public Integer getIsRobot() {
		return isRobot;
	}

	public void setIsRobot(Integer isRobot) {
		this.isRobot = isRobot;
	}

	@Column(name="roomNo")
	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	@Column(name="validUser")
	public String getValidUser() {
		return validUser;
	}

	public void setValidUser(String validUser) {
		this.validUser = validUser;
	}

	@Column(name="faceImg")
	public String getFaceImg() {
		return faceImg;
	}

	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}

	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
