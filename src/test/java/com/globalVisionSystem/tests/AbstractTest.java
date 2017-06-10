package com.globalVisionSystem.tests;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.junit.Test;
import javafx.application.Application;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by Dasha on 07.06.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = Application.class
)
public class AbstractTest {
    protected Logger logger = Logger.getLogger(this.getClass());
}
