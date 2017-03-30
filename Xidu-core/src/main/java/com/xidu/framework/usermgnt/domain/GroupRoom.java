package com.xidu.framework.usermgnt.domain;

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
@Table(name = "tr_group_room")
@AttributeOverrides({
		@AttributeOverride(name = "createDate", column = @Column(name = "CREATE_DATE", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
		@AttributeOverride(name = "lastUpdateDate", column = @Column(name = "LAST_UPDATE_DATE", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")),
		@AttributeOverride(name = "lastUpdateBy", column = @Column(name = "LAST_UPDATE_BY", nullable = false, columnDefinition = "number(18) default 0")),
		@AttributeOverride(name = "createBy", column = @Column(name = "CREATE_BY", nullable = false, columnDefinition = "number(18) default 0")) })
public class GroupRoom extends BaseDomain {

	private String group;
	private String room ;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="group_id")
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Column(name="room_id")
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}


	

}
