package org.phpbae.repository;

import org.phpbae.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by phpbae on 2017-08-12.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
