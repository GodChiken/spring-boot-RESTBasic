package org.phpbae.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentVo {

    private String departmentName;
    private int departmentGroupIdx;

}
