package java15.dao;

import java15.models.Job;

import java.util.List;

public interface JobDao {
    void createJobTable();
    void addJob(Job job);
    Job getJobById(int id);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
