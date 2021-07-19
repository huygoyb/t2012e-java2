package com.t2012e.worker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DemoWorker {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArticleModel model = new ArticleModel();
        ArrayList<String> listUrl = getListUrl();
        ArrayList<ArticleThread> listThread = new ArrayList<>();
        for (int i = 0; i < listUrl.size(); i++) {
            ArticleThread articleThread = new ArticleThread(listUrl.get(i));
            listThread.add(articleThread);
            articleThread.start();
        }
        for (int i = 0; i < listThread.size(); i++) {
            try {
                listThread.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //luc nay tat ca thread da hoan thanh (co article ben trong roi)
        for (int i = 0; i< listThread.size(); i++){
            model.insert(listThread.get(i).getArticle());
        }
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime- startTime + "mls");
    }
    private static ArrayList<String> getListUrl() {
        ArrayList<String>list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("http:/vnexpress.net/the-thao").get();
            Elements els = doc.select(".title=neww a");
            if (els.size()>0){
                for (int i = 0; i< list.size(); i++){
                    list.add(els.get(i).attr("href"));
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.printf("Got %d links.", list.size());
        return list;
    }
}
