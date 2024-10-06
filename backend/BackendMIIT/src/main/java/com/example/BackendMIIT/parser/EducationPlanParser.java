package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.model.domain.Profile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EducationPlanParser {

    //TODO: delegate url init to the url config after merging feature/ii/parse-ia branch
    private static final String educationProgramsUrl = "https://www.miit.ru/sveden/education/programs";

    public void parseEducationPlan(List<Profile> profiles) {
        try {
            Document document = Jsoup.connect(educationProgramsUrl).get();
            Elements rows = document.select("tr[itemprop='eduOp']");

            for (Element row : rows) {
                String specialty = getStringFromElement(row, "td[itemprop='eduName']");
                String educationLevel = getStringFromElement(row, "td[itemprop='eduLevel']");

                if (!isValidSpecialty(specialty) || !isValidEducationLevel(educationLevel)) { continue; }

                String pdfLink = extractPdfLink(row);

                Profile profile = findProfileByName(profiles, specialty);

                if (profile != null && pdfLink != null) {
                    profile.setEducationPlan(pdfLink);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String getStringFromElement(Element element, String prop) {
        return element.select(prop).text().trim();
    }

    private boolean isValidSpecialty(String specialty) {
        return specialty.contains(". ") && specialty.split("\\. ").length == 2;
    }

    private boolean isValidEducationLevel(String educationLevel) {
        return educationLevel.equalsIgnoreCase("бакалавриат") ||
                educationLevel.equalsIgnoreCase("специалитет");
    }

    private String extractPdfLink(Element row) {
        Element pdfElement = row.select("td[itemprop='EducationPlan'] a").first();
        return pdfElement != null ? pdfElement.attr("href") : null;
    }

    private Profile findProfileByName(List<Profile> profiles, String specialty) {
        String[] parts = specialty.split("\\. ");
        if (parts.length < 2) { return null; }

        String profileName = parts[1].trim();
        return profiles.stream()
                .filter(profile -> profile.getName().equalsIgnoreCase(profileName))
                .findFirst()
                .orElse(null);
    }
}
