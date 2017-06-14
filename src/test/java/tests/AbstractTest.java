package tests;

import javafx.application.Application;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Dasha on 14.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = Application.class
)
public class AbstractTest {
    protected Logger logger = Logger.getLogger(this.getClass());
}
