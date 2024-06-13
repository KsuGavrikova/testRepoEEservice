package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.LessonDto;
import com.senlalab.eeservice.security.JwtService;
import com.senlalab.eeservice.security.UserService;
import com.senlalab.eeservice.service.LessonService;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LessonController.class)
class LessonControllerTest {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private LessonService lessonService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Read Lessons - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void readLessons_ReturnsStatusOk() throws Exception {
        List<LessonDto> lessons = Collections.singletonList(
                LessonDto.builder()
                        .description("Lesson Description")
                        .startDateAndTime(LocalDateTime.now())
                        .cost(10.0)
                        .programId(123L)
                        .build()
        );

        when(lessonService.getAllLessons()).thenReturn(lessons);

        mockMvc.perform(MockMvcRequestBuilders.get("/lessons")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Lesson Description"));
    }


    @Test
    @DisplayName("Create Lesson - Status 201 Created")
    @WithMockUser(username = "username", roles = "USER")
    void createLesson_ReturnsStatusCreated() throws Exception {
        String lessonDtoJson = "{\"title\":\"Test Lesson\",\"description\":\"Test Description\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lessonDtoJson)
                        .with(csrf()))
                .andExpect(status().isCreated());

        verify(lessonService, times(1)).createLesson(any(LessonDto.class));
    }

    @Test
    @DisplayName("Update Lesson - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void updateLesson_ReturnsStatusOk() throws Exception {
        String lessonDtoJson = "{\"description\":\"Updated Lesson Description\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/lessons/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lessonDtoJson)
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(lessonService, times(1)).updateLesson(any(LessonDto.class), eq(123L));
    }

    @Test
    @DisplayName("Delete Lesson - Status 200 OK")
    @WithMockUser(username = "username", roles = "USER")
    void deleteLesson_ReturnsStatusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/lessons/456")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(lessonService, times(1)).deleteLesson(456L);
    }

}
