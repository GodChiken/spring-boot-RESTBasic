package org.phpbae;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.phpbae.controller.rest.IndexRestController;
import org.phpbae.domain.Department;
import org.phpbae.service.DepartmentGroupRestService;
import org.phpbae.service.DepartmentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by Administrator on 2017-08-12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexRestTest {

    @Autowired
    private DepartmentRestService departmentRestService;
    @Autowired
    private DepartmentGroupRestService departmentGroupRestService;

    @Test
    public void serviceObjectTest() {
        Assert.assertNotNull(departmentRestService);
        Assert.assertNotNull(departmentGroupRestService);
    }

    @Test
    public void getDepartmentTest() {
        Department department = departmentRestService.getDepartment(1);
        Assert.assertNotNull(department);
        Assert.assertEquals("부서명", "인사팀", department.getDepartmentName());
        Assert.assertEquals("부서가 포함되어있는 그룹", "사업부문", department.getDepartmentGroup().getDepartmentGroupName()); //fetchType 변경으로 테스트 통과
    }

}
