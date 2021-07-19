package com.t2012e.entity;

import com.t2012e.util.DateTimeUtil;

import java.util.Calendar;
import java.util.Date;

public class Article {
    private String url;
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private Date createdAt;
    private Date updatedAt;
    private int status; // 0. chưa hoàn thiện, 1, đã hoàn thiện, -1, đã xoá.

    public Article() {
        this.title = "";
        this.description = "";
        this.content = "";
        this.thumbnail = "";
        this.createdAt = Calendar.getInstance().getTime();
        this.updatedAt = Calendar.getInstance().getTime();
        this.status = 0;
    }

    public Article(String url, String title, String description, String content, String thumbnail, int status) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.content = content;
        this.thumbnail = thumbnail;
        this.createdAt = Calendar.getInstance().getTime();
        this.updatedAt = Calendar.getInstance().getTime();
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedAtString() {
        return DateTimeUtil.formatDateToString(this.createdAt);
    }

    public String getUpdatedAtString() {
        return DateTimeUtil.formatDateToString(this.updatedAt);
    }

    public boolean isValid(){
        if(this.title.length()==0){
            System.out.println("Content is required");
            return false;
        }
        return true;
    }
}
