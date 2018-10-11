package com.urler.service;

import com.urler.utils.AppUtils;
import com.urler.bean.Link;
import com.urler.bean.LinkRequest;
import com.urler.bean.LinkRequestStatistic;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LinkService implements LinkServiceInterface {

    @PersistenceContext

    private EntityManager em;

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Link insertLink(Link linkToInsert) {
        return em.merge(linkToInsert);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Link findByLink(String requestedLink) {
        Link result = null;
        TypedQuery q = em.createNamedQuery(Link.FIND_BY_ID, Link.class);
        q.setParameter("link", requestedLink);
        try {
            result = (Link) q.getSingleResult();
        } catch (NoResultException nre) {
        }

        return result;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void insertUrlRequest(LinkRequest lr) {
        em.merge(lr);
    }

    @Override
    public LinkRequestStatistic getLinkRequestCountByDate(String link, Date date) {
        LinkRequestStatistic result = null;
        TypedQuery<LinkRequestStatistic> q = em.createNamedQuery(LinkRequestStatistic.FIND_BY_DATE_AND_LINK, LinkRequestStatistic.class);
        q.setParameter("date", date);
        q.setParameter("link", link);
        q.setMaxResults(1);
        
        try {
            result = (LinkRequestStatistic) q.getSingleResult();
        } catch (NoResultException nre) {
        }

        return result;
    }

    @Override
    public List<LinkRequestStatistic> getTodayTopTenLinks() {
        TypedQuery<LinkRequestStatistic> q = em.createNamedQuery(LinkRequestStatistic.FIND_BY_DATE, LinkRequestStatistic.class);
        Date today = AppUtils.getBeginningOfDate(new Date());
        q.setParameter("date", today);
        q.setMaxResults(10);
        return q.getResultList();

    }
}
