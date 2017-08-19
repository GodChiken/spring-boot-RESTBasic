package org.phpbae.controller.rest;

import org.phpbae.service.DepartmentGroupRestService;
import org.phpbae.service.DepartmentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/departments/departments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDepartments() {
        return new ResponseEntity(departmentRestService.getDepartments(), HttpStatus.OK);
    }

    @GetMapping(value = "/departments/{departmentIdx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDepartmentOne(@PathVariable(value = "departmentIdx") int departmentIdx) {
        return new ResponseEntity(departmentRestService.getDepartment(departmentIdx), HttpStatus.OK);
    }

    @PutMapping(value = "/departments/insert_department/{departmentName}/{departmentGroupIdx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity putDepartment(@PathVariable(value = "departmentName") String departmentName, @PathVariable(value = "departmentGroupIdx") int departmentGroupIdx) {
        return new ResponseEntity(departmentRestService.insertDepartment(departmentName, departmentGroupIdx), HttpStatus.OK);
    }

    @PostMapping(value = "/departments/update_department/{departmentIdx}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateDepartment() {
        return null;
    }

    @DeleteMapping(value = "/departments/delete_department/{departmentIdx}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteDepartment() {
        return null;
    }

    @GetMapping(value = "/departmentGroups/departmentGroups", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDepartmentGroups() {
        return new ResponseEntity(departmentGroupRestService.getDepartmentGroups(), HttpStatus.OK);
    }

    @GetMapping(value = "/departmentGroups/{departmentGroupIdx}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDepartmentGroup(@PathVariable(value = "departmentGroupIdx") int departmentGroupIdx) {
        return new ResponseEntity(departmentGroupRestService.getDepartmentGroup(departmentGroupIdx), HttpStatus.OK);
    }


}
