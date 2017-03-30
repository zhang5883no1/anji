package com.zhibo.reporting.domain;

import java.io.Serializable;

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
@Table(name = "xd_upload")
public class DeviationReportDomain extends BaseDomain implements Serializable{


		private static final long serialVersionUID = 1L;

		private String src;
		private String name;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Column(name="src")
		public String getSrc() {
			return src;
		}

		public void setSrc(String src) {
			this.src = src;
		}

		@Column(name="name")
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public DeviationReportDomain(String src, String name) {
			super();
			this.src = src;
			this.name = name;
		}

		
}
