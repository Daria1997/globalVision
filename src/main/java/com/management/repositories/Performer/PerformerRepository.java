package com.management.repositories.Performer;

import com.management.entities.Performer.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dasha on 14.06.2017.
 */
@Repository
public interface PerformerRepository  extends JpaRepository<Performer,Integer> {
        }
