package com.isacode.repository;

import com.isacode.entity.SequentialCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequentialCodeRepository extends JpaRepository<SequentialCode, Integer> {

}
