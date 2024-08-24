package org.revature.RevTaskManagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revature.RevTaskManagement.models.Milestone;
import org.revature.RevTaskManagement.repository.MilestoneRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MilestoneServiceTest {

    @Mock
    private MilestoneRepository milestoneRepository;

    @InjectMocks
    private MilestoneService milestoneService;

    @Test
    public void testCreateMilestone() {
        Milestone milestone = new Milestone();
        when(milestoneRepository.save(milestone)).thenReturn(milestone);

        Milestone result = milestoneService.createMilestone(milestone);

        assertEquals(milestone, result);
        verify(milestoneRepository, times(1)).save(milestone);
    }

    @Test
    public void testGetAllMilestones() {
        List<Milestone> milestones = Arrays.asList(new Milestone(), new Milestone());
        when(milestoneRepository.findAll()).thenReturn(milestones);

        List<Milestone> result = milestoneService.getAllMilestones();

        assertEquals(milestones, result);
        verify(milestoneRepository, times(1)).findAll();
    }

    @Test
    public void testGetMilestoneById() {
        Milestone milestone = new Milestone();
        when(milestoneRepository.findByMilestoneId(1)).thenReturn(milestone);

        Milestone result = milestoneService.getMilestoneById(1);

        assertEquals(milestone, result);
        verify(milestoneRepository, times(1)).findByMilestoneId(1);
    }
}
