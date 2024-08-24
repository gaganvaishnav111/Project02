package org.revature.RevTaskManagement.controller;

import org.revature.RevTaskManagement.models.*;
import org.revature.RevTaskManagement.service.MilestoneService;
import org.revature.RevTaskManagement.service.ProjectService;
import org.revature.RevTaskManagement.service.TaskService;
import org.revature.RevTaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;
    @Autowired
    private MilestoneService milestoneService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            Task task = new Task();
            task.setTaskName(taskDTO.getTaskName());
            task.setTaskDetails(taskDTO.getTaskDetails());
            task.setStartDate(taskDTO.getStartDate());
            task.setDueDate(taskDTO.getDueDate());

            Project project = projectService.getProjectById(taskDTO.getProjectId());
            if (project == null) {
                return ResponseEntity.badRequest().body("Project not found with ID: " + taskDTO.getProjectId());
            }
            task.setProject(project);

            User assignedTo = userService.getUserById(taskDTO.getAssignedToId());
            if (assignedTo == null) {
                return ResponseEntity.badRequest().body("User not found with ID: " + taskDTO.getAssignedToId());
            }
            task.setAssignedTo(assignedTo);

            Milestone milestone = milestoneService.getMilestoneById(taskDTO.getMilestoneId());
            if (milestone == null) {
                return ResponseEntity.badRequest().body("Milestone not found with ID: " + taskDTO.getMilestoneId());
            }
            task.setMilestone(milestone);

            Task createdTask = taskService.createTask(task);
            return ResponseEntity.ok(createdTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating task: " + e.getMessage());
        }
    }



    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/by-username/{username}")
    public List<Task> getTasksByUsername(@PathVariable String username) {
        return taskService.getTasksByUsername(username);
    }

    @PutMapping("/update-milestone")
    public Task updateTaskMilestone(
            @RequestParam int taskId,
            @RequestParam int milestoneId) {
        return taskService.updateTaskMilestone(taskId, milestoneId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            Task task = taskService.getTaskById(id);
            if (task == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving task: " + e.getMessage());
        }
    }
}