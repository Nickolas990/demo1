package com.testcase.testcasecgm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharStats {
    private Long count = 1L;
    private Long averageLength = 1L;

    private Double averageChain = 1.0;

    private Double sumOfChains = 0.0;
    private Long sumOfLengths = 0L;
}
