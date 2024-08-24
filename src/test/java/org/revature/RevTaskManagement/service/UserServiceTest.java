package org.revature.RevTaskManagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revature.RevTaskManagement.Enums.Role;
import org.revature.RevTaskManagement.Enums.Status;
import org.revature.RevTaskManagement.models.*;
import org.revature.RevTaskManagement.repository.ProjectRepository;
import org.revature.RevTaskManagement.repository.UserRepository;

import jakarta.mail.MessagingException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testAuthenticateUser() {
        User user = new User();
        user.setPassword("password");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User result = userService.authenticateUser("test@example.com", "password");

        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testCreateUser() throws MessagingException {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertEquals(user, result);
        verify(emailService, times(1)).sendEmail(eq(user.getEmail()), anyString(), anyString());
    }

    @Test
    public void testUpdateUser() throws MessagingException {
        User user = new User();
        user.setUsername("oldName");
        user.setEmail("oldEmail@example.com");
        user.setStatus(Status.ACTIVE);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(1, "newName", "newEmail@example.com", Status.INACTIVE);

        assertEquals("newName", updatedUser.getUsername());
        assertEquals("newEmail@example.com", updatedUser.getEmail());
        assertEquals(Status.INACTIVE, updatedUser.getStatus());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAssignAccessLevel() throws MessagingException {
        User user = new User();
        user.setRole(Role.USER);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.assignAccessLevel(1, "ADMIN");

        assertEquals(Role.ADMIN, updatedUser.getRole());
        verify(userRepository, times(1)).save(user);
        verify(emailService, times(1)).sendEmail(eq(user.getEmail()), anyString(), anyString());
    }

    @Test
    public void testFindByNameAndEmail() {
        User user = new User();
        when(userRepository.findByUsernameAndEmail("username", "email@example.com")).thenReturn(user);

        User result = userService.findByNameAndEmail("username", "email@example.com");

        assertEquals(user, result);
        verify(userRepository, times(1)).findByUsernameAndEmail("username", "email@example.com");
    }

    @Test
    public void testUpdatePassword() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        userService.updatePassword(1, "newPassword");

        assertEquals("newPassword", user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAllTeamMembers() {
        List<User> teamMembers = Arrays.asList(new User(), new User());
        when(userRepository.findByRole(Role.TEAM_MEMBER)).thenReturn(teamMembers);

        List<User> result = userService.getAllTeamMembers();

        assertEquals(teamMembers, result);
        verify(userRepository, times(1)).findByRole(Role.TEAM_MEMBER);
    }

    @Test
    public void testGetProjectManagers() {
        List<User> managers = Arrays.asList(new User(), new User());
        when(userRepository.findByRole(Role.PROJECT_MANAGER)).thenReturn(managers);

        List<User> result = userService.getProjectManagers();

        assertEquals(managers, result);
        verify(userRepository, times(1)).findByRole(Role.PROJECT_MANAGER);
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User();
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByUsername("username");

        assertEquals(Optional.of(user), result);
        verify(userRepository, times(1)).findByUsername("username");
    }

    @Test
    public void testResetPassword() {
        when(userRepository.resetPasswordById(1, "newPassword")).thenReturn(1);

        String result = userService.resetPassword(1, "newPassword");

        assertEquals("Password updated successfully!", result);
    }

    @Test
    public void testGetTeamMembersByManagerName() {
        User user = new User();
        Project project = new Project();
        project.setTeamMembers(Set.of(user));
        when(projectRepository.findProjectsByProjectManagerName("managerName"))
                .thenReturn(List.of(project));

        Set<User> result = userService.getTeamMembersByManagerName("managerName");

        assertEquals(Set.of(user), result);
        verify(projectRepository, times(1)).findProjectsByProjectManagerName("managerName");
    }

    @Test
    public void testGetClientsByManagerName() {
        User user = new User();
        Project project = new Project();
        project.setClient(new Client());
        when(projectRepository.findProjectsByProjectManagerName("managerName"))
                .thenReturn(List.of(project));

        Set<Client> result = userService.getClientsByManagerName("managerName");

        assertEquals(Set.of(project.getClient()), result);
        verify(projectRepository, times(1)).findProjectsByProjectManagerName("managerName");
    }

    @Test
    public void testGetTasksByManagerName() {
        Task task = new Task();
        Project project = new Project();
        project.setTasks(Set.of(task));
        when(projectRepository.findProjectsByProjectManagerName("managerName"))
                .thenReturn(List.of(project));

        Set<Task> result = userService.getTasksByManagerName("managerName");

        assertEquals(Set.of(task), result);
        verify(projectRepository, times(1)).findProjectsByProjectManagerName("managerName");
    }

//    @Test
//    public void testGetMilestonesByManagerName() {
//        Milestone milestone = new Milestone();
//        Project project = new Project();
//        project.setMilestones(Set.of(milestone));
//        when(projectRepository.findProjectsByProjectManagerName("managerName"))
//                .thenReturn(List.of(project));
//
//        Set<Milestone> result = userService.getMilestonesByManagerName("managerName");
//
//        assertEquals(Set.of(milestone), result);
//        verify(projectRepository, times(1)).findProjectsByProjectManagerName("managerName");
//    }
}
