package org.phpbae.repository;

import org.phpbae.domain.DepartmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by phpbae  on 2017-08-12.
 */
@Repository
public interface DepartmentGroupRepository extends JpaRepository<DepartmentGroup, Integer> {
}
