package org.phpbae.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017-08-12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "departmentsOfGroup")
@EqualsAndHashCode(exclude = "departmentsOfGroup")
@Entity
@Table(name = "department_group")
public class DepartmentGroup{

    @Column(name = "department_group_idx", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int departmentGroupIdx;

    @Column(name = "department_group_name")
    String departmentGroupName;

    //FK가 만들어진다. (many 쪽에 FK가 생성)
    //mappedBy는 양방향 관계에서, 주체가 되는쪽(many)을 정의
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "departmentGroup")
    //List<Department> departmentsOfGroup;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_group_idx", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    List<Department> departmentsOfGroup;
}
