package com.senlalab.eeservice.service;

import com.senlalab.eeservice.model.Program;
import com.senlalab.eeservice.service.impl.ProgramServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProgramServiceImplTest {
    @Autowired
    private ProgramServiceImpl programServiceImpl;

    @Test
    public void getAuthorizationService() {
        List<Program> programs = programServiceImpl.getAll();
        Assertions.assertEquals(programs.size(), 2);
        System.out.println();
        programs.forEach(System.out::println);
        System.out.println();
    }
}
