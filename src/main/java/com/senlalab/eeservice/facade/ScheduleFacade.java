package com.senlalab.eeservice.facade;

import com.senlalab.eeservice.dto.ScheduleDto;
import com.senlalab.eeservice.service.LessonService;
import com.senlalab.eeservice.service.ProgramService;
import com.senlalab.eeservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleFacade {

    private final SubscriptionService subscriptionService;
    private final LessonService lessonService;
    private final ProgramService programService;

    public List<ScheduleDto> getSchedule(Long id) {

        List<ScheduleDto> subs = subscriptionService.getAllByPersonId(id)
                .stream()
                .map(subscribeDto -> lessonService.getAllByProgramId(subscribeDto.getProgramId()))
                .flatMap(Collection::stream)
                .map(lessonDto -> new ScheduleDto(programService.getById(lessonDto.getProgramId()).getName(),
                        lessonDto.getStartDateAndTime()))
                .toList();

        return subs;
    }
}
