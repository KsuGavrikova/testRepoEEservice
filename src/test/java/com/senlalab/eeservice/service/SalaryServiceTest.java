package com.senlalab.eeservice.service;


import com.senlalab.eeservice.dto.SalaryDto;
import com.senlalab.eeservice.service.impl.SalaryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SalaryServiceTest {
    @Autowired
    private SalaryServiceImpl salaryService;

    @Test
    public void whenApplicationStarts_thenGetSalary() {
        List<SalaryDto> salaryList = salaryService.getAllSalaries();
        Assertions.assertEquals(salaryList.size(), 2);
        System.out.println();
        salaryList.forEach(System.out::println);
        System.out.println();
    }
}
