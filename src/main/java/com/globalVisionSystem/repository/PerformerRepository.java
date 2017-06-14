package com.globalVisionSystem.repository;

import com.globalVisionSystem.domains.performer.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dasha on 13.06.2017.
 */
@Repository
public interface PerformerRepository extends JpaRepository<Performer,Long>{
}
