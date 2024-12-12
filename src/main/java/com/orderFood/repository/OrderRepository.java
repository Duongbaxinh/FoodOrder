package com.orderFood.repository;

import com.orderFood.entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository   extends JpaRepository<Orders, Integer> {
    @Query("SELECT o.food.id AS foodId, o.food.image AS image, o.food.description AS description, o.quantity AS quantity, o.food.price,o.food.name " +
            "FROM Orders o WHERE o.tableO.id = :tableId")
    List<Object[]> findByTableId(@Param("tableId") int tableId);

    @Query("select o from Orders o where o.tableO.id = :tableId and o.food.id = :foodId")
    Optional<Orders> findByFoodOrdered(@Param("tableId") int tableId, @Param("foodId") int foodId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Orders o WHERE o.food.id = :foodId")
    void deleteByFoodId(@Param("foodId") int foodId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Orders o WHERE o.tableO.id = :tableId and o.user.id = :userId")
    void deleteByTableId(@Param("tableId") int tableId, @Param("userId") int userId);
}
