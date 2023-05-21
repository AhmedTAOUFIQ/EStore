package com.example.filrougefo.web.cart;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.service.order.IntOrderService;
import com.example.filrougefo.service.orderline.IntOrderLineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private IntOrderService orderService;
    private IntOrderLineService orderLineService;

    @GetMapping
    private String getCart(){
        return "";
    }
    @PostMapping("/add")
    private String addProductToCart (@RequestParam int productId, @RequestParam long quantity){
        orderService.addOrderLineToOrder(productId,quantity);
        return "product-list";
    }
}
