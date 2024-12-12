package com.orderFood.repository;

import com.orderFood.entity.TableO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository  extends JpaRepository<TableO, Integer> {

}
