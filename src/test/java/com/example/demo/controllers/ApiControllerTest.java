package com.example.demo.controllers;

import com.example.demo.services.AnalysingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnalysingService analysingService;

}
