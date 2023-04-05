package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharStats {
    private Long count = 1L;
    private Double averageLength = 1.0;

    private Double averageChain = 1.0;

    private Double sumOfChains = 0.0;
    private Long sumOfLengths = 0L;
}
