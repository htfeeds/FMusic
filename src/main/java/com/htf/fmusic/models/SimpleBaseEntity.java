package com.htf.fmusic.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author HTFeeds
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class SimpleBaseEntity implements Serializable {

	protected static final long serialVersionUID = 1L;

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
