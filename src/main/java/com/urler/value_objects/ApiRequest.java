package com.urler.value_objects;

import com.urler.utils.AppUtils;
import com.urler.enums.ApiAction;
import java.util.Map;

public class ApiRequest {

    private ApiAction action;
    private String date;
    private String url;
    private String link;
    private String id;

    public ApiRequest() {
    }

    public ApiRequest(Map<String, String> post) {
        this.id = post.get("id");
        this.action = ApiAction.valueOf(post.get("action"));
        this.date = post.get("date");
        this.url = post.get("url");
        this.link = post.get("link");
    }

    public ApiAction getAction() {
        return action;
    }

    public void setAction(ApiAction action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatsAction() {
        return ApiAction.stats.equals(this.getAction());
    }

    public boolean isCreateAction() {
        return ApiAction.create.equals(this.getAction());
    }

    public ApiResponse validateRequest() {
        if (getId() == null) {
            return ApiResponse.createErrorResponse("Transaction id cannot be empty", null);
        }

        if (isCreateAction()) {
            if (getUrl() == null) {
                return ApiResponse.createErrorResponse("URL cannot be empty", getId());
            }

            if (!AppUtils.isUrlValid(getUrl())) {
                return ApiResponse.createErrorResponse("URL format wrong", getId());
            }

            if (!AppUtils.isProtocolSpecified(getUrl())) {
                return ApiResponse.createErrorResponse("Please specify protocol (http:// or https://)", getId());
            }
        }

        if (isStatsAction()) {
            if (getLink() == null) {
                return ApiResponse.createErrorResponse("Link cannon be empty", getId());
            }
            if (!AppUtils.isDateValid(getDate())) {
                return ApiResponse.createErrorResponse("Date is empty or wrong format. Please use as follows yyyy-mm-dd", getId());
            }

        }

        return null;
    }
}