package java15.service.impl;

import java15.dao.EmployeeDao;
import java15.models.Employee;
import java15.models.Job;
import java15.service.EmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void createEmployee() {
employeeDao.createEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

    }

    @Override
    public void dropTable() {
        employeeDao.dropTable();

    }

    @Override
    public void cleanTable() {
        employeeDao.cleanTable();

    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        employeeDao.updateEmployee(id, employee);

    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDao.getEmployeeByPosition(position);
    }
}
