import com.epam.jwd.mds.api.service.EmployeeService;

module lectures.starter {
    requires lectures.service.api;
    requires org.apache.logging.log4j;

    uses EmployeeService;
}