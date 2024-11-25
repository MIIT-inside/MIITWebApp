package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.parser.InstituteParser;
import com.example.BackendMIIT.repository.InstituteRepository;
import com.example.BackendMIIT.service.InstituteService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {

	private final InstituteRepository instituteRepository;
	private final InstituteParser instituteParser;

	public void parseInstitutes() {

	}

	private void extractName() {

		List<String> names = instituteParser.getInstNames();


	}

	public void saveInstitute() {

	}
}
