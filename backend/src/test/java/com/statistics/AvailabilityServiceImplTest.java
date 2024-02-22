package com.statistics;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.statistics.repository.AvailabilityRepository;
import com.statistics.service.AvailabilityServiceImpl;
import com.statistics.model.Availability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AvailabilityServiceImplTest {

    @Mock
    private AvailabilityRepository availabilityRepository;

    @InjectMocks
    private AvailabilityServiceImpl availabilityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAvailability() {
        String mockEan = "123";
        Availability mockAvailability = new Availability();
        mockAvailability.setEan("123");
        mockAvailability.setManufacturer(12);

        when(availabilityRepository.findAvailabilityByEans(mockEan)).thenReturn(mockAvailability);

        Availability result = availabilityService.getAvailability(mockEan);

        assertNotNull(result);
        assertEquals(mockEan, result.getEan());
        assertEquals(12, result.getManufacturer());
    }
}