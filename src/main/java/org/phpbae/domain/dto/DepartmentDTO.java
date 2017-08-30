package org.phpbae.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.phpbae.domain.DepartmentGroup;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private int departmentIdx;
    private String departmentName;
    private DepartmentGroup departmentGroup;

}
