package com.catchingnow.evangelion.unit00;

/**
 * Created by Heaven on 4/22/15.
 */
public class Article {
    private long date;
    private String title;
    private String content;
    private String link;
    private String source;
    private String other;
    public Article(long date, String title, String content, String link, String source, String other){
        this.date = date;
        this.title = title;
        this.content = content;
        this.link = link;
        this.source = source;
        this.other = other;
    }
    public Article(long date, String title, String content, String link, String source){
        this(date, title, content, link, source, null);
    }
    public long getDate(){
        return date;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }
    public String getLink(){
        return link;
    }
    public String getSource(){
        return source;
    }
    public String getOther(){
        return other;
    }
}
