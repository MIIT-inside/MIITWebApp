package com.example.BackendMIIT.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EducationPlanParser {

    //TODO: delegate url init to the url config after merging feature/ii/parse-ia branch
    private static final String educationProgramsUrl = "https://www.miit.ru/sveden/education/programs";

    public List<String> parseAnnotations() {
        Map<String, String> latestAnnotations = new HashMap<>();

        try {
            Elements rows = getRows();

            for (Element row : rows) {
                String educationLevel = getStringFromElement(row, "td[itemprop='eduLevel']");
                String educationForm = getStringFromElement(row, "td[itemprop='eduForm']");
                String profile = getStringFromElement(row, "td[itemprop='eduProf']");

                if (!isValidEducationLevel(educationLevel) ||
                        !isValidEducationForm(educationForm) ||
                        isNullOrEmpty(profile)) {
                    continue;
                }

                String annotationLink = extractAnnotationLink(row);

                if (isNotNull(annotationLink)) {
                    int year = extractYearFromLink(annotationLink);

                    if (year >= 2024) {
                        if (!latestAnnotations.containsKey(profile)) {
                            latestAnnotations.put(profile, annotationLink);
                        } else {
                            int existingYear = extractYearFromLink(latestAnnotations.get(profile));
                            if (year > existingYear) {
                                latestAnnotations.put(profile, annotationLink);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return new ArrayList<>(latestAnnotations.values());
    }

    private Document getDocumentByUrl() throws IOException {
        return Jsoup.connect(educationProgramsUrl).get();
    }

    private Elements getRows() throws IOException {
        Document document = getDocumentByUrl();
        return document.select("tr[itemprop='eduOp']");
    }

    private static String getStringFromElement(Element element, String prop) {
        return element.select(prop).text().trim();
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private boolean isValidEducationLevel(String educationLevel) {
        return educationLevel.equalsIgnoreCase("бакалавриат") ||
                educationLevel.equalsIgnoreCase("специалитет");
    }

    private boolean isValidEducationForm(String educationForm) {
        return educationForm.equalsIgnoreCase("очная");
    }

    private int extractYearFromLink(String link) {
        if (link.contains("2025")) {
            return 2025;
        } else if (link.contains("2024")) {
            return 2024;
        }
        return 0;
    }

    private String extractAnnotationLink(Element row) {
        Element annotationElement = row.select("td[itemprop='educationAnnotation'] a").first();
        return isNotNull(annotationElement) ? annotationElement.attr("href") : null;
    }

    private boolean isNotNull(Object arg) {
        return arg != null;
    }
}
