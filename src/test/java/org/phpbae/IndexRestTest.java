package org.phpbae;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.phpbae.domain.Department;
import org.phpbae.service.DepartmentGroupRestService;
import org.phpbae.service.DepartmentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by Administrator on 2017-08-12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IndexRestTest {

    @Autowired
    private DepartmentRestService departmentRestService;
    @Autowired
    private DepartmentGroupRestService departmentGroupRestService;

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    public void createDepartmentTest() {
        departmentRestService.insertDepartment("전설의팀", 1);

        Department department = departmentRestService.getDepartment(9);
        Assert.assertNotNull(department);
        Assert.assertEquals("부서명", "전설의팀", department.getDepartmentName());
        Assert.assertEquals("부서가 포함되어있는 그룹", "사업부문", department.getDepartmentGroup().getDepartmentGroupName());

    }

    @Test
    public void mockMvcGetDepartmentTest() throws Exception {
        this.mockMvc.perform(get("/api/departments/{departmentIdx}", 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.departmentName").value("인사팀"));
    }

}
