package com.amu.helpers;

import org.apache.ibatis.jdbc.SQL;

public class NewsHelper {
    public String addNews(){
        return new SQL(){
            {
                INSERT_INTO("news");
                INTO_COLUMNS("title","content");
                INTO_VALUES("#{title}","#{content}");
            }
        }.toString();
    }

    public String getNewsByClass(final String news_class){
        return new SQL(){
            {
                SELECT("*");
                FROM("news");
                if (news_class == null || news_class.trim().equals("")){
                    WHERE("class = 'webnews'");
                } else {
                    WHERE("class = #{news_class}");
                }
                ORDER_BY("newsid desc");
            }
        }.toString();
    }
}
