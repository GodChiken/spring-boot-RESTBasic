package org.phpbae.service;

import org.phpbae.domain.DepartmentGroup;
import org.phpbae.repository.DepartmentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-08-12.
 */
@Service
public class DepartmentGroupRestService {

    @Autowired
    private DepartmentGroupRepository departmentGroupRepository;

    public List<DepartmentGroup> getDepartmentGroups() {
        return departmentGroupRepository.findAll();
    }

    public DepartmentGroup getDepartmentGroup(int departmentGroupIdx) {
        return departmentGroupRepository.findOne(departmentGroupIdx);
    }

}
