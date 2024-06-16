package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.security.JwtService;
import com.senlalab.eeservice.security.UserService;
import com.senlalab.eeservice.service.TopicService;
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

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
class TopicControllerTest {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private TopicService topicService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create Topic - Status 201 Created")
    @WithMockUser(username = "username", roles = "USER")
    void createTopic_ReturnsStatusCreated() throws Exception {
        String topicDtoJson = "{\"name\":\"Test Topic\",\"parentId\":null}";

        mockMvc.perform(MockMvcRequestBuilders.post("/directory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(topicDtoJson)
                        .with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Read Root Topics - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void getRootTopics_ReturnsStatusOk() throws Exception {
        List<DirectoryDto> rootTopics = Collections.singletonList(DirectoryDto.builder().name("Root Topic").build());
        when(topicService.getRootTopics()).thenReturn(rootTopics);

        mockMvc.perform(MockMvcRequestBuilders.get("/directory")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Root Topic"));


    }

    @Test
    @DisplayName("Update Topic - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void updateTopic_ReturnsStatusOk() throws Exception {
        String topicDtoJson = "{\"name\":\"Updated Topic Name\",\"parentId\":null}";
        mockMvc.perform(MockMvcRequestBuilders.post("/directory/topics/456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(topicDtoJson)
                        .with(csrf()))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Delete Topic - Status 204 No Content")
    @WithMockUser(username = "username", roles = "USER")
    void deleteTopic_ReturnsStatusNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/directory/topics/789")
                        .with(csrf()))
                .andExpect(status().isNoContent());
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

}

