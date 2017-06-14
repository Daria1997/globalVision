package com.management.repositories.Manager;

import com.management.entities.Manager.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dasha on 14.06.2017.
 */
@Repository
public interface ManagerRepository  extends JpaRepository<Manager,Integer> {
}
