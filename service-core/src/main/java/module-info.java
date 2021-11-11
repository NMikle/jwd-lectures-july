import com.epam.jwd.mds.api.service.EmployeeService;
import com.epam.jwd.mds.core.service.ListEmployeeService;

module lectures.service.core {
    requires lectures.service.api;
    requires org.apache.logging.log4j;

    provides EmployeeService with ListEmployeeService;
}