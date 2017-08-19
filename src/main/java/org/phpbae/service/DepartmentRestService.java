package org.phpbae.service;

import org.phpbae.advice.exception.RestCustomException;
import org.phpbae.advice.exception.RestFiledException;
import org.phpbae.domain.Department;
import org.phpbae.domain.DepartmentGroup;
import org.phpbae.repository.DepartmentGroupRepository;
import org.phpbae.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phpbae on 2017-08-12.
 */
@Service
public class DepartmentRestService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentGroupRepository departmentGroupRepository;

    public List<Department> getDepartments() {
        List<Department> departmentList = new ArrayList<>();
        try {
            departmentList = departmentRepository.findAll();
        } catch (Exception ex) {
            throw new RestCustomException("test");
        }

        return departmentList;
    }

    public Department getDepartment(int departmentIdx) {

        Department department = departmentRepository.findOne(departmentIdx);
        if (department == null) {
            throw new RestCustomException("No department_group information & No department information");
        }

        return department;
    }

    public Department insertDepartment(String departmentName, int departmentGroupIdx) {
        Department insertDepartment = null;
        DepartmentGroup departmentGroup = null;
        try {
            departmentGroup = departmentGroupRepository.findOne(departmentGroupIdx);
            insertDepartment = new Department(departmentName, departmentGroup);
        } catch (Exception ex) {
            throw new RestFiledException("부서그룹 미존재");
        }

        return departmentRepository.save(insertDepartment);
    }

}
