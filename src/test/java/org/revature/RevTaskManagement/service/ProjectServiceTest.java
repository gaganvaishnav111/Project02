package org.revature.RevTaskManagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revature.RevTaskManagement.models.Project;
import org.revature.RevTaskManagement.models.User;
import org.revature.RevTaskManagement.repository.ProjectRepository;
import org.revature.RevTaskManagement.repository.UserRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void testCreateProject() {
        Project project = new Project();
        when(projectRepository.save(project)).thenReturn(project);

        Project result = projectService.createProject(project);

        assertEquals(project, result);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void testGetAllProjects() {
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertEquals(projects, result);
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    public void testAddTeamMemberToProject() throws Exception {
        Project project = new Project();
        User user = new User();
        when(projectRepository.findById(1)).thenReturn(Optional.of(project));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(projectRepository.save(project)).thenReturn(project);

        String result = projectService.addTeamMemberToProject(1, 1);

        assertEquals("Team member added successfully!", result);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void testGetTeamMembersByProjectName() {
        Project project = new Project();
        Set<User> teamMembers = new HashSet<>();
        project.setTeamMembers(teamMembers);
        when(projectRepository.findByProjectName("ProjectName")).thenReturn(project);

        Set<User> result = projectService.getTeamMembersByProjectName("ProjectName");
        // Continue with ProjectServiceTest
        assertEquals(teamMembers, result);
        verify(projectRepository, times(1)).findByProjectName("ProjectName");
    }

    @Test
    public void testGetTeamMembersByProjectId() {
        Project project = new Project();
        Set<User> teamMembers = new HashSet<>();
        project.setTeamMembers(teamMembers);
        when(projectRepository.findByProjectId(1)).thenReturn(project);

        Set<User> result = projectService.getTeamMembersByProjectId(1);

        assertEquals(teamMembers, result);
        verify(projectRepository, times(1)).findByProjectId(1);
    }
}
