package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.DirectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = DirectionController.class)
@ExtendWith(MockitoExtension.class)
public class DirectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectionService directionService;

    @Test
    public void getAllDirectionsTest() throws Exception {
        //TODO
    }

    @Test
    public void getDirectionsByPassPointTest() throws Exception {
        //TODO
    }

    @Test
    public void getDirectionByName() throws Exception {
        //TODO
    }
}
