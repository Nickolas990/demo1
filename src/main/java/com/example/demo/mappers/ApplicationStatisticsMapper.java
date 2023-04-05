package com.example.demo.mappers;


import com.example.demo.domain.ApplicationStatistics;
import com.example.demo.domain.CharStats;
import com.example.demo.dto.ApplicationStatisticsDTO;
import com.example.demo.interfaces.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class ApplicationStatisticsMapper implements Mapper<ApplicationStatistics, ApplicationStatisticsDTO> {

    private final CharStatsMapper mapper;

    @Autowired
    public ApplicationStatisticsMapper(CharStatsMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ApplicationStatisticsDTO toDto(ApplicationStatistics value) {
        ConcurrentHashMap<Character, CharStats> map = value.getStats();
        ApplicationStatisticsDTO result = new ApplicationStatisticsDTO();


        for (Map.Entry<Character, CharStats> entry : map.entrySet()) {
            result.getStats().put(entry.getKey(), mapper.toDto(entry.getValue()));
        }
        return result;
    }
}
