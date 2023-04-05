package com.example.demo.domain;



import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
@Setter
public class ApplicationStatistics {
    private ConcurrentHashMap<Character, CharStats> stats = new ConcurrentHashMap<>();
}
