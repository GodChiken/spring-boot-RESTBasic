package org.phpbae.service;

import org.phpbae.advice.exception.RestCustomException;
import org.phpbae.advice.exception.RestFiledException;
import org.phpbae.domain.Department;
import org.phpbae.domain.DepartmentGroup;
import org.phpbae.domain.dto.DepartmentDTO;
import org.phpbae.domain.vo.UpdateDepartmentVo;
import org.phpbae.repository.DepartmentGroupRepository;
import org.phpbae.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by phpbae on 2017-08-12.
 */
@Service
public class DepartmentRestService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentGroupRepository departmentGroupRepository;

    /**
     * 모든 부서를 조회해 온다. 단, 해당 부서에 대응하는 부서그룹이 없는 경우, Exception 발생.
     * READ
     *
     * @return
     */
    public List<DepartmentDTO> getDepartments() {
        List<Department> departmentList = new ArrayList<>();
        try {
            departmentList = departmentRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RestCustomException(ex.getCause().getMessage());
        }

        return departmentList.stream()
                .map(department -> {
                    DepartmentDTO departmentDTO = new DepartmentDTO(department.getDepartmentIdx(), department.getDepartmentName(), department.getDepartmentGroup());
                    return departmentDTO;
                }).collect(Collectors.toList());
    }

    /**
     * 부서Idx를 통해서, 하나의 부서를 조회해 온다. 단, 가지고온 부서에 대응하는 부서그룹이 없는 경우, Exception 발생
     * READ
     *
     * @param departmentIdx
     * @return
     */
    public DepartmentDTO getDepartment(int departmentIdx) {
        Department department = departmentRepository.findOne(departmentIdx);
        if (department == null) {
            throw new RestCustomException("Not find Department Or Not find Department_group");
        }
        return new DepartmentDTO(department.getDepartmentIdx(), department.getDepartmentName(), department.getDepartmentGroup());
    }

    /**
     * 부서를 생성한다. 생성 시, 부서명과 부서그룹Idx 값을 지정해줘야한다. 지정한 부서그룹Idx가 존재하지 않을 시, Exception 발생
     * CREATE
     *
     * @param departmentName
     * @param departmentGroupIdx
     * @return
     */
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

    /**
     * 부서를 수정한다. 부서Idx, 부서명, 부서그룹Idx 값을 지정해줘야한다.
     * 부서그룹Idx가 없는 부서그룹 또는 부서가 존재하지 않는 경우 Exception 발생.
     * @param departmentIdx
     * @param updateDepartmentVo
     */
    public void updateDepartment(int departmentIdx, UpdateDepartmentVo updateDepartmentVo) {

        Department department = departmentRepository.findOne(departmentIdx);
        DepartmentGroup departmentGroup = departmentGroupRepository.findOne(updateDepartmentVo.getDepartmentGroupIdx());
        if (department == null) {
            throw new RestCustomException("Not find Department_group Or Not find Department");
        } else if (departmentGroup == null) {
            throw new RestCustomException("Not find department_group information");
        }

        department.setDepartmentName(updateDepartmentVo.getDepartmentName());
        department.setDepartmentGroup(departmentGroup);
        departmentRepository.save(department);
    }

    /**
     * 부서Idx를 통해, 부서를 삭제한다.
     * @param departmentIdx
     */
    public void deleteDepartment(int departmentIdx) {
        departmentRepository.delete(departmentIdx);
    }

}
