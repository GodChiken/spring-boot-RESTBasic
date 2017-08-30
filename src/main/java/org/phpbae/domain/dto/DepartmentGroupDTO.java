package org.phpbae.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.phpbae.domain.Department;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentGroupDTO {

    private int departmentGroupIdx;
    private String departmentGroupName;
    private List<Department> departmentsOfGroup;

}
