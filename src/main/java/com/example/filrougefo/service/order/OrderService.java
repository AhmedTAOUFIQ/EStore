package com.example.filrougefo.service.order;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.OrderStatus;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.OrderNotFoundException;
import com.example.filrougefo.repository.OrderLineRepository;
import com.example.filrougefo.repository.OrderRepository;
import com.example.filrougefo.service.orderline.IntOrderLineService;
import com.example.filrougefo.service.product.IntProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService implements IntOrderService{
    private final OrderRepository orderRepository;
    private IntProductService productService;
    private IntOrderLineService orderLineService;

    @Override
    public List<Order> findAllOrdersByClientId(long id) {
        return orderRepository.findAllByClientId(id);
    }

    @Override
    public List<Order> findAllOrdersByStatus_Id(long id) {
        return orderRepository.findAllByStatus_Id(id);
    }

    @Override
    public List<Order> findAllOrdersByStatus_Name(String name) {
        return orderRepository.findAllByStatus_Name(name);
    }

    @Override
    public List<Order> findAllByOrdersBeforeChosenDate(LocalDate date) {
        return orderRepository.findAllByDateIsLessThanEqual(date);
    }

    @Override
    public Order findOrderById(long id){
        return orderRepository
                .findById(id)
                .orElseThrow(()-> new OrderNotFoundException("No such Order found for id:"+id));
    }

    @Override
    public void addOrderLineToOrder(int productId, long quantity) {
        Order pendingOrder = hasPendingOrder();
        //orderRepository.save(pendingOrder);
        Product product = productService.findById(productId);
        //saving the new orderline to the database
        OrderLine orderLine = new OrderLine(pendingOrder,product,quantity);
        orderLineService.save(orderLine);
        //adding the orderLine to OrderLine List in the Order
        pendingOrder.getOrderLines().add(orderLine);
        orderRepository.save(pendingOrder);
    }

    private Order hasPendingOrder() {
        List<Order> orders = orderRepository.findAllByStatus_Name("PENDING");
        if(orders.size()==1){
            return orders.get(0);
        }else{
            Order order = new Order();
            order.setStatus(new OrderStatus(1,"PENDING"));
            return order;
        }

    }
}
