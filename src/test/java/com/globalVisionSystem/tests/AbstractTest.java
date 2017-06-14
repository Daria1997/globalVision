package com.globalVisionSystem.tests;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import javafx.application.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Dasha on 07.06.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = Application.class
)
@WebAppConfiguration
public class AbstractTest {
    protected Logger logger = Logger.getLogger(this.getClass());
}
