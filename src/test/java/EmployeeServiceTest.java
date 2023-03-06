import com.alibaba.excel.metadata.BaseRowModel;
import com.dne.core.common.Constant;
import com.dne.core.util.ExcelExportUtils;
import com.dne.ctrip.entity.SyncEmployeeInfo;
import com.dne.ctrip.excel.model.ExportCompareEmpRowModel;
import com.dne.ctrip.excel.model.ExportEmpRowModel;
import com.dne.ctrip.syndata.service.EmployeeService;
import org.apache.commons.compress.utils.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class EmployeeServiceTest {


    private static ApplicationContext applicationContext = null;

    @Before
    public void before() {
        applicationContext =
                new ClassPathXmlApplicationContext("classpath:config/spring-context.xml");
    }

    @Test
    public void updateSyncStatusTest() {
        String batchNo = "20230228303068";
        List<String> errorSyncToCtripCwid = Arrays.asList("GDKCU","GONYC");
        EmployeeService service = (EmployeeService) applicationContext.getBean("employeeService");
        List<SyncEmployeeInfo> employeeInfos = service.getSyncEmployeeInfoByBatchNo("20230301251430");
        List<ExportEmpRowModel> rowModels = new ArrayList<>();
        List<ExportCompareEmpRowModel> compareEmpRowModels = new ArrayList<>();
        for(SyncEmployeeInfo employeeInfo : employeeInfos){
            ExportEmpRowModel rowModel = new ExportEmpRowModel();
            ExportCompareEmpRowModel compareEmpRowModel = new ExportCompareEmpRowModel();
            BeanUtils.copyProperties(employeeInfo, rowModel);
            BeanUtils.copyProperties(employeeInfo,compareEmpRowModel);
            rowModels.add(rowModel);
            compareEmpRowModels.add(compareEmpRowModel);
        }
        try {
            ExcelExportUtils.export2ExcelByPojo(Arrays.asList(compareEmpRowModels,rowModels),
                    Arrays.asList(ExportCompareEmpRowModel.class, ExportEmpRowModel.class),
                    Arrays.asList("Create", "Modify"),
                    Arrays.asList("New Create Employee", "Modify Employee"),
                    "C:\\App\\Ctrip\\employee-infomation\\12345\\test.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        service.batchUpdateSyncEmployeeStatus(
//                batchNo, Constant.CTRIP_SYNC_STATUS_N, errorSyncToCtripCwid,20);

    }
}
