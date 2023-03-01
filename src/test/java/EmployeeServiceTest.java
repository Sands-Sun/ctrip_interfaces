import com.dne.core.common.Constant;
import com.dne.ctrip.syndata.service.EmployeeService;
import org.apache.commons.compress.utils.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class EmployeeServiceTest {


    private static ApplicationContext applicationContext = null;

//    @Before
    public void before() {
        applicationContext =
                new ClassPathXmlApplicationContext("classpath:config/spring-context.xml");
    }

//    @Test
    public void updateSyncStatusTest() {
        String batchNo = "20230228303068";
        List<String> errorSyncToCtripCwid = Arrays.asList("GDKCU","GONYC");
        EmployeeService service = (EmployeeService) applicationContext.getBean("employeeService");
        service.batchUpdateSyncEmployeeStatus(
                batchNo, Constant.CTRIP_SYNC_STATUS_N, errorSyncToCtripCwid,20);

    }
}
