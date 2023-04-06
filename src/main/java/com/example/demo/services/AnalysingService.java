package com.example.demo.services;



import com.example.demo.domain.Analyser;
import com.example.demo.domain.ApplicationStatistics;
import com.example.demo.domain.CharStats;
import com.example.demo.domain.Value;
import com.example.demo.dto.ApplicationStatisticsDTO;
import com.example.demo.interfaces.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Scope("prototype")
public class AnalysingService {

    private final ApplicationStatistics applicationStatistics;

    private final Mapper<ApplicationStatistics, ApplicationStatisticsDTO> mapper;

    @Autowired
    public AnalysingService(ApplicationStatistics applicationStatistics, Mapper<ApplicationStatistics, ApplicationStatisticsDTO> mapper) {
        this.applicationStatistics = applicationStatistics;
        this.mapper = mapper;
    }

    public Analyser analysing(String string) {
        Analyser analyser = new Analyser();
        Long chains = 1L;
        char prevChar = 0;

        for (int i = 0; i < string.length(); i++) {
            Value value;


            if (string.charAt(i) == prevChar && i!=0) {
                chains++;
            } else {
                chains = 1L;
            }

            if (!analyser.getValues().containsKey(string.charAt(i))) {
                value = new Value();
                value.setCounts(1L);
                value.setChains(chains);
            } else {
                value = analyser.getValues().get(string.charAt(i));
                value.setCounts(value.getCounts() + 1);
                if (value.getChains() < chains) {
                    value.setChains(chains);
                }
            }
            analyser.getValues().put(string.charAt(i), value);

            prevChar = string.charAt(i);
        }

        countGlobalStatistics(analyser, string);

        return analyser;
    }


    private void countGlobalStatistics(Analyser analyser, String string) {
        ConcurrentHashMap <Character, CharStats> stats = applicationStatistics.getStats();
        for (Map.Entry<Character, Value> entry : analyser.getValues().entrySet()) {
            stats.computeIfPresent(entry.getKey(), (character, charStats)  -> {
                Value value = entry.getValue();
                charStats = stats.get(entry.getKey());
                charStats.setSumOfChains(charStats.getSumOfChains() + value.getChains());
                charStats.setSumOfLengths(charStats.getSumOfLengths() + string.length());
                charStats.setCount(charStats.getCount() + 1);
                charStats.setAverageChain(charStats.getSumOfChains()/charStats.getCount());
                charStats.setAverageLength((double)charStats.getSumOfLengths()/charStats.getCount());
                return charStats;
            });
            stats.computeIfAbsent(entry.getKey(), character -> new CharStats());
        }
    }

    public ApplicationStatisticsDTO getApplicationStatistics() {
        return mapper.toDto(applicationStatistics);
    }


}
