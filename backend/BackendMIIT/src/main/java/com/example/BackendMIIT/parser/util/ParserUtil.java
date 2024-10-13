package com.example.BackendMIIT.parser.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParserUtil {

    public static Document getDocumentByUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public static Elements getElements(String url, String cssQuery) throws IOException {
        Document document = getDocumentByUrl(url);
        return document.select(cssQuery);
    }

    public static String getStringFromElement(Element element, String prop) {
        return element.select(prop).text().trim();
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }


    public static boolean isNotNull(Object arg) {
        return arg != null;
    }
}
