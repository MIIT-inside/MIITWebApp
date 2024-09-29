package com.example.BackendMIIT.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = DepartmentController.class)
@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

}
