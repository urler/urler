package com.urler.bean;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "links")
@NamedQueries({
    @NamedQuery(name = Link.FIND_ALL, query = "SELECT l FROM Link l")
    ,
    @NamedQuery(name = Link.FIND_BY_ID, query = "SELECT l FROM Link l WHERE l.link = :link")
})
public class Link {

    public static final String FIND_ALL = "Link.findAll";
    public static final String FIND_BY_ID = "Link.findById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String link;
    private String url;

    public Link() {
    }

    public Link(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.link);
        hash = 17 * hash + Objects.hashCode(this.url);
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
        final Link other = (Link) obj;
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Link{" + "link=" + link + ", url=" + url + '}';
    }

}
