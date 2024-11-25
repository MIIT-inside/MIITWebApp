package com.example.BackendMIIT.parser;

import com.example.BackendMIIT.configuration.UrlsConfig;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstituteParser {

	private final String deptsUrl;
	private final List<String> profiles = new ArrayList<>();

	public InstituteParser(UrlsConfig urlsConfig) {
		this.deptsUrl = urlsConfig.getDeptsUrl();
	}

	@SneakyThrows
	public Elements parseInstitutes() {

		Document doc = Jsoup.connect(deptsUrl).maxBodySize(0).get();
		Element container = doc.selectFirst("div[class=info-block__content info-block__content_top-padding]");

		return container.select("a");
	}

	@SneakyThrows
	public List<String> getProfiles(String link) {

		Document doc = Jsoup.connect(link).maxBodySize(0).get();
		Element divContainer = doc.selectFirst("div[class=info-block info-block_collapse dept-about");
		Element ul = divContainer.selectFirst("ul");
		Elements profilesTag = ul.select("a");

		for (Element profile : profilesTag) {

			String profileText = profile.text();
			profiles.add(profileText.substring(profileText.indexOf(". ")).trim());
		}

		return profiles;
	}
}
