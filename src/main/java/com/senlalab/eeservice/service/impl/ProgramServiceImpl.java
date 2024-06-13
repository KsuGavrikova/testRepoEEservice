package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.dto.ProgramDtoToUpdate;
import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.exception.MyRepoException;
import com.senlalab.eeservice.mapper.ProgramMapper;
import com.senlalab.eeservice.model.Program;
import com.senlalab.eeservice.repository.ProgramRepository;
import com.senlalab.eeservice.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProgramServiceImpl implements ProgramService {

    private final ProgramMapper programMapper;

    private final ProgramRepository programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public List<DirectoryDto> getVisibilityProgram() {
        return programMapper.entityListToDto(programRepository.findAll()
                .stream()
                .filter(Program::getIsVisibility)
                .toList());
    }

    @Override
    public List<DirectoryDto> getIndividualProgram() {
        return programMapper.entityListToDto(programRepository.findAll()
                .stream()
                .filter(Program::getIsIndividual)
                .toList());
    }

    @Override
    public List<DirectoryDto> getAllProgramByTopic(Long topicId) {
        return programMapper.entityListToDto(programRepository.findAllByTopicId(topicId)
                .orElseThrow(() -> new EntryNotFoundException("Program with " + topicId + " not found")));
    }

    @Override
    public void createProgram(ProgramDtoToUpdate programDto) {
        programRepository.save(programMapper.dtoToEntity(programDto));
        log.info("Program " + programDto + " was created");
    }

    @Override
    public ProgramDto getProgramById(Long id) {
        return programMapper.entityToDto(programRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Program", id)));
    }

    @Override
    @Transactional
    public boolean updateProgram(ProgramDtoToUpdate programDto, Long id) {
        if (programRepository.findById(id).isPresent()) {
            Program program = programRepository.findById(id)
                    .orElseThrow(() -> new EntryNotFoundException("Program", id));
            Program newProgram = programMapper.dtoToEntity(programDto);
            newProgram.setId(id);
            newProgram.setAuthor(program.getAuthor());
            newProgram.setTopic(program.getTopic());
            programRepository.save(newProgram);
            log.info("Program " + newProgram + " was update");
            return true;
        }
        log.info("Program " + programDto + " was not updated");
        throw new EntryNotFoundException("Program", id);
    }

    @Override
    public boolean deleteProgram(Long id) {
        programRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Program", id));
        try {
            programRepository.deleteById(id);
            log.info("Program with id" + id + " was delete");
            return true;
        } catch (MyRepoException e) {
            log.info("Program with id" + id + " no delete");
            return false;
        }
    }

}
