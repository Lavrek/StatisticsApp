package com.vodahory.statistics.service;

import com.vodahory.statistics.model.Availability;
import com.vodahory.statistics.repository.AvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    AvailabilityRepository availabilityRepository;

    @Override
    public Availability getAvailability(String ean) {
        return availabilityRepository.findAvailabilityByEans(ean);
    }
}
