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
    public List<SalaryDto> getAll() {
        return salaryMapper.entityListToDto(salaryRepository.findAll());
    }

    @Override
    public void create(SalaryDto salaryDto) {
        salaryRepository.save(salaryMapper.dtoToEntity(salaryDto));
        log.warn("Salary " + salaryDto + " was create");
    }
}
