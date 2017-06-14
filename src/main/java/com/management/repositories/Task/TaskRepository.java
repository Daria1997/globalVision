package com.management.repositories.Task;

import com.management.entities.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{
}
