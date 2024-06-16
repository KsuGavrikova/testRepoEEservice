package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.PersonDto;
import com.senlalab.eeservice.security.JwtService;
import com.senlalab.eeservice.security.UserService;
import com.senlalab.eeservice.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get All Persons - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void getAllPersons_ReturnsStatusOk() throws Exception {
        List<PersonDto> persons = Collections.singletonList(
                PersonDto.builder()
                        .fullName("John Doe")
                        .rating(5)
                        .accountNumber("1234567890")
                        .salaryId(1L)
                        .build()
        );

        when(personService.getAllPerson()).thenReturn(persons);

        mockMvc.perform(MockMvcRequestBuilders.get("/people/persons")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rating").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accountNumber").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].salaryId").value(1));
    }


    @Test
    @DisplayName("Update Person - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void updatePerson_ReturnsStatusOk() throws Exception {
        String personDtoJson = "{\"name\":\"Jane Doe\",\"email\":\"jane@example.com\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/people/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personDtoJson)
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(personService, times(1)).updatePerson(any(PersonDto.class), eq(123L));
    }
}
