package com.management.repositories.Project;

import com.management.entities.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
