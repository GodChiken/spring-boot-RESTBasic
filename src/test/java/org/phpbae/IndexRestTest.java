package org.phpbae;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.phpbae.advice.exception.RestCustomException;
import org.phpbae.domain.dto.DepartmentDTO;
import org.phpbae.service.DepartmentGroupRestService;
import org.phpbae.service.DepartmentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        DepartmentDTO departmentDTO = departmentRestService.getDepartment(1);
        Assert.assertNotNull(departmentDTO);
        Assert.assertEquals("부서명", "인사팀", departmentDTO.getDepartmentName());
        Assert.assertEquals("부서가 포함되어있는 그룹", "사업부문", departmentDTO.getDepartmentGroup().getDepartmentGroupName());
    }

    @Test
    public void mockMvcGetDepartmentTest() throws Exception {
        this.mockMvc.perform(get("/api/departments/{departmentIdx}", 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.departmentName").value("인사팀"))
                .andExpect(jsonPath("$.departmentGroup.departmentGroupIdx").value(1));
    }

    @Test
    public void mockMvcCreateDepartmentTest() throws Exception {
        this.mockMvc.perform(put("/api/departments/insert_department/{departmentName}/{departmentGroupIdx}", "창현팀", 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        DepartmentDTO departmentDTO = departmentRestService.getDepartment(9);
        Assert.assertNotNull(departmentDTO);
        Assert.assertEquals("부서명", "창현팀", departmentDTO.getDepartmentName());
        Assert.assertEquals("부서가 포함되어있는 그룹", "사업부문", departmentDTO.getDepartmentGroup().getDepartmentGroupName());

    }

    @Test
    public void mockMvcUpdateDepartmentTest() throws Exception {
        JSONObject updateDepartment = new JSONObject();
        updateDepartment.put("departmentName", "전설의팀");
        updateDepartment.put("departmentGroupIdx", 2);
        System.out.println(updateDepartment.toString());

        this.mockMvc.perform(post("/api/departments/update_department/{departmentIdx}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(updateDepartment.toString()))
                .andDo(print())
                .andExpect(status().isOk());

        DepartmentDTO departmentDTO = departmentRestService.getDepartment(1);
        Assert.assertNotNull(departmentDTO);
        Assert.assertEquals("부서명", "전설의팀", departmentDTO.getDepartmentName());
        Assert.assertEquals("부서가 포함되어있는 그룹", "개발부문", departmentDTO.getDepartmentGroup().getDepartmentGroupName());
    }

    @Test(expected = RestCustomException.class)
    public void mockMvcDeleteDepartmentTest() throws Exception {
        final int departmentIdx = 3;
        this.mockMvc.perform(delete("/api/departments/delete_department/{departmentIdx}", departmentIdx)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

        DepartmentDTO departmentDTO = departmentRestService.getDepartment(departmentIdx);

    }


}
