package com.senlalab.eeservice.controller;

import com.senlalab.eeservice.dto.DirectoryDto;
import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<ProgramDto> createProgram(@RequestBody ProgramDto programDto) {
        programService.create(programDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(programDto);
    }

    @GetMapping("/topic/{topic_id}")
    public ResponseEntity<List<DirectoryDto>> read(@PathVariable("topic_id") Long id) {
        return ResponseEntity.ok(programService.getListByTopicId(id));
    }

    @GetMapping("/{program_id}")
    public ResponseEntity<ProgramDto> readProgram(@PathVariable("program_id") Long id) {
        return ResponseEntity.ok(programService.getById(id));
    }

    @PostMapping("/{program_id}")
    public ResponseEntity<ProgramDto> updateProgram(@PathVariable(name = "program_id") Long id,
                                                    @RequestBody ProgramDto programDto) {
        programService.update(programDto, id);
        return ResponseEntity.ok(programDto);
    }

    @DeleteMapping("/{program_id}")
    public ResponseEntity<Void> delete(@PathVariable("program_id") Long id) {
        programService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
