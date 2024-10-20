package java15;
import java15.dao.impl.EmployeeDaoImpl;
import java15.dao.impl.JobDaoImpl;
import java15.models.Employee;
import java15.models.Job;
import java15.service.EmployeeService;
import java15.service.JobService;
import java15.service.impl.EmployeeServiceImpl;
import java15.service.impl.JobServiceImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JobDaoImpl jobDao = new JobDaoImpl();
        JobService jobService = new JobServiceImpl(jobDao);

        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);

//       employeeService.createEmployee();
//        System.out.println(employeeService.getEmployeeById(1L));
//        System.out.println(employeeService.getAllEmployees());
//        System.out.println(employeeService.getEmployeeByPosition("Mentor"));
//        System.out.println(employeeService.findByEmail("B@gmail.com"));
//        employeeService.cleanTable();
//        employeeService.dropTable();
//       employeeService.addEmployee(new Employee(
//                "Beka","Bakas",20,"B@gmail.com",1
//        ));
//        System.out.println(employeeService.getEmployeeById(1L));

        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//        jobService.createJobTable();
//      jobService.addJob(new Job(
//                "Mentor",
//                "Java",
//                "Beckend Developer",
//                1
//        System.out.println(jobService.getJobById(1));
//        System.out.println(jobService.getJobByEmployeeId(1L));
//        System.out.println(jobService.sortByExperience("asc"));
//        jobService.deleteDescriptionColumn();
//        ));




    }
}
