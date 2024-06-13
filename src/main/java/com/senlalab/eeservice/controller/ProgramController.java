package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.dto.ProgramDtoToUpdate;
import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.service.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramDtoToUpdate createProgram(@Valid @RequestBody ProgramDtoToUpdate programDto) {
        log.info("Received request to create program: {}", programDto);
        programService.createProgram(programDto);
        log.info("Program created successfully: {}", programDto);
        return programDto;
    }

    @GetMapping("/topic/{topic_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectoryDto> getProgramsByTopic(@PathVariable("topic_id") Long id) {
        log.info("Received request to get programs by topic id {}", id);
        List<DirectoryDto> programs = programService.getAllProgramByTopic(id);
        log.info("Retrieved {} get programs", programs.size());
        return programs;
    }

    @GetMapping("/{program_id}")
    @ResponseStatus(HttpStatus.OK)
    public ProgramDto getProgram(@PathVariable("program_id") Long id) {
        log.info("Received request to read program with id {}", id);
        ProgramDto programDto = programService.getProgramById(id);
        log.info("Program with id {} found: {}", id, programDto);
        return programDto;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DirectoryDto> getVisibilityPrograms() {
        log.info("Received request to get visibility program");
        List<DirectoryDto> programs = programService.getVisibilityProgram();
        log.info("Retrieved {} get visibility programs", programs.size());
        return programs;
    }

    @GetMapping("/individual")
    @ResponseStatus(HttpStatus.OK)
    public List<DirectoryDto> getIndividualProgram() {
        log.info("Received request to get individual program");
        List<DirectoryDto> programs = programService.getIndividualProgram();
        log.info("Retrieved {} get individual programs", programs.size());
        return programs;
    }

    @PostMapping("/{program_id}")
    @ResponseStatus(HttpStatus.OK)
    public ProgramDtoToUpdate updateProgram(@PathVariable(name = "program_id") Long id,
                                            @Valid @RequestBody ProgramDtoToUpdate programDto) {
        log.info("Received request to update program with id {}: {}", id, programDto);
        programService.updateProgram(programDto, id);
        log.info("Program with id {} updated successfully", id);
        return programDto;
    }

    @DeleteMapping("/{program_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProgram(@PathVariable("program_id") Long id) {
        log.info("Received request to delete program with id {}", id);
        programService.deleteProgram(id);
        log.info("Program with id {} deleted successfully", id);
    }
}
