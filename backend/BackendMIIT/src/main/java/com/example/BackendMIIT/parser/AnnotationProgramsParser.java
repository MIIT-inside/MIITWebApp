package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.model.domain.AnnotationData;
import com.example.BackendMIIT.parser.util.ParserUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AnnotationProgramsParser {

    @Value("${miit.baseurl}")
    private static String miitBaseUrl;

    @Value("${url.edu-programs}")
    private static String educationProgramsUrl;

    public List<String> parseAnnotations() {
        Map<String, AnnotationData> latestAnnotations = new HashMap<>();

        Elements rows = ParserUtil.getElements(educationProgramsUrl, "tr[itemprop='eduOp']");

        for (Element row : rows) {
            String educationForm = ParserUtil.getStringFromElement(row, "td[itemprop='eduForm']");
            String educationLevel = ParserUtil.getStringFromElement(row, "td[itemprop='eduLevel']");
            String profile = ParserUtil.getStringFromElement(row, "td[itemprop='eduProf']");

            if (!isValidEducationLevel(educationLevel) ||
                    !isValidEducationForm(educationForm) ||
                    ParserUtil.isNullOrEmpty(profile)) {
                continue;
            }

            String annotationLink = miitBaseUrl + extractAnnotationLink(row);
            String annotationText = extractAnnotationText(row);
            int year = extractYearFromText(annotationText);

            if (year >= 2024) {
                if (!latestAnnotations.containsKey(profile)) {
                    latestAnnotations.put(profile, new AnnotationData(annotationLink, year));
                } else {
                    int existingYear = latestAnnotations.get(profile).year();

                    if (year > existingYear) {
                        latestAnnotations.put(profile, new AnnotationData(annotationLink, year));
                    }
                }
            }
        }

        return latestAnnotations
                .values()
                .stream()
                .map(AnnotationData::link)
                .collect(Collectors.toList());
    }

    private boolean isValidEducationLevel(String educationLevel) {
        return educationLevel.equalsIgnoreCase("бакалавриат") ||
                educationLevel.equalsIgnoreCase("специалитет");
    }

    private boolean isValidEducationForm(String educationForm) {
        return educationForm.equalsIgnoreCase("очная");
    }

    private String extractAnnotationLink(Element row) {
        Element annotationElement = row.select("td[itemprop='educationAnnotation'] a").first();
        return ParserUtil.isNotNull(annotationElement) ? annotationElement.attr("href") : null;
    }

    private String extractAnnotationText(Element row) {
        Element annotationElement = row.select("td[itemprop='educationAnnotation'] a span.link-text").first();
        return ParserUtil.isNotNull(annotationElement) ? annotationElement.text() : "";
    }

    private int extractYearFromText(String annotationText) {
        if (annotationText.contains("2025")) {
            return 2025;
        } else if (annotationText.contains("2024")) {
            return 2024;
        }
        return 0;
    }
}
