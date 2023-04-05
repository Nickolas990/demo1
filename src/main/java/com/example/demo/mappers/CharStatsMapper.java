package com.example.demo.mappers;


import com.example.demo.domain.CharStats;
import com.example.demo.dto.CharStatsDTO;
import com.example.demo.interfaces.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CharStatsMapper implements Mapper<CharStats, CharStatsDTO> {
    public CharStatsDTO toDto (CharStats charStats) {
        return new CharStatsDTO(charStats.getCount(), charStats.getAverageLength(), charStats.getAverageChain());
    }
}
