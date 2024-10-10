package com.example.BackendMIIT.controller;

import com.example.BackendMIIT.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ProfileController.class)
@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @Test
    public void getAllProfiles() throws Exception {
        //TODO
    }

    @Test
    public void getProfilesByPassPoint() throws Exception {
        //TODO
    }

    @Test
    public void getProfileByName() throws Exception {
        //TODO
    }
}
