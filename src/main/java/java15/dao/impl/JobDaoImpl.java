package java15.dao.impl;

import java15.config.DatabaseConfig;
import java15.dao.JobDao;
import java15.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JobDaoImpl implements JobDao {
    private final Connection connection = DatabaseConfig.getConnection();

    @Override
    public void createJobTable() {
        String query = """
                
                create table if not exists jobs(
                id serial primary key,
                positions  varchar not null,
                proffession varchar not null,
                description varchar not null,
                experience int not null)""";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addJob(Job job) {
        String query = """
                insert into jobs (position,proffession,description, experience) values(?,?,?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProffession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Job getJobById(int id) {
        String query = "select * from jobs where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("positions"));
                job.setProffession(resultSet.getString("proffession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                return job;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String query = "select * from jobs order by experience " + (ascOrDesc.equalsIgnoreCase("asc") ? "asc" : "desc");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                jobs.add(new Job(
                        resultSet.getString("id"),
                        resultSet.getString("positions"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        String query = """
                select j.* from jobs j
                           join employees e on j.id = e.jobId
                           where e.id = ?""";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("positions"));
                job.setProffession(resultSet.getString("proffession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                return job;
                }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {
        String query = "Alter table jobs drop column description ";
        try (PreparedStatement preparedStatement= connection.prepareStatement(query)){
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted description column");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}