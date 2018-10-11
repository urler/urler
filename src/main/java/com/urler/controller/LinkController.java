package com.urler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.urler.utils.AppUtils;
import com.urler.enums.ResponseStatus;
import com.urler.bean.Link;
import com.urler.bean.LinkRequest;
import com.urler.bean.LinkRequestStatistic;
import com.urler.service.LinkServiceInterface;
import com.urler.value_objects.ApiRequest;
import com.urler.value_objects.ApiResponse;
import com.urler.value_objects.Response;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

@Controller
public class LinkController {

    @Autowired
    LinkServiceInterface linkServiceInterface;

    @RequestMapping("*")
    public String redirectToUrl(HttpServletRequest request, HttpServletResponse response) {
        String requestedLink = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        requestedLink = requestedLink.substring(1);
        Link l = linkServiceInterface.findByLink(requestedLink);
        if (l != null) {
            response.setHeader("Location", l.getUrl());
            response.setStatus(302);
            String remoteAddr = "";
            if (request != null) {
                remoteAddr = request.getHeader("X-FORWARDED-FOR");
                if (remoteAddr == null || "".equals(remoteAddr)) {
                    remoteAddr = request.getRemoteAddr();
                }
            }
            LinkRequest lr = new LinkRequest();
            lr.setLink(l.getLink());
            lr.setIp(remoteAddr);

            linkServiceInterface.insertUrlRequest(lr);
        }
        return "index";
    }

    @RequestMapping("/app")
    public String getAppIndex() {
        return "index";
    }
    
    @RequestMapping("/app/testApi")
    public String getAppTestApi() {
        return "testApi";
    }

    @RequestMapping("/app/topTenLinks")
    @ResponseBody
    public Response getAppTopTenLinks() {
        Response result = new Response();
        try {
            
            String json = new ObjectMapper().writeValueAsString(getTopTenLinks());
            result.setStatus(ResponseStatus.OK);
            result.setMessage(json);
        } catch (JsonProcessingException ex) {
            return Response.createErrorResponse();
        }
        return result;
    }

    @PostMapping(value = "/app/createLink", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Response createLink(@RequestParam Map<String, String> body) {
        String url = body.get("url");

        if (!AppUtils.isUrlValid(url)) {
            return Response.createErrorResponse();
        }

        Response result = new Response();
        Link saved = insertLink(url);
        result.setMessage(saved.getLink());
        result.setStatus(ResponseStatus.OK);
        return result;
    }

    @PostMapping(value = "/app/api", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ApiResponse processApiRequest(@RequestParam Map<String, String> post) {

        ApiRequest request = new ApiRequest(post);

        ApiResponse errorResponse = request.validateRequest();

        if (errorResponse != null) {
            return errorResponse;
        }

        String id = post.get("id");
        ApiResponse response = new ApiResponse(id);

        if (request.isCreateAction()) {
            Link saved = insertLink(request.getUrl());
            if (saved != null) {
                response.setMessage(saved.getLink());
                response.setStatus(ResponseStatus.OK);
            }
        }

        if (request.isStatsAction()) {
            Integer requestCount = getLinkRequestCountByDate(request);
            response.setMessage(requestCount.toString());
            response.setStatus(ResponseStatus.OK);
        }

        return response;
    }

    private Link insertLink(String url) {
        Link l = new Link(url);
        Link saved = linkServiceInterface.insertLink(l);
        return saved;
    }

    private int getLinkRequestCountByDate(ApiRequest request) {
        String link = request.getLink();
        Date date = AppUtils.getDateFromString(request.getDate());
        LinkRequestStatistic result = linkServiceInterface.getLinkRequestCountByDate(link, date);;
        if(result != null) {
            return result.getCount();
        }
        return 0;
    }

    private List<LinkRequestStatistic> getTopTenLinks() {
        return linkServiceInterface.getTodayTopTenLinks();
    }

}
