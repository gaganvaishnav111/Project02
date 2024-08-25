package org.revature.RevTaskManagement.controller;

import jakarta.servlet.http.HttpSession;
import org.revature.RevTaskManagement.models.Project;
import org.revature.RevTaskManagement.models.User;
import org.revature.RevTaskManagement.service.ProjectService;
import org.revature.RevTaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/by-username")
    public List<Project> getProjectsByUsername(@RequestParam("username") String username) {
        return projectService.getProjectsByUsername(username); // Call service method to fetch projects
    }

    @PutMapping("/{projectId}/add-team-member/{userId}")
    public ResponseEntity<String> addTeamMemberToProject(
            @PathVariable int projectId,
            @PathVariable int userId) {
        try {
            String result = projectService.addTeamMemberToProject(projectId, userId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/teamMembers")
    public Set<User> getTeamMembersByProjectName(@RequestParam String projectName) {
        return projectService.getTeamMembersByProjectName(projectName);
    }

    @GetMapping("/getTeamMembersByProjectId")
    public Set<User> getTeamMembersByProjectId(@RequestParam int projectId) {
        return projectService.getTeamMembersByProjectId(projectId);
    }

    @GetMapping("/projectManager")
    public User getProjectManagerByTeamMember(@RequestParam String username) {
        return projectService.getProjectManagerByTeamMemberUsername(username);
    }

    @GetMapping("/user/{username}")
    public List<Project> getProjectsByUsernameget(@PathVariable String username) {
        return projectService.getProjectsByUsernameget(username);
    }

    @GetMapping("/project-managers/team-member/{username}")
    public List<User> getProjectManagersByTeamMemberUsername(@PathVariable String username) {
        return projectService.getAllProjectManagersByTeamMemberUsername(username);
    }

    @GetMapping("/{projectId}/manager")
    public ResponseEntity<User> getProjectManagerByProjectId(@PathVariable int projectId) {
        User projectManager = projectService.getProjectManagerByProjectId(projectId);
        return ResponseEntity.ok(projectManager);
    }
}
