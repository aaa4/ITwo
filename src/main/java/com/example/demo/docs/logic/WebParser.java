package com.example.demo.docs.logic;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

import static sun.util.logging.LoggingSupport.log;


public class WebParser {

    public static void main(String[] args) throws IOException {
        //Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
        Document doc = Jsoup.connect("https://badoo.com/").get();
        log(doc.title());
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            log("%s\n\t%s",
                    headline.attr("title"), headline.absUrl("href"));
        }
    }

    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }
}
