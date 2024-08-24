package org.revature.RevTaskManagement.service;

import org.revature.RevTaskManagement.models.Project;
import org.revature.RevTaskManagement.models.ResourceNotFoundException;
import org.revature.RevTaskManagement.models.User;
import org.revature.RevTaskManagement.repository.ProjectRepository;
import org.revature.RevTaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByUsername(String username) {
        return projectRepository.findProjectsByUsername(username);
    }

    public String addTeamMemberToProject(int projectId, int userId) throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("Project not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));
        project.getTeamMembers().add(user);
        projectRepository.save(project);

        return "Team member added successfully!";
    }

    public Set<User> getTeamMembersByProjectName(String projectName) {
        Project project = projectRepository.findByProjectName(projectName);
        if (project != null) {
            return project.getTeamMembers();
        } else {
            return Set.of();
        }
    }

    public Set<User> getTeamMembersByProjectId(int projectId) {
        Project project = projectRepository.findByProjectId(projectId);
        if (project != null) {
            return project.getTeamMembers();
        }
        return null;
    }

    public Project getProjectById(int projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    // Method to assign a user to a project
    public Project assignUserToProject(int projectId, int userId) throws Exception {
        // Fetch the project by projectId
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (!optionalProject.isPresent()) {
            throw new Exception("Project not found");
        }
        Project project = optionalProject.get();

        // Fetch the user by userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new Exception("User not found");
        }
        User user = optionalUser.get();

        // Add the user to the project's team members
        project.getTeamMembers().add(user);

        // Save the updated project
        return projectRepository.save(project);
    }

    // Method to get all users for selection
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String removeTeamMemberFromProject(int projectId, int userId) throws Exception {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (project.getTeamMembers().contains(user)) {
            project.getTeamMembers().remove(user);
            projectRepository.save(project);
            return "Team member removed successfully!";
        } else {
            throw new Exception("Team member not found in the project");
        }
    }
}