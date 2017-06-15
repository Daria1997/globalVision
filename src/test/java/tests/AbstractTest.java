package tests;

import com.management.ManagementApplication;
import com.management.services.Account.AccountService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Dasha on 14.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagementApplication.class)
public class AbstractTest {
    protected Logger logger = Logger.getLogger(this.getClass());
}
