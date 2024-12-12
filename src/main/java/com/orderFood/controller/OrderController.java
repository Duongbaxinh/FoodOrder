package com.orderFood.controller;

import com.orderFood.dto.FoodOrder;
import com.orderFood.dto.OrderDetail;
import com.orderFood.entity.Food;
import com.orderFood.entity.Orders;
import com.orderFood.entity.TableO;
import com.orderFood.entity.User;
import com.orderFood.service.FoodService;
import com.orderFood.service.OrderService;
import com.orderFood.service.TableService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    private TableService tableService;
    @Autowired
    private FoodService foodService;

    @PostMapping("/addOrder")
    public String addOrder(@RequestParam int foodId,
                           @RequestParam int tableId,
                           @RequestParam int quantity,
                           HttpSession session) {

        System.out.println("fodd id " + foodId);
        User user = (User) session.getAttribute("userInfo");

        if (user == null) {
            return "User not logged in";
        }
        int userId = user.getId();

        try {
            Orders order = orderService.getFoodOrdered(tableId, foodId);
            if (order == null) {
                orderService.createOrder(userId, foodId, tableId, quantity);
            }else {
                order.setQuantity(order.getQuantity() + 1);
                orderService.saveOrder(order);
            }
            return "redirect:/orderDetail?tableIds=" + tableId;
        } catch (Exception e) {
            return "error";
        }
    }
    @GetMapping("/orderDetail")
    public String orderDetail(@RequestParam int tableIds, Model model,HttpSession session) {

        OrderDetail orderDetail = new OrderDetail();
        TableO tableO = tableService.getTableOById(tableIds);
        Iterable<Food> foods = foodService.getAllFoods();
        List<Object[]> orders = orderService.getOrderByTableId(tableIds);
        ArrayList<FoodOrder> foodOrders = new ArrayList<>();
        double totalPrice = 0;
        for (Object[] row : orders) {
            FoodOrder foodOrder = new FoodOrder();
            int foodId = (int) row[0];
            foodOrder.setId(foodId);
            String image = (String) row[1];
            foodOrder.setImage(image);
            String description = (String) row[2];
            foodOrder.setDescription(description);
            int quantity = (int) row[3];
            foodOrder.setQuantity(quantity);
            double price = (double) row[4];
            foodOrder.setPrice(price);
            String nameFood = (String) row[5];
            foodOrder.setName(nameFood);
            totalPrice += price*quantity;
            foodOrders.add(foodOrder);
        }
    System.out.println(foodOrders.size());
        orderDetail.setFoodOrders(foodOrders);
        orderDetail.setFoods((ArrayList<Food>) foods);
        orderDetail.setTableO(tableO);
        orderDetail.setTotalPrice((double) totalPrice);
        model.addAttribute("foodOrders", orderDetail);


        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("userInfo", user);
        return "order/order";
    }
    @GetMapping("/cancelOrderFood")
    public String cancelOrderFood(@RequestParam int tableId, Model model,HttpSession session) {
        System.out.println("fodd id " + tableId);
       User user = (User) session.getAttribute("userInfo");
       orderService.deleteOrderByUserId(tableId,user.getId());
       return "redirect:/home";
    }

    @GetMapping("/deleteOrderFood")
    public String deleteOrderFood(@RequestParam int foodId,@RequestParam int tableId, HttpSession session) {
        orderService.deleteOrderByFoodId(foodId);
        return "redirect:/orderDetail?tableIds=" + tableId;
    }
}
