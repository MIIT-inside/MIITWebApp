package com.example.BackendMIIT.service.impl;

import com.example.BackendMIIT.mapper.DirectionMapper;
import com.example.BackendMIIT.model.domain.Direction;
import com.example.BackendMIIT.model.dto.DirectionDto;
import com.example.BackendMIIT.model.dto.DirectionWithProfilesDto;
import com.example.BackendMIIT.model.dto.PassPointDto;
import com.example.BackendMIIT.repository.DirectionRepository;
import com.example.BackendMIIT.service.DirectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;
    private final DirectionMapper directionMapper;

    public DirectionServiceImpl(DirectionRepository directionRepository, DirectionMapper directionMapper) {
        this.directionRepository = directionRepository;
        this.directionMapper = directionMapper;
    }

    @Override
    @Cacheable(value = "DirectionService::getDirectionByName", key = "#name")
    public DirectionDto getDirectionByName(String name) {
        Direction direction = directionRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Direction doesn't exist"));

        return directionMapper.directionToDto(direction);
    }

    @Override
    @Cacheable(value = "DirectionService::getDirectionByCode", key = "#code")
    public DirectionDto getDirectionByCode(String code) {
        Direction direction = directionRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Direction doesn't exist"));

        return directionMapper.directionToDto(direction);
    }

    @Override
    @Cacheable(value = "DirectionService::getDirections", key = "'directions'")
    public List<DirectionDto> getDirections() {
        List<Direction> directions = directionRepository.findAll();

        return directionMapper.directionToDirectionDto(directions);
    }

    @Override
    @SneakyThrows
    public void parseDirections(String url) {

        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        List<Element> props = new ArrayList<>();

        Elements elements = doc.select("tr");

        for (Element element : elements) {
            props.add(element.selectFirst("td[itemprop=eduCode]"));
            props.add(element.selectFirst("td[itemprop=eduName]"));
            props.add(element.selectFirst("td[itemprop=eduLevel]"));
            props.add(element.selectFirst("td[itemprop=eduForm]"));

            saveDirection(props);
            props.clear();
        }
    }

    @Override
    public void saveDirection(List<Element> props) {

        if (props.get(0) != null && props.get(1) != null && props.get(2) != null && props.get(3) != null) {

            String code = props.get(0).text().trim();
            String name = props.get(1).text().trim();
            String level = props.get(2).text().trim();
            String form = props.get(3).text().trim();

            if (directionRepository.findByCode(code).isEmpty() && form.equals("очная") && (level.equals("бакалавриат") || level.equals("специалитет"))) {

                Direction direction = new Direction();

                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                }

                direction.setCode(code);
                direction.setName(name);
                direction.setLevel(level);
                direction.setForm(form);

                directionRepository.save(direction);
            }
        }
    }

    @Override
    public List<DirectionWithProfilesDto> getSortedDirections(String ppType) {
        List<Direction> directions;
        Sort sort;

        if ("min".equalsIgnoreCase(ppType)) {
            sort = Sort.by(Sort.Order.desc("passPoints.min"), Sort.Order.asc("name"));
        } else if ("avg".equalsIgnoreCase(ppType)) {
            sort = Sort.by(Sort.Order.desc("passPoints.avg"), Sort.Order.asc("name"));
        } else {
            sort = Sort.by(Sort.Order.asc("name"));
        }

        directions = directionRepository.findAll(sort);
        return directions.stream()
                .map(direction -> {
                    DirectionWithProfilesDto dto = directionMapper.directionToWithProfilesDto(direction);
                    dto.setPassPoints(filterAndMapPassPoints(dto.getPassPoints(), ppType));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private List<PassPointDto> filterAndMapPassPoints(List<PassPointDto> passPoints, String ppType) {
        return passPoints.stream()
                .filter(pp -> !(pp.getMin() == 0 && pp.getAvg() == 0))
                .map(pp -> {
                    PassPointDto filteredPoints = new PassPointDto();
                    filteredPoints.setCategory(pp.getCategory());

                    if ("avg".equalsIgnoreCase(ppType)) {
                        filteredPoints.setAvg(pp.getAvg());
                    } else {
                        filteredPoints.setMin(pp.getMin());
                    }

                    return filteredPoints;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<DirectionWithProfilesDto> getSortedDirectionsByCategory(String ppType, String category) {
        List<Direction> directions;
        Sort sort;

        if ("min".equalsIgnoreCase(ppType)) {
            sort = Sort.by(Sort.Order.desc("passPoints.min"), Sort.Order.asc("name"));
        } else if ("avg".equalsIgnoreCase(ppType)) {
            sort = Sort.by(Sort.Order.desc("passPoints.avg"), Sort.Order.asc("name"));
        } else {
            sort = Sort.by(Sort.Order.asc("name"));
        }

        directions = directionRepository.findAll(sort);
        return directions.stream()
                .map(direction -> {
                    DirectionWithProfilesDto dto = directionMapper.directionToWithProfilesDto(direction);
                    dto.setPassPoints(
                            dto.getPassPoints().stream()
                                    .filter(pp ->
                                            pp.getCategory().equalsIgnoreCase(category) && (pp.getMin() != 0 || pp.getAvg() != 0))
                                    .map(pp -> {
                                        PassPointDto filtered = new PassPointDto();
                                        filtered.setCategory(pp.getCategory());
                                        if ("avg".equalsIgnoreCase(ppType)) {
                                            filtered.setAvg(pp.getAvg());
                                        } else {
                                            filtered.setMin(pp.getMin());
                                        }
                                        return filtered;
                                    })
                                    .collect(Collectors.toList())
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
