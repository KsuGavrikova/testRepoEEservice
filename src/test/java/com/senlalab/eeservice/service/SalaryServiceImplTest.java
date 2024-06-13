package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.SalaryDto;
import com.senlalab.eeservice.mapper.SalaryMapper;
import com.senlalab.eeservice.model.Salary;
import com.senlalab.eeservice.repository.SalaryRepository;
import com.senlalab.eeservice.service.impl.SalaryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SalaryServiceImplTest {

    @Mock
    private SalaryRepository salaryRepository;

    @Mock
    private SalaryMapper salaryMapper;

    @InjectMocks
    private SalaryServiceImpl salaryService;

    private SalaryDto salaryDto;
    private Salary salary;

    @BeforeEach
    void setUp() {
        salaryDto = SalaryDto.builder()
                .amount(1000.0)
                .isFixed(true)
                .build();

        salary = Salary.builder()
                .amount(1000.0)
                .isFixed(true)
                .build();
    }

    @Test
    @DisplayName("Create Salary - Success")
    void createSalary_Success() {
        when(salaryMapper.dtoToEntity(salaryDto)).thenReturn(salary);

        assertDoesNotThrow(() -> salaryService.createSalary(salaryDto));

        verify(salaryRepository, times(1)).save(salary);
    }

    @Test
    @DisplayName("Get All Salaries - Success")
    void getAllSalaries_Success() {
        List<Salary> salaries = new ArrayList<>();

        when(salaryRepository.findAll()).thenReturn(salaries);

        List<SalaryDto> returnedSalaries = salaryService.getAllSalaries();

        assertEquals(salaries.size(), returnedSalaries.size());
    }


}
