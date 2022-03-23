package com.icoder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.icoder.model.iCoder;
import org.springframework.stereotype.Repository;

@Repository
public interface iCoderRepository extends JpaRepository<iCoder, Integer> {
}
