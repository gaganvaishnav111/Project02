package org.revature.RevTaskManagement.repository;

import org.revature.RevTaskManagement.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p FROM Project p WHERE p.projectManager.username = :username")
    List<Project> findProjectsByUsername(@Param("username") String username);

    Project findByProjectName(String projectName);

    Project findByProjectId(int projectId);

    // Finds a project by ID
    Optional<Project> findById(Integer id);

    @Query("SELECT p FROM Project p WHERE p.projectManager.username = :managerName")
    List<Project> findProjectsByProjectManagerName(@Param("managerName") String managerName);

}