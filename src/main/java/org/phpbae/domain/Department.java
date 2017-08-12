package org.phpbae.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017-08-12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "departmentGroup")
@EqualsAndHashCode(exclude = "departmentGroup")
@Entity
@Table(name = "department")
public class Department{

    @Column(name = "department_idx", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentIdx;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_group_idx", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private DepartmentGroup departmentGroup;


}
