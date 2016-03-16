package com.htf.fmusic.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author HTFeeds
 */
@Entity
@Table(name = "weeks")
public final class Week extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotNull
    @Column(name = "no", nullable = false)
    private Integer no;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.Summary.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "startDate")
    private Date startDate;

    @JsonView(Views.Summary.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "endDate")
    private Date endDate;

    @JsonView(Views.Summary.class)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "week")
    private List<Playlist> playlists = new ArrayList<Playlist>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void update(Integer newNo, String newName, Date newStartDate, Date newEndDate, String newDescription) {
        this.no = newNo;
        this.name = newName;
        this.startDate = newStartDate;
        this.endDate = newEndDate;
        this.description = newDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Week)) {
            return false;
        }
        Week other = (Week) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("description", this.description).toString();
    }
}
