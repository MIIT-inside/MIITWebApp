package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.ExamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ExamController.class)
@ExtendWith(MockitoExtension.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @Test
    public void getAllExams() throws Exception {
        //TODO
    }

    @Test
    public void getExamsByDirections() throws Exception {
        //TODO
    }
}
