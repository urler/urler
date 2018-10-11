package com.urler.bean;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "link_requests")
@NamedQueries({
    @NamedQuery(name = LinkRequest.FIND_ALL, query = "SELECT l FROM LinkRequest l"),
    @NamedQuery(name = LinkRequest.FIND_BY_DATE_AND_LINK, query = "SELECT l FROM LinkRequest l WHERE cast(l.requested_at as date) = :date AND l.link = :link"),
    @NamedQuery(name = LinkRequest.FIND_BY_DATE, query = "SELECT l FROM LinkRequest l WHERE cast(l.requested_at as date) = :date"),
})
public class LinkRequest {
    public static final String FIND_ALL = "LinkRequest.findAll";
    public static final String FIND_BY_DATE_AND_LINK = "LinkRequest.findDistinctByDateAndLink";
    public static final String FIND_BY_DATE = "LinkRequest.findDistinctByDate";

    
    @Id
    @SequenceGenerator(name = "link_requests_request_id_seq", sequenceName = "link_requests_request_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="request_id")
    private Long requestId;
    
    @Column(name="link")
    private String link;
    
    @Column(name="requested_at")
    private Date requested_at = new Date();
    
    @Column(name="ip")
    private String ip;

    public LinkRequest() {
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getRequested_at() {
        return requested_at;
    }

    public void setRequested_at(Date requested_at) {
        this.requested_at = requested_at;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.requestId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LinkRequest other = (LinkRequest) obj;
        if (!Objects.equals(this.requestId, other.requestId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LinkRequest{" + "requestId=" + requestId + ", link=" + link + ", requested_at=" + requested_at + ", ip=" + ip + '}';
    }

    
}
