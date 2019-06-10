package com.lemon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lemon
 */
@Repository
public interface StudentDao extends JpaRepository<StudentDao, Integer> {

}
