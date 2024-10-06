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

                if (individualAchievement != null) {
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
            String description = achievement.select("td:nth-child(2)").text().trim();
            int countPoints = Integer.parseInt(achievement.select("td:nth-child(3)").text().trim());

            IndividualAchievements individualAchievements = new IndividualAchievements();
            individualAchievements.setDescription(description);
            individualAchievements.setCountPoints(countPoints);

            return individualAchievements;
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
