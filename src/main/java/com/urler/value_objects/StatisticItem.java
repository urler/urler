package com.urler.value_objects;

public class StatisticItem {
    private String link;
    private Integer count = 0;

    public StatisticItem() {
    }

    public StatisticItem(String link, String requestCount) {
        this.link = link;
        this.count = Integer.valueOf(requestCount);
    }

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
    
    
}
