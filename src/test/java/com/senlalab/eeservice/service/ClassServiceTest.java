package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.LessonDto;
import com.senlalab.eeservice.service.impl.LessonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClassServiceTest {
    @Autowired
    private LessonServiceImpl classService;

    @Test
    public void getAllClasses() {
        List<LessonDto> classList = classService.getAllLessons();
        Assertions.assertEquals(classList.size(), 4);
        System.out.println();
        classList.forEach(System.out::println);
        System.out.println();
    }
}
