package com.htf.fmusic.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
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

    //    @JsonView(Views.Summary.class)
    //    @Id
    //    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @Column(nullable = false, updatable = false)
    //    private Integer id;

    @Column(name = "created_by_user", nullable = true)
    @CreatedBy
    private String createdByUser;

    @Column(name = "modified_by_user", nullable = true)
    @LastModifiedBy
    private String modifiedByUser;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    protected Date creationTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_time")
    protected Date modificationTime;

    @Version
    @Column(nullable = false, insertable = false, columnDefinition = "INT DEFAULT 0")
    protected Integer version;

    public String getCreatedByUser() {
        return createdByUser;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

}
