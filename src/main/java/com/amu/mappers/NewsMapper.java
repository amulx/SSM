package com.amu.mappers;

import com.amu.Entities.NewsInfo;
import com.amu.helpers.NewsHelper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper {

    public List<NewsInfo> getNews();

    @Select("select * from news where newsid=#{newsid}")
    public NewsInfo getNewsDetail(@Param("newsid") int newsid);

    @InsertProvider(type = NewsHelper.class,method = "addNews")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",keyProperty = "newsid",before = false,resultType = Integer.class)
    public int addNews(NewsInfo newsInfo);

    @SelectProvider(type = NewsHelper.class,method = "getNewsByClass")
    public List<NewsInfo> getNewsByClass(String news_class);
}
