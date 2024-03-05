package com.company.project.employes;

import com.company.project.entity.Greeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author snd00 created at 28.02.2024
 * @project backend
 */
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}