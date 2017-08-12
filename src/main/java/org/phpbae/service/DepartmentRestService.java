package org.phpbae.service;

import org.phpbae.advice.exception.RestCustomException;
import org.phpbae.domain.Department;
import org.phpbae.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by phpbae on 2017-08-12.
 */
@Service
public class DepartmentRestService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(int departmentIdx) {
        if(departmentIdx == 1){
            throw new RestCustomException("강제발생");
        }
        return departmentRepository.findOne(departmentIdx);
    }


}
