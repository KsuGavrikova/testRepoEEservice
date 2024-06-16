package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.security.JwtService;
import com.senlalab.eeservice.security.UserService;
import com.senlalab.eeservice.service.ProgramService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
class ProgramControllerTest {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private ProgramService programService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Read Directories By Topic - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void getAllProgramByTopic_ReturnsStatusOk() throws Exception {
        List<DirectoryDto> directories = Collections.singletonList((DirectoryDto.builder().name("Test Directory").build()));
        when(programService.getAllProgramByTopic(anyLong())).thenReturn(directories);

        mockMvc.perform(MockMvcRequestBuilders.get("/123")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test Directory"));
    }


    @Test
    @DisplayName("Create Program - Status 201 Created")
    @WithMockUser(username = "username", roles = "USER")
    void createProgram_ReturnsStatusCreated() throws Exception {
        String programDtoJson = "{\"name\":\"Test Program\",\"isVisibility\":true,\"isIndividual\":false,\"authorId\":123,\"topicId\":456}";
        mockMvc.perform(MockMvcRequestBuilders.post("/programs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(programDtoJson)
                        .with(csrf()))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("Read Program By Id - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void getProgram_ReturnsStatusOk() throws Exception {
        ProgramDto programDto = ProgramDto.builder()
                .name("Test Program")
                .isIndividual(false)
                .authorId(123L)
                .topicId(456L)
                .build();

        when(programService.getProgramById(anyLong())).thenReturn(programDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/programs/789")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Program"));
    }


    @Test
    @DisplayName("Update Program - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void updateProgram_ReturnsStatusOk() throws Exception {
        String programDtoJson = "{\"name\":\"Updated Program Name\",\"isVisibility\":true,\"isIndividual\":false,\"authorId\":123,\"topicId\":456}";
        mockMvc.perform(MockMvcRequestBuilders.post("/directory/programs/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(programDtoJson)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete Program - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void deleteProgram_ReturnsStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/programs/123")
                        .with(csrf())).
                andExpect(status().isOk());

        verify(programService, times(1)).deleteProgram(123L);
    }

}

