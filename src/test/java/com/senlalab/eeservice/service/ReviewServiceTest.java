package com.senlalab.eeservice.service;

import com.senlalab.eeservice.dto.ProgramDto;
import com.senlalab.eeservice.dto.ProgramDtoToUpdate;
import com.senlalab.eeservice.dto.ReviewDto;
import com.senlalab.eeservice.dto.response.DirectoryDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.mapper.ProgramMapper;
import com.senlalab.eeservice.model.Person;
import com.senlalab.eeservice.model.Program;
import com.senlalab.eeservice.model.Topic;
import com.senlalab.eeservice.repository.ProgramRepository;
import com.senlalab.eeservice.service.impl.ProgramServiceImpl;
import com.senlalab.eeservice.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReviewServiceTest {
    @Autowired
    private ReviewServiceImpl reviewService;

    @Test
    public void getReviewService() {
        List<ReviewDto> reviews = reviewService.getAllReviews();
        Assertions.assertEquals(reviews.size(), 1);
        System.out.println();
        reviews.forEach(System.out::println);
        System.out.println();
    }

    @SpringBootTest
    public static class ProgramServiceImplTest {

        @Mock
        private ProgramMapper programMapper;

        @Mock
        private ProgramRepository programRepository;

        @InjectMocks
        private ProgramServiceImpl programService;

        private ProgramDto programDto;
        private ProgramDtoToUpdate programDtoToUpdate;
        private Program program;

        @BeforeEach
        void setUp() {
            programDtoToUpdate = ProgramDtoToUpdate.builder()
                    .name("Test Program")
                    .isIndividual(true)
                    .isVisibility(true)
                    .authorId(2L)
                    .topicId(1L)
                    .build();
            programDto = ProgramDto.builder()
                    .name("Test Program")
                    .topicId(1L)
                    .build();
            program = Program.builder()
                    .name("Test Program")
                    .isIndividual(true)
                    .isVisibility(true)
                    .author(new Person())
                    .topic(new Topic())
                    .build();
        }

        @Test
        @DisplayName("Get All Programs - Success")
        void getAllPrograms_Success() {
            List<Program> programs = new ArrayList<>();
            programs.add(program);

            when(programRepository.findAll()).thenReturn(programs);

            List<Program> returnedPrograms = programService.getAllPrograms();

            assertEquals(programs.size(), returnedPrograms.size());
            assertEquals(programs.get(0), returnedPrograms.get(0));
        }

        @Test
        @DisplayName("Find All Directories By Topic ID - Success")
        void findAllDirectoriesByTopicId_Success() {
            Long topicId = 1L;
            List<Program> programs = new ArrayList<>();
            programs.add(program);

            when(programRepository.findAllByTopicId(topicId)).thenReturn(Optional.of(programs));

            List<DirectoryDto> directoryDtos = new ArrayList<>();
            directoryDtos.add(DirectoryDto.builder()
                    .name("Test Program")
                    .build());

            when(programMapper.entityListToDto(programs)).thenReturn(directoryDtos);

            List<DirectoryDto> returnedDirectories = programService.getAllProgramByTopic(topicId);

            assertEquals(directoryDtos.size(), returnedDirectories.size());
            assertEquals(directoryDtos.get(0), returnedDirectories.get(0));
        }

        @Test
        @DisplayName("Create Program - Success")
        void createProgram_Success() {
            when(programMapper.dtoToEntity(programDto)).thenReturn(program);
            when(programRepository.save(program)).thenReturn(program);

            assertDoesNotThrow(() -> programService.createProgram(programDtoToUpdate));
            verify(programRepository, times(1)).save(program);
        }

        @Test
        @DisplayName("Get Program By ID - Success")
        void getProgramById_Success() {
            Long id = 1L;

            when(programRepository.findById(id)).thenReturn(Optional.of(program));
            when(programMapper.entityToDto(program)).thenReturn(programDto);

            ProgramDto retrievedProgram = programService.getProgramById(id);

            assertEquals(programDto, retrievedProgram);
        }

        @Test
        @DisplayName("Update Program - Success")
        void updateProgram_Success() {
            Long id = 1L;

            Program program = new Program();
            program.setId(id);
            when(programRepository.findById(id)).thenReturn(Optional.of(program));

            when(programMapper.dtoToEntity(programDto)).thenReturn(new Program());

            when(programRepository.save(any(Program.class))).thenAnswer(invocation -> invocation.getArgument(0));

            assertDoesNotThrow(() -> programService.updateProgram(programDtoToUpdate, id));

            verify(programRepository, times(1)).save(any(Program.class));
        }


        @Test
        @DisplayName("Update Program - Not Found")
        void updateProgram_NotFound() {
            Long id = 1L;

            when(programRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(EntryNotFoundException.class, () -> programService.updateProgram(programDtoToUpdate, id));
        }

        @Test
        @DisplayName("Delete Program - Success")
        void deleteProgram_Success() {
            Long id = 1L;

            when(programRepository.findById(id)).thenReturn(Optional.of(program));
            assertDoesNotThrow(() -> programService.deleteProgram(id));
            verify(programRepository, times(1)).deleteById(id);
        }

        @Test
        @DisplayName("Delete Program - Not Found")
        void deleteProgram_NotFound() {
            Long id = 1L;

            when(programRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(EntryNotFoundException.class, () -> programService.deleteProgram(id));
        }
    }
}
