package com.zhibo.mainTain.domain;

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
@Table(name = "xd_customer_type")
public class CustomerType extends BaseDomain{

	private Long id;                             
	private Long customer_id;          // 对应用户id                 
	private Long chat_time;            // 聊天间隔单位秒               
	private Long video_time;           // 总视频时间 单位分钟         
	private Long used_video_time;      // 已用视频时间 单位分钟  
	private Integer is_chat;           // 是否禁言 0未禁言，1已禁言  DEFAULT：0          
	private Integer is_scrol;          // 是否滚动，0不可滚动，1可滚动         DEFAULT：0 
	private Integer is_top;            // 是否置顶 0不可，1可          DEFAULT：0           
	private Integer is_font;           // 是否字体 0 不可，1可         DEFAULT：0             
	private Integer is_valid;          // 是否审核 0 不可，1 可        DEFAULT：0  
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="customer_id")
	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	@Column(name="chat_time")
	public Long getChat_time() {
		return chat_time;
	}

	public void setChat_time(Long chat_time) {
		this.chat_time = chat_time;
	}
	@Column(name="video_time")
	public Long getVideo_time() {
		return video_time;
	}

	public void setVideo_time(Long video_time) {
		this.video_time = video_time;
	}
	@Column(name="used_video_time")
	public Long getUsed_video_time() {
		return used_video_time;
	}

	public void setUsed_video_time(Long used_video_time) {
		this.used_video_time = used_video_time;
	}
	@Column(name="is_chat")
	public Integer getIs_chat() {
		return is_chat;
	}

	public void setIs_chat(Integer is_chat) {
		this.is_chat = is_chat;
	}
	@Column(name="is_scrol")
	public Integer getIs_scrol() {
		return is_scrol;
	}

	public void setIs_scrol(Integer is_scrol) {
		this.is_scrol = is_scrol;
	}
	@Column(name="is_top")
	public Integer getIs_top() {
		return is_top;
	}

	public void setIs_top(Integer is_top) {
		this.is_top = is_top;
	}
	@Column(name="is_font")
	public Integer getIs_font() {
		return is_font;
	}

	public void setIs_font(Integer is_font) {
		this.is_font = is_font;
	}
	@Column(name="is_valid")
	public Integer getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
	}

	
}
