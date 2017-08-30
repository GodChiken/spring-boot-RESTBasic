package org.phpbae.controller.rest;

import org.phpbae.domain.vo.UpdateDepartmentVo;
import org.phpbae.service.DepartmentGroupRestService;
import org.phpbae.service.DepartmentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017-08-12.
 */
@RestController
@RequestMapping("api")
public class IndexRestController {

    @Autowired
    private DepartmentRestService departmentRestService;

    @Autowired
    private DepartmentGroupRestService departmentGroupRestService;

    /**
     * 부서명과 부서그룹Idx를 전달받아, 새로운 부서를 생성한다.
     * CREATE
     *
     * @param departmentName
     * @param departmentGroupIdx
     * @return
     */
    @PutMapping(value = "/departments/insert_department/{departmentName}/{departmentGroupIdx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity putDepartment(@PathVariable(value = "departmentName") String departmentName, @PathVariable(value = "departmentGroupIdx") int departmentGroupIdx) {
        return new ResponseEntity(departmentRestService.insertDepartment(departmentName, departmentGroupIdx), HttpStatus.OK);
    }

    /**
     * 모든 부서를 조회해 온다. json 형식으로 변환되어 return
     * READ
     *
     * @return
     */
    @GetMapping(value = "/departments/departments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDepartments() {
        return new ResponseEntity(departmentRestService.getDepartments(), HttpStatus.OK);
    }

    /**
     * 부서Idx를 통해서, 하나의 부서를 조회해 온다. json 형식으로 변환되어 return
     * READ
     *
     * @param departmentIdx
     * @return
     */
    @GetMapping(value = "/departments/{departmentIdx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDepartmentOne(@PathVariable(value = "departmentIdx") int departmentIdx) {
        return new ResponseEntity(departmentRestService.getDepartment(departmentIdx), HttpStatus.OK);
    }

    /**
     * 부서Idx를 전달받아, 해당 부서 정보(부서명, 부서그룹Idx)를 수정한다.
     * UPDATE
     *
     * @return
     */
    @PostMapping(value = "/departments/update_department/{departmentIdx}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateDepartment(@PathVariable(value = "departmentIdx") int departmentIdx, @RequestBody @Validated UpdateDepartmentVo updateDepartmentVo) {
        departmentRestService.updateDepartment(departmentIdx, updateDepartmentVo);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    /**
     * 부서Idx를 전달받아, 해당 부서를 삭제한다.
     * DELETE
     *
     * @param departmentIdx
     * @return
     */
    @DeleteMapping(value = "/departments/delete_department/{departmentIdx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteDepartment(@PathVariable(value = "departmentIdx") int departmentIdx) {
        departmentRestService.deleteDepartment(departmentIdx);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    /**
     * 모든 부서그룹을 조회한다. json 형식으로 변환되어 return
     * READ
     *
     * @return
     */
    @GetMapping(value = "/departmentGroups/departmentGroups", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDepartmentGroups() {
        return new ResponseEntity(departmentGroupRestService.getDepartmentGroups(), HttpStatus.OK);
    }

    /**
     * 부서그룹Idx를 통해서, 하나의 부서그룹을 조회한다. json 형식으로 변환되어 return
     * READ
     *
     * @param departmentGroupIdx
     * @return
     */
    @GetMapping(value = "/departmentGroups/departmentGroups/{departmentGroupIdx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDepartmentGroup(@PathVariable(value = "departmentGroupIdx") int departmentGroupIdx) {
        return new ResponseEntity(departmentGroupRestService.getDepartmentGroup(departmentGroupIdx), HttpStatus.OK);
    }


}
