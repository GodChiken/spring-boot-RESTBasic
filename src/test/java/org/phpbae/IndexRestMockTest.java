package org.phpbae;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.phpbae.domain.Department;
import org.phpbae.service.DepartmentGroupRestService;
import org.phpbae.service.DepartmentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Administrator on 2017-08-12.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(secure = false)
public class IndexRestMockTest {

    @MockBean
    private DepartmentRestService departmentRestService;
    @MockBean
    private DepartmentGroupRestService departmentGroupRestService;

    @Autowired
    private MockMvc mockMvc;

    private Department department;

    @Test
    public void insertDepartmentTest() throws Exception {
        when(this.departmentRestService.insertDepartment("배님",1)).thenReturn(department);
        this.mockMvc.perform(put("/api/departments/insert_department/배님/1").accept(MediaType.APPLICATION_JSON).characterEncoding("utf8")
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getDepartmentTest() throws Exception {
        //서비스 구현체가 없어도 Test 가능. request, response 결과가 정상적인지 확인.
        given(this.departmentRestService.getDepartment(1)).willReturn(new Department(1,"인사팀", null)); // getDepartment 메소드에 인자값 1이 입력될 경우 .willReturn(getDepartment); // getDepartment 객체를 리턴한다.
        this.mockMvc.perform(get("/api/departments/{departmentIdx}", 1).characterEncoding("utf8")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(redirectedUrl(null))
                .andExpect(jsonPath("$.departmentName").isNotEmpty())
                .andExpect(jsonPath("$.departmentName").value("인사팀"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

}
