package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.model.domain.IndividualAchievements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class IndividualAchievementsParser {
    private static final String URL = "https://miit.ru/page/136668";

    public Set<IndividualAchievements> parse() {
        Set<IndividualAchievements> achievements = new HashSet<>();

        try {
            Document document = Jsoup.connect(URL).get();
            Elements achievementsElements  = document.select("tr");

            for (Element achievement: achievementsElements) {
                IndividualAchievements individualAchievement = extractAchievement(achievement);

                if (areAllNotNull(individualAchievement)) {
                    achievements.add(individualAchievement);
                }
            }
        } catch (IOException | NumberFormatException ioException) {
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

            Integer countPoints = parseCountPoints(pointsText);

            return achievementWithParams(description, countPoints);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private static boolean isValidSize(Elements elements) {
        if (elements.size() < 3) {
            System.err.println("Invalid number of columns in achievement row");
            return false;
        }
        return true;
    }


    private static String getStringFromElement(Elements elements, int index) {
        return elements.get(index).text().trim();
    }

    private static Integer parseCountPoints(String points) {
        Integer countPoints = null;

        try {
            countPoints = Integer.parseInt(points);
        } catch (NumberFormatException e) {
            System.err.println("Invalid count points format:" + e.getMessage());
        }
        return countPoints;
    }

    private static IndividualAchievements achievementWithParams(String description, Integer countPoints) {
        IndividualAchievements achievement = new IndividualAchievements();

        if (areAllNotNull(description, countPoints)) {
            achievement.setDescription(description);
            achievement.setCountPoints(countPoints);
        }

        return achievement;
    }

    private static boolean areAllNotNull(Object... args) {
        for (Object arg : args) {
            if (arg == null) { return false; }
        }
        return true;
    }
}
