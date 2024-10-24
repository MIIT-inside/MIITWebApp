package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.configuration.UrlsConfig;
import com.example.BackendMIIT.model.domain.IndividualAchievements;
import com.example.BackendMIIT.repositories.IndividualAchievementsRepository;
import com.example.BackendMIIT.service.IndividualAchievementsService;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class IndividualAchievementsServiceImpl implements IndividualAchievementsService {

    private final IndividualAchievementsRepository individualAchievementsRepository;
    private final UrlsConfig urlsConfig;

    public IndividualAchievementsServiceImpl(IndividualAchievementsRepository individualAchievementsRepository, UrlsConfig urlsConfig) {
        this.individualAchievementsRepository = individualAchievementsRepository;
        this.urlsConfig = urlsConfig;
    }

    @Override
    public void parseAndSaveAchievements() {
        Set<IndividualAchievements> parsedAchievements = parse();

        parsedAchievements.add(new IndividualAchievements(
                "Статус чемпиона или призера Олимпийских, Паралимпийских, " +
                        "Сурдлимпийских игр, чемпионатов мира и Европы.", "10"));
        parsedAchievements.add(new IndividualAchievements(
                "Наличие документа об образовании с отличием " +
                        "(аттестат или диплом).", "10"));
        parsedAchievements.add(new IndividualAchievements(
                "Наличие золотого, серебряного или бронзового знака отличия ГТО." +
                        "Начисление баллов за наличие знака ГТО осуществляется однократно.", "2"));
        parsedAchievements.add(new IndividualAchievements(
                "Участие в добровольческих формированиях в рамках " +
                        "специальной военной операции.", "10"));

        individualAchievementsRepository.saveAll(parsedAchievements);
    }

    @Override
    public List<IndividualAchievements> getAllIndividualAchievements() {
        return individualAchievementsRepository.findAll();
    }

    @SneakyThrows
    public Set<IndividualAchievements> parse() {
        String iaUrl = urlsConfig.getIa();

        Set<IndividualAchievements> achievements = new HashSet<>();

        Document document = Jsoup.connect(iaUrl).maxBodySize(0).get();
        Element table = document.selectFirst("table.mytable.lastcenter.table.table-striped");

        if (table == null) {
            System.err.println("Table not found");
            return achievements;
        }

        Elements achievementsElements = table.select("tbody tr");

        for (Element achievement : achievementsElements) {
            IndividualAchievements individualAchievement = extractAchievement(achievement);

            if (areAllNotNull(individualAchievement)) {
                achievements.add(individualAchievement);
            }
        }

        return achievements;
    }

    private static IndividualAchievements extractAchievement(Element achievement) {
        try {

            Elements columns = achievement.select("td");
            if (!isValidSize(columns)) {
                return null;
            }

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
            if (arg == null) {
                return false;
            }
        }
        return true;
    }
}
