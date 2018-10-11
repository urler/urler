package com.urler.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "link_request_statistics")
@NamedQueries({
    @NamedQuery(name = LinkRequestStatistic.FIND_ALL, query = "SELECT l FROM LinkRequestStatistic l"),
    @NamedQuery(name = LinkRequestStatistic.FIND_BY_DATE_AND_LINK, query = "SELECT l FROM LinkRequestStatistic l WHERE l.requestDate = :date AND l.link = :link"),
    @NamedQuery(name = LinkRequestStatistic.FIND_BY_DATE, query = "SELECT l FROM LinkRequestStatistic l WHERE l.requestDate = :date")
})
public class LinkRequestStatistic implements Serializable{
    
     public static final String FIND_ALL = "LinkRequestStatistic.findAll";
    public static final String FIND_BY_DATE_AND_LINK = "LinkRequestStatistic.findDistinctByDateAndLink";
    public static final String FIND_BY_DATE = "LinkRequestStatistic.findDistinctByDate";

    @Id
    @Column(name = "link")
    private String link;

    @Id
    @Column(name = "request_date")
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    
    @Column(name = "count")
    private Integer count;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

}
