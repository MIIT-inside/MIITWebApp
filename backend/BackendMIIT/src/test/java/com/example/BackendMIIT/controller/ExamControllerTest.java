package com.example.BackendMIIT.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ExamController.class)
@ExtendWith(MockitoExtension.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllExams() throws Exception {
        //TODO
    }

    @Test
    public void getExamsByDirections() throws Exception {
        //TODO
    }
}