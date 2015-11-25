package com.htf.fmusic.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author HTFeeds
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

	protected static final long serialVersionUID = 1L;
	
	protected static final int MAX_LENGTH_USER = 64;
	protected static final int MAX_LENGTH_PASSWORD = 100;
	protected static final int MAX_LENGTH_EMAIL = 100;
	protected static final int MAX_LENGTH_ROLE = 15;

	@CreatedBy
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "created_by_user_id")
	protected User createdByUser;

	@LastModifiedBy
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "modified_by_user_id")
	protected User modifiedByUser;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_time")
	protected Date creationTime;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_time")
	protected Date modificationTime;

	@Version
	protected long version;

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public User getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(User modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
