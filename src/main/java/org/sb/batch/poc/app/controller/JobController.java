package org.sb.batch.poc.app.controller;

//import org.sb.batch.poc.app.model.CustomerNoSql;
import org.sb.batch.poc.app.model.CustomerRdbms;
//import org.sb.batch.poc.app.repository.CustomerNoSqlRepository;
import org.sb.batch.poc.app.repository.CustomerRdbmsRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired(required = false)
    JobLauncher jobLauncher;
//    @Autowired
//    CustomerNoSqlRepository customerNoSqlRepository;
    @Autowired
    CustomerRdbmsRepository customerRdbmsRepository;
    @Autowired
    Job processJob;

    @RequestMapping("/invokejob")
    public String handle() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(processJob, jobParameters);

        return "Batch job has been invoked";//{_id: 1}

    }

//    @RequestMapping(value = "/insert/customers/noSql", method = RequestMethod.POST)
//    public ResponseEntity<List<CustomerNoSql>> createEmpDataNoSql
//            (@RequestBody CustomerNoSql customer) {
//        customerNoSqlRepository.save(customer);
//        List<CustomerNoSql> customers = List.of(customer);
//        return new ResponseEntity<>(customers, HttpStatus.OK);
//    }

    @RequestMapping(value = "/insert/customers/rdbms", method = RequestMethod.POST)
    public ResponseEntity<List<CustomerRdbms>> createEmpDataRdbms
            (@RequestBody CustomerRdbms customer) {
        customerRdbmsRepository.save(customer);
        List<CustomerRdbms> customers = List.of(customer);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}