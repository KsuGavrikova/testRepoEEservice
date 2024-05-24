package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.exception.MyRepoException;
import com.senlalab.eeservice.mapper.ProgramMapper;
import com.senlalab.eeservice.model.Program;
import com.senlalab.eeservice.model.Topic;
import com.senlalab.eeservice.repository.ProgramRepository;
import com.senlalab.eeservice.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProgramServiceImpl implements ProgramService {

    private final ProgramMapper programMapper;
    private final ProgramRepository programRepository;

    public List<Program> getAll() {
        return programRepository.findAll();
    }

    @Override
    public List<DirectoryDto> getList(Topic topic) {
        return programMapper.entityListToDto(programRepository.findAll().stream()
                .filter(x -> x.getTopic().equals(topic))
                .toList());
    }

    @Override
    public List<DirectoryDto> getListByTopicId(Long topicId) {
        return programMapper.entityListToDto(programRepository.findAllByTopicId(topicId)
                .orElseThrow(() -> new EntryNotFoundException("Program with " + topicId + " not found")));
    }


    public void create(ProgramDto programDto) {
        programRepository.save(programMapper.dtoToEntity(programDto));
        log.warn("Program " + programDto + " was created");
    }

    public ProgramDto getById(Long id) {
        return programMapper.entityToDto(programRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Program", id)));
    }

    @Override
    public boolean update(ProgramDto programDto, Long id) {
        try {
            Program program = programRepository.findById(id)
                    .orElseThrow(() -> new EntryNotFoundException("Program", id));
            Program newProgram = programMapper.dtoToEntity(programDto);
            newProgram.setId(id);
            newProgram.setAuthor(program.getAuthor());
            newProgram.setTopic(program.getTopic());
            programRepository.save(newProgram);
            log.warn("Program " + newProgram + " was update");
            return true;
        } catch (MyRepoException e) {
            log.warn("Program " + programDto + " no update");
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        programRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Program", id));
        try {
            programRepository.deleteById(id);
            log.warn("Program with id" + id + " was delete");
            return true;
        } catch (MyRepoException e) {
            log.warn("Program with id" + id + " no delete");
            return false;
        }
    }
}
