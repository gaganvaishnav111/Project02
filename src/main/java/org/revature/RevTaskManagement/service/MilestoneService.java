package org.revature.RevTaskManagement.service;

import org.revature.RevTaskManagement.models.Milestone;
import org.revature.RevTaskManagement.models.ResourceNotFoundException;
import org.revature.RevTaskManagement.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneService {

    @Autowired
    private MilestoneRepository milestoneRepository;

    public Milestone createMilestone(Milestone milestone) {
        return milestoneRepository.save(milestone);
    }

    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }

    public Milestone getMilestoneById(int milestoneId) {
        return milestoneRepository.findByMilestoneId(milestoneId);
    }
}
