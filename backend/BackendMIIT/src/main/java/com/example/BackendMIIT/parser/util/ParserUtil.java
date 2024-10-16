package com.example.BackendMIIT.parser.util;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserUtil {

    @SneakyThrows
    public static Document getDocumentByUrl(String url) {
        return Jsoup.connect(url).maxBodySize(0).get();
    }

    public static Elements getElements(String url, String cssQuery) {
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
