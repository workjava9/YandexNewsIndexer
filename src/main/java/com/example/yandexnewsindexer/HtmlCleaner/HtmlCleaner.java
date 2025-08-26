package com.example.yandexnewsindexer.HtmlCleaner;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlCleaner {

    public static String extractCleanText(Document doc) {
        doc.select("script, style, header, footer, nav, aside, noscript, iframe, svg").remove();

        Element body = doc.body();
        return body.text().replaceAll("\\s+", " ").trim();
    }

    public static String extractTitle(Document doc) {
        return doc.title();
    }
}

