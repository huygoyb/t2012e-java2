package com.t2012e.worker;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


public class ArticleThread extends Thread {
    private String url;
    private Article article;

    public ArticleThread(String url) {
        this.url = url;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        System.out.printf("Crawling data form url %\n", url);
        crawlData();
    }

    private void crawlData() {
        try {
            article = new Article();
            article.setUrl(url);
            Document document = Jsoup.connect(url).get();
            // xu ly lay tieu de tin theo selector tu trang vnexpress
            Element titleNode = document.selectFirst("h1.title-detail");
            // kiem tra tieu de mode lay tu text ben trong set vao tieu de cua doi tuong article.
            if (titleNode != null) {
                String title = titleNode.text();
                article.setTitle(title);
            }
            // xu ly lay mo ta chi tiet
            Element contentElement = document.selectFirst("h1.title-detail");
            if (contentElement != null) {
                String content = contentElement.text();
                article.setContent(content);
            }
            // xu ly lay mo ta bai viet
            Element descriptionElement = document.selectFirst("p.description");
            if (descriptionElement != null) {
                String description = descriptionElement.text();
                article.setDescription(description);
            }
            // xu ly lay anh
            Element thumbnailElement = document.selectFirst("div. fig- picture picture img");
            if (thumbnailElement != null) {
                String thumbnail = thumbnailElement.text();
                article.setThumbnail(thumbnail);
                System.out.println(article.getThumbnail());
            } else {
                article.setThumbnail("htpp://defaulf.jpeg");
            }
            article.setStatus(1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.printf("Error %s,", ioException.getMessage());
        }
    }
}