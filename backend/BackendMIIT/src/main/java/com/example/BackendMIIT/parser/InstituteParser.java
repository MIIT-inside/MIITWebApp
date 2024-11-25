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
@Getter
public class InstituteParser {

	private final String deptsUrl;
	private final List<String> instLinks = new ArrayList<>();
	private final List<String> instNames = new ArrayList<>();

	public InstituteParser(UrlsConfig urlsConfig) {
		this.deptsUrl = urlsConfig.getDeptsUrl();
	}

	@SneakyThrows
	public void parseInstitutes() {

		Document doc = Jsoup.connect(deptsUrl).maxBodySize(0).get();


		Element container = doc.selectFirst("div[class=info-block__content info-block__content_top-padding]");
		Elements deptProps = container.select("a");

		for (Element dept : deptProps) {
			instLinks.add(dept.attr("abs:href"));
			instNames.add(dept.text());
		}
	}
}
