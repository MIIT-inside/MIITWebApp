package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.configuration.UrlsConfig;
import com.example.BackendMIIT.model.domain.IndividualAchievements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class IndividualAchievementsParser {

    private final UrlsConfig urlsConfig;

    public IndividualAchievementsParser(UrlsConfig urlsConfig) {
        this.urlsConfig = urlsConfig;
    }

    public Set<IndividualAchievements> parse() {
        String iaUrl = urlsConfig.getIa();

        Set<IndividualAchievements> achievements = new HashSet<>();

        try {
            Document document = Jsoup.connect(iaUrl).get();
            Element table = document.selectFirst("table.mytable.lastcenter.table.table-striped");

            if (table == null) {
                System.err.println("Table not found");
                return achievements;
            }

            Elements achievementsElements  = table.select("tbody tr");

            for (Element achievement: achievementsElements) {
                IndividualAchievements individualAchievement = extractAchievement(achievement);

                if (areAllNotNull(individualAchievement)) {
                    achievements.add(individualAchievement);
                }
            }
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }

        return achievements;
    }

    private static IndividualAchievements extractAchievement(Element achievement) {
        try {

            Elements columns = achievement.select("td");
            if (!isValidSize(columns)) { return null; }

            String description = getStringFromElement(columns, 1);
            String pointsText = getStringFromElement(columns, 2);

            if (description.length() > 255) {
                return null;
            }

            return achievementWithParams(description, pointsText);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private static boolean isValidSize(Elements elements) {
        if (elements.size() != 3) {
            System.err.println("Invalid number of columns in achievement row");
            return false;
        }
        return true;
    }


    private static String getStringFromElement(Elements elements, int index) {
        return elements.get(index).text().trim();
    }

    private static IndividualAchievements achievementWithParams(String description, String countPoints) {
        description = description.replace("\"", "").trim();
        countPoints = countPoints.trim();

        IndividualAchievements achievement = new IndividualAchievements();
        achievement.setDescription(description);
        achievement.setCountPoints(countPoints);

        return achievement;
    }

    private static boolean areAllNotNull(Object... args) {
        for (Object arg : args) {
            if (arg == null) { return false; }
        }
        return true;
    }
}
