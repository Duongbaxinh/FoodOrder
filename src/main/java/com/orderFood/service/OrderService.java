package com.orderFood.service;

import com.orderFood.entity.Food;
import com.orderFood.entity.Orders;
import com.orderFood.entity.TableO;
import com.orderFood.entity.User;
import com.orderFood.repository.FoodRepository;
import com.orderFood.repository.OrderRepository;
import com.orderFood.repository.TableRepository;
import com.orderFood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository ordersRepository;

    @Autowired
    private TableRepository tableORepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    public void  deleteOrderByFoodId(int foodId){
        ordersRepository.deleteByFoodId(foodId);
    }
    public void  deleteOrderByUserId(int tableId,int userId){
        ordersRepository.deleteByTableId(tableId,userId);
    }

    public List<Object[]> getOrderByTableId(int tableId) {
        return ordersRepository.findByTableId(tableId);
    }

    public Orders getFoodOrdered(int tableId,int foodId){
        return  ordersRepository.findByFoodOrdered(tableId,foodId).orElse(null);
    }

    public void saveOrder(Orders orders){
        ordersRepository.save(orders);
    }

    public void createOrder(int userId, int foodId, int tableId, int quantity) {
    System.out.println("run at here " + tableId + " " + foodId + " " + userId + " " + quantity);
        TableO table = tableORepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found with id " + tableId));
        System.out.println("end1");
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found with id " + foodId));
        System.out.println("end2");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        Orders order = new Orders();
        order.setTableO(table);
        order.setFood(food);
        order.setUser(user);
        order.setQuantity(quantity);
        order.setOrderTime(LocalDateTime.now());


         ordersRepository.save(order);

    }
}
