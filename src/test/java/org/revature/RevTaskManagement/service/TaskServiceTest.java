package org.revature.RevTaskManagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revature.RevTaskManagement.models.Milestone;
import org.revature.RevTaskManagement.models.Task;
import org.revature.RevTaskManagement.repository.TaskRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private MilestoneService milestoneService;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertEquals(task, result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    public void testGetTasksByUsername() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findTasksByUsername("username")).thenReturn(tasks);

        List<Task> result = taskService.getTasksByUsername("username");

        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findTasksByUsername("username");
    }

    @Test
    public void testUpdateTaskMilestone() {
        Task task = new Task();
        Milestone milestone = new Milestone();
        when(taskRepository.findByTaskId(1)).thenReturn(task);
        when(milestoneService.getMilestoneById(1)).thenReturn(milestone);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.updateTaskMilestone(1, 1);

        assertEquals(task, result);
        verify(taskRepository, times(1)).save(task);
    }
}
