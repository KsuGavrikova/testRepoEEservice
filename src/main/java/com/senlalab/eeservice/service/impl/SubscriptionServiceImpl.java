package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.ScheduleDto;
import com.senlalab.eeservice.dto.SubscribeDto;
import com.senlalab.eeservice.exception.EntryNotFoundException;
import com.senlalab.eeservice.exception.MyRepoException;
import com.senlalab.eeservice.mapper.SubscribeMapper;
import com.senlalab.eeservice.model.Subscription;
import com.senlalab.eeservice.repository.SubscriptionRepository;
import com.senlalab.eeservice.service.LessonService;
import com.senlalab.eeservice.service.ProgramService;
import com.senlalab.eeservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscribeMapper subscribeMapper;

    private final LessonService lessonService;

    private final ProgramService programService;

    @Override
    public List<SubscribeDto> getAllSubscriptions() {
        List<Subscription> subscriptionList = subscriptionRepository.findAll();
        log.info("Retrieved {} subscriptions", subscriptionList.size());
        return subscribeMapper.entityListToDto(subscriptionList);
    }

    @Override
    public void createSubscription(SubscribeDto subscribeDto) {
        subscriptionRepository.save(subscribeMapper.dtoToEntity(subscribeDto));
        log.info("Subscribe {} was created", subscribeDto);
    }

    @Override
    public List<SubscribeDto> getAllByPersonId(Long personId) {
        return subscribeMapper.entityListToDto(subscriptionRepository.findAllByPersonId(personId)
                .orElseThrow(EntryNotFoundException::new));
    }

    @Override
    public List<ScheduleDto> getSchedule(Long id) {
        return  getAllByPersonId(id)
                .stream()
                .map(subscribeDto -> lessonService.getAllLessonsByProgramId(subscribeDto.getProgramId()))
                .flatMap(Collection::stream)
                .map(lessonDto -> new ScheduleDto(programService.getProgramById(lessonDto.getProgramId()).getName(),
                        lessonDto.getStartDateAndTime()))
                .toList();
    }

}
