package com.t2012e;

import com.t2012e.entity.Article;
import com.t2012e.util.ConnectionHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainThread {
    public static void main(String[] args) throws IOException, SQLException {
        long startTime = System.currentTimeMillis();
        ArrayList<Article> list = new ArrayList<>();
        Document doc = Jsoup.connect("https://vnexpress.net/the-thao").get();
        Elements els = doc.select(".title-news a");
        for (int i = 0; i < els.size(); i++) {
            Article article = new Article();
            article.setUrl(els.get(i).attr("href"));
            article.setStatus(0);
            list.add(article);
        }
        System.out.println(list.size());

        List<Thread> listThread = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Article article = list.get(finalI);
                    try {
                        Document currentDoc = Jsoup.connect(article.getUrl()).get();
                        String title = currentDoc.select("h1.title-detail").first().text();
                        String content = currentDoc.select("article.fck_detail").first().text();
                        article.setTitle(title);
                        article.setContent(content);
                        insertArticle(article);
                        System.out.printf("%d - %s\n", finalI + 1, title);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            listThread.add(t);
            // crawl + bot
        }
        for (int i = 0; i < listThread.size(); i++) {
            listThread.get(i).start();
        }
        for (int i = 0; i < listThread.size(); i++) {
            try {
                listThread.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + " mls.");
//        Article article =
//                new Article("url", "title", "description",
//                        "content", "thumb", 0);
//        try {
//            insertArticle(article);
//        } catch (SQLException throwables) {
//            System.err.println("Có lỗi xảy ra, vui lòng thử lại sau.");
//        }
    }

    static void insertArticle(Article article) throws SQLException {
        Connection cnn = ConnectionHelper.getConnection();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("insert into articles");
        sqlBuilder.append(" ");
        sqlBuilder.append("(url, title, description, content, thumbnail, createdAt, updatedAt, status)");
        sqlBuilder.append(" ");
        sqlBuilder.append("values");
        sqlBuilder.append(" ");
        sqlBuilder.append("(?, ?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement preparedStatement = cnn.prepareStatement(sqlBuilder.toString());
        preparedStatement.setString(1, article.getUrl());
        preparedStatement.setString(2, article.getTitle());
        preparedStatement.setString(3, article.getDescription());
        preparedStatement.setString(4, article.getContent());
        preparedStatement.setString(5, article.getThumbnail());
        preparedStatement.setString(6, article.getCreatedAtString());
        preparedStatement.setString(7, article.getUpdatedAtString());
        preparedStatement.setInt(8, article.getStatus());
        preparedStatement.execute();
    }
}
