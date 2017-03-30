package com.zhibo.mainTain.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
@Table(name = "xd_qq_manager")
@AttributeOverrides({
        @AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE", 
            nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
        @AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")),
        @AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY", 
            nullable = false, columnDefinition = "number(18) default 0")),
    		@AttributeOverride(name = "deleteFlag", column = @Column(name = "DELETE_FLAG", nullable = false, columnDefinition = "number(18) default 1")) })
public class QQFenPei extends BaseDomain implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private	String QQ1;
	private String name1;
	private	String QQ2;
	private String name2;
	private	String QQ3;
	private String name3;
	private	String QQ4;
	private String name4;
	private	String QQ5;
	private String name5;
	private	String QQ6;
	private String name6;
	private String roomId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId(){
		return this.id;
	}
	public void setId(Long id){
		this.id = id;
	}
	
	@Column(name="name")
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	@Column(name="QQ1")
	public String getQQ1() {
		return QQ1;
	}
	public void setQQ1(String qQ1) {
		QQ1 = qQ1;
	}
	
	@Column(name="name1")
	public String getName1(){
		return this.name1;
	}
	public void setName1(String name1){
		this.name1 = name1;
	}
	
	@Column(name="QQ2")
	public String getQQ2() {
		return QQ2;
	}
	public void setQQ2(String qQ2) {
		QQ2 = qQ2;
	}
	
	@Column(name="name2")
	public String getName2(){
		return this.name2;
	}
	public void setName2(String name2){
		this.name2 = name2;
	}
	
	@Column(name="QQ3")
	public String getQQ3() {
		return QQ3;
	}
	public void setQQ3(String qQ3) {
		QQ3 = qQ3;
	}
	
	@Column(name="name3")
	public String getName3(){
		return this.name3;
	}
	public void setName3(String name3){
		this.name3 = name3;
	}
	
	@Column(name="QQ4")
	public String getQQ4() {
		return QQ4;
	}
	public void setQQ4(String qQ4) {
		QQ4 = qQ4;
	}
	
	@Column(name="name4")
	public String getName4(){
		return this.name4;
	}
	public void setName4(String name4){
		this.name4 = name4;
	}
	
	@Column(name="QQ5")
	public String getQQ5() {
		return QQ5;
	}
	public void setQQ5(String qQ5) {
		QQ5 = qQ5;
	}
	
	@Column(name="name5")
	public String getName5(){
		return this.name5;
	}
	public void setName5(String name5){
		this.name5 = name5;
	}
	
	@Column(name="QQ6")
	public String getQQ6() {
		return QQ6;
	}
	public void setQQ6(String qQ6) {
		QQ6 = qQ6;
	}
	
	@Column(name="name6")
	public String getName6(){
		return this.name6;
	}
	public void setName6(String name6){
		this.name6 = name6;
	}
	
	@Column(name="roomId")
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
}
