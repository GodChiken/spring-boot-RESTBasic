package org.phpbae.service;

import org.phpbae.domain.DepartmentGroup;
import org.phpbae.domain.dto.DepartmentGroupDTO;
import org.phpbae.repository.DepartmentGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017-08-12.
 */
@Service
public class DepartmentGroupRestService {

    @Autowired
    private DepartmentGroupRepository departmentGroupRepository;

    /**
     * 모든 부서 그룹을 조회 한다.
     * READ
     *
     * @return
     */
    public List<DepartmentGroupDTO> getDepartmentGroups() {
        List<DepartmentGroup> departmentGroupList = departmentGroupRepository.findAll();

        return departmentGroupList
                .stream()
                .map(departmentGroup -> {
                    DepartmentGroupDTO departmentGroupDTO = new DepartmentGroupDTO(departmentGroup.getDepartmentGroupIdx(), departmentGroup.getDepartmentGroupName(), departmentGroup.getDepartmentsOfGroup());
                    return departmentGroupDTO;
                }).collect(Collectors.toList());
    }

    /**
     * 부서그룹Idx를 통해, 하나의 부서 그룹을 조회 한다.
     * READ
     *
     * @param departmentGroupIdx
     * @return
     */
    public DepartmentGroupDTO getDepartmentGroup(int departmentGroupIdx) {
        DepartmentGroup departmentGroup = departmentGroupRepository.findOne(departmentGroupIdx);
        return new DepartmentGroupDTO(departmentGroup.getDepartmentGroupIdx(),departmentGroup.getDepartmentGroupName(),departmentGroup.getDepartmentsOfGroup());
    }

}
