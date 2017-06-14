package com.globalVisionSystem.application;

import com.globalVisionSystem.domains.performer.Performer;
import com.globalVisionSystem.services.performer.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dasha on 13.06.2017.
 */
@RestController
@RequestMapping("/test")
public class MainRest {
    @Autowired
    private PerformerService performerService;

    @GetMapping("firstPerformer")
    public Performer getFirstPerformer(Long id) {
        return performerService.get(id);
    }
}
