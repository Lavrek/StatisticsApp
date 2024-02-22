package com.statistics.service;

import com.statistics.repository.AvailabilityRepository;
import com.statistics.model.Availability;
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
