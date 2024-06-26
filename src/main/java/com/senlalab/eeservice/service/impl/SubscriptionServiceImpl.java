package com.senlalab.eeservice.service.impl;

import com.senlalab.eeservice.dto.SubscribeDto;
import com.senlalab.eeservice.exception.MyRepoException;
import com.senlalab.eeservice.mapper.SubscribeMapper;
import com.senlalab.eeservice.repository.SubscriptionRepository;
import com.senlalab.eeservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscribeMapper subscribeMapper;

    @Override
    public List<SubscribeDto> getAll() {
        return subscribeMapper.entityListToDto(subscriptionRepository.findAll());
    }

    @Override
    public void create(SubscribeDto subscribeDto) {
        try {
            subscriptionRepository.save(subscribeMapper.dtoToEntity(subscribeDto));
            log.warn("Subscribe " + subscribeDto + " was created");
        } catch (MyRepoException e) {
            log.warn("Subscribe " + subscribeDto + " no created");
        }
    }

    @Override
    public List<SubscribeDto> getAllByPersonId(Long personId) {
        return subscribeMapper.entityListToDto(subscriptionRepository.findAllByPersonId(personId));
    }

}
