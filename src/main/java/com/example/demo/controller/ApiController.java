package com.example.demo.controller;



import com.example.demo.domain.Analyser;
import com.example.demo.dto.ApplicationStatisticsDTO;
import com.example.demo.services.AnalysingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
     private AnalysingService analysingService;

    @Autowired
    public ApiController(AnalysingService analysingService) {
        this.analysingService = analysingService;
    }

    @Async
    @GetMapping(value = "/analyse/{input_string}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Analyser> analyse(@PathVariable("input_string") String inputString) {
           return ResponseEntity.ok(analysingService.analysing(inputString));
    }

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicationStatisticsDTO> statistics () {
        return ResponseEntity.ok(analysingService.getApplicationStatistics());
    }
}
