package com.senlalab.eeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senlalab.eeservice.dto.SalaryDto;
import com.senlalab.eeservice.security.JwtService;
import com.senlalab.eeservice.security.UserService;
import com.senlalab.eeservice.service.SalaryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalaryController.class)
@AutoConfigureMockMvc
class SalaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SalaryService salaryService;

    @Test
    @DisplayName("Create Salary - Status 201 Created")
    @WithMockUser(username = "username", roles = "USER")
    void createSalary_ReturnsStatusCreated() throws Exception {
        SalaryDto salaryDto = SalaryDto.builder()
                .isFixed(true)
                .amount(1000.0)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/salaries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salaryDto))
                        .with(csrf()))
                .andExpect(status().isCreated());

        verify(salaryService, times(1)).createSalary(salaryDto);
    }
}
