package java15.dao.impl;

import java15.config.DatabaseConfig;
import java15.dao.EmployeeDao;
import java15.models.Employee;
import java15.models.Job;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
   private final Connection connection= DatabaseConfig.getConnection();

    @Override
    public void createEmployee() {
        String sql = """ 
        CREATE TABLE IF NOT EXISTS employees
        (id serial primary key,
        firstName varchar(50),
        lastName varchar(50),
        age int,
        email varchar(50),
        jobId int references jobs (id)    
                )
                """;
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Successfully created table Employees !");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void addEmployee(Employee employee) {
        String sql = """
        insert into employees (firstName, lastName, age, email, jobId)
        values (?,?,?,?,?)""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("Successfully added Employee !");

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropTable() {
String sql = "drop table employees cascade";
try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    preparedStatement.executeUpdate();
    System.out.println("Successfully dropped table Employees !");
}catch (SQLException e) {
    System.out.println(e.getMessage()+"Error dropping table Employees !");
}
    }

    @Override
    public void cleanTable() {
        String sql = "TRUNCATE TABLE employees cascade";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
            System.out.println("Successfully cleaned table Employees !");
        }catch (SQLException e) {
            System.out.println(e.getMessage()+"Error cleaning table Employees !");
        }


    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql = """
                update employees set firstName = ?, lastName = ?, age = ?, email = ?, jobId = ? where id = ?""";
try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    preparedStatement.setString(1,employee.getFirstName());
    preparedStatement.setString(2,employee.getLastName());
    preparedStatement.setInt(3,employee.getAge());
    preparedStatement.setString(4,employee.getEmail());
    preparedStatement.setInt(5,employee.getJobId());
    preparedStatement.setLong(6,id);
    preparedStatement.executeUpdate();
    System.out.println("Successfully updated Employee !");
}catch (SQLException e) {
    System.out.println(e.getMessage());
}
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = " select * from employees";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("jobId")
                ));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public Employee findByEmail(String email) {
        String sql = "select * from employees where email = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("jobId")
                );
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee, Job> employees = new HashMap<>();
        String sql =  """
        SELECT e.*, j.* FROM employees e
        JOIN jobs j ON e.jobId = j.id
        WHERE e.id = ?""";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getLong("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getInt("jobId")
                );
                Job job = new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("proffession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience"));
                employees.put(employee, job);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        String sql = """ 
                select e.* from employees e join jobs j on e.jobId = j.id
                where positions = ?""";
        List<Employee> employees = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("jobId"));
                employees.add(employee);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }
}

