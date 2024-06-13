package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.SalaryDto;
import com.senlalab.eeservice.mapper.SalaryMapper;
import com.senlalab.eeservice.repository.SalaryRepository;
import com.senlalab.eeservice.service.SalaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;

    private final SalaryMapper salaryMapper;

    @Override
    public List<SalaryDto> getAllSalaries() {
        List<SalaryDto> salaries = salaryMapper.entityListToDto(salaryRepository.findAll());
        log.info("Retrieved {} salaries", salaries.size());
        return salaries;
    }

    @Override
    public void createSalary(SalaryDto salaryDto) {
        salaryRepository.save(salaryMapper.dtoToEntity(salaryDto));
        log.info("Salary {} was created", salaryDto);
    }
}
