package com.amu.controllers;

import com.amu.Entities.NewsInfo;
import com.amu.mappers.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("news")
public class NewsController {
    @Autowired
    NewsMapper newsMapper;

    @GetMapping("{newsid:\\d+}")
    public @ResponseBody
    NewsInfo getNewsDetail(@PathVariable("newsid") int newsid){
        return newsMapper.getNewsDetail(newsid);
    }

/*    @GetMapping("")
    public @ResponseBody
    List<NewsInfo> getNews(){
        return newsMapper.getNews();
    }*/

    // 访问地址：http://localhost:8080/amulx_war/news/
    // http://localhost:8080/amulx_war/news/aa
    @GetMapping(value = {"","{classname:\\w*}"})
    public @ResponseBody List<NewsInfo> newsList(@PathVariable(value = "classname",required = false) String className){
        return newsMapper.getNewsByClass(className);
    }

    @PostMapping("")
    public @ResponseBody String addNews(@RequestBody NewsInfo newsInfo){
        newsMapper.addNews(newsInfo);
        return  newsInfo.toString();
    }

    @GetMapping("/pets/{petId}")
    public @ResponseBody String findPet(@PathVariable Long newsId, @PathVariable Long petId) {
        return String.valueOf(newsId) + String.valueOf(petId);
    }
    @GetMapping(path = "list")
    public @ResponseBody String newsList(@RequestParam(required = false,defaultValue = "1",value = "p") int page){
        return "中文测试"+"newsList,page="+String.valueOf(page);
    }
    @GetMapping("lists")
    public @ResponseBody String newsLists(HttpServletRequest request){
        return request.getMethod();
    }

    @PostMapping(value = "add")
    public @ResponseBody String addnews(NewsInfo newsInfo){
        return newsInfo.toString();
    }
    @PostMapping(value = "json")
    public @ResponseBody String addnewsjson(@RequestBody NewsInfo newsInfo){
        return newsInfo.toString();
    }
}
