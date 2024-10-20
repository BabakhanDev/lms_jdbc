package java15.service.impl;

import java15.dao.JobDao;
import java15.models.Job;
import java15.service.JobService;

import java.util.List;
import java.util.NoSuchElementException;

public class JobServiceImpl implements JobService {
    private final JobDao jobDao;

    public JobServiceImpl(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public void createJobTable() {
        jobDao.createJobTable();

    }

    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);

    }

    @Override
    public Job getJobById(int id) {
        return jobDao.getJobById(id);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();

    }
}
