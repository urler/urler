package com.urler.service;

import com.urler.bean.Link;
import com.urler.bean.LinkRequest;
import com.urler.bean.LinkRequestStatistic;
import java.util.Date;
import java.util.List;

public interface LinkServiceInterface {

    public Link insertLink(Link linkToInsert);
    public Link findByLink(String requestedLink);

    public void insertUrlRequest(LinkRequest link);
    public LinkRequestStatistic getLinkRequestCountByDate(String link, Date date);

    public List<LinkRequestStatistic> getTodayTopTenLinks();
}
