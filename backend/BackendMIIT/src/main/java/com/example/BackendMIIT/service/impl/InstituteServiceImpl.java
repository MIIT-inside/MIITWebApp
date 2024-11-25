package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.model.domain.Institute;
import com.example.BackendMIIT.model.domain.Profile;
import com.example.BackendMIIT.parser.InstituteParser;
import com.example.BackendMIIT.repository.InstituteRepository;
import com.example.BackendMIIT.repository.ProfileRepository;
import com.example.BackendMIIT.service.InstituteService;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {

	private final InstituteRepository instituteRepository;
	private final InstituteParser instituteParser;
	private final ProfileRepository profileRepository;

	@Override
	public String parseInstitutes() {

		Elements insts = null;
		if (instituteParser.parseInstitutes() != null) {
			insts = instituteParser.parseInstitutes();
		}

		if (insts == null) {
			return "Failed";
		}

		for (Element inst : insts) {

			String link = extractLink(inst);
			saveInstitute(extractName(inst),
					extractAbbreviation(inst),
					extractProfiles(link)
			);
		}

		return "Success";
	}

	private String extractAbbreviation(Element inst) {

		return inst.text().substring(inst.text().indexOf(",")+2).trim();
	}

	private String extractLink(Element inst) {

		return inst.attr("abs:href");
	}

	private List<Profile> extractProfiles(String link) {

		List<String> names = instituteParser.getProfiles(link);
		List<Profile> profiles = new ArrayList<>();

		for (String name : names) {
			Optional<Profile> profile = profileRepository.findByName(name);

			profile.ifPresent(profiles::add);
		}

		return profiles;
	}

	private String extractName(Element inst) {

		return inst.text().substring(0, inst.text().indexOf(",")+2).trim();
	}

	private void saveInstitute(String name, String abbreviation, List<Profile> profiles) {

		Institute institute = new Institute();

		institute.setProfiles(profiles);
		institute.setName(name);
		institute.setAbbreviation(abbreviation);
	}
}
