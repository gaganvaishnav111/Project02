package org.revature.RevTaskManagement.service;

import org.revature.RevTaskManagement.models.Project;
import org.revature.RevTaskManagement.models.ResourceNotFoundException;
import org.revature.RevTaskManagement.models.User;
import org.revature.RevTaskManagement.repository.ProjectRepository;
import org.revature.RevTaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Set;@Service
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

    public User getProjectManagerByTeamMemberUsername(String username) {
        return projectRepository.findProjectManagerByTeamMemberUsername(username);
    }

    public List<Project> getProjectsByUsernameget(String username) {
        return projectRepository.findProjectsByUsernameget(username);
    }

    public List<User> getAllProjectManagersByTeamMemberUsername(String username) {
        return projectRepository.findAllProjectManagersByTeamMemberUsername(username);
    }

    public User getProjectManagerByProjectId(int projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return project.getProjectManager();
    }
}