package com.example.filrougefo;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class EStoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EStoreApplication.class, args);
    }
    @Autowired
    private OrderService orderService;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Category c = new Category();
        c.setId(5);

        Product p = new Product();
        p.setId(1);

        int quantity = 5;
        orderService.addOrderLineToOrder(p.getId(),quantity);
    }
}
