package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.model.domain.AnnotationData;
import com.example.BackendMIIT.parser.util.ParserUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AnnotationProgramsParser {

    private static final String educationProgramsUrl = "https://www.miit.ru/sveden/education/programs";

    public List<String> parseAnnotations() {
        Map<String, AnnotationData> latestAnnotations = new HashMap<>();

        try {
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

                System.out.println("Level: " + educationLevel + ", Form: " + educationForm + ", Profile: " + profile);

                String annotationLink = "https://www.miit.ru" + extractAnnotationLink(row);
                String annotationText = extractAnnotationText(row);
                int year = extractYearFromText(annotationText);

                System.out.println("Annotation Link: " + annotationLink + ", Year: " + year);

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
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return latestAnnotations.values().stream()
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

    private int extractYearFromText(String annotationText) {
        if (annotationText.contains("2025")) {
            return 2025;
        } else if (annotationText.contains("2024")) {
            return 2024;
        }
        return 0;
    }

    private String extractAnnotationText(Element row) {
        Element annotationElement = row.select("td[itemprop='educationAnnotation'] a span.link-text").first();
        return ParserUtil.isNotNull(annotationElement) ? annotationElement.text() : "";
    }

    private String extractAnnotationLink(Element row) {
        Element annotationElement = row.select("td[itemprop='educationAnnotation'] a").first();
        return ParserUtil.isNotNull(annotationElement) ? annotationElement.attr("href") : null;
    }
}

