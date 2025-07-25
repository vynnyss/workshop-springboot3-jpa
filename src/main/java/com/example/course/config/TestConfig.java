package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Category;
import com.example.course.entities.Order;
import com.example.course.entities.OrderItem;
import com.example.course.entities.Payment;
import com.example.course.entities.Product;
import com.example.course.entities.User;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.CategoryRepository;
import com.example.course.repositories.OrderItemRepository;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.ProductRepository;
import com.example.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository; 
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().addAll(Arrays.asList(cat1, cat3));
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User u1 = new User(null, "Jane Doe", "Jane@ZZZ.com", "988888888", "123456");
		User u2 = new User(null, "Ellen Joe", "shark@ZZZ.com", "977777777", "123456");
		User u3 = new User(null, "Corin", "corin@ZZZ.com", "977777776", "1234567");
		User u4 = new User(null, "Miyabi", "Miyabi@ZZZ.com", "123123","131241241");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1); 
		Order o4 = new Order(null, Instant.parse("2019-07-20T05:42:10Z"), OrderStatus.SHIPPED, u3);
		Order o5 = new Order(null, Instant.parse("2019-07-23T10:21:22Z"), OrderStatus.DELIVERED,u4); 
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));	
		orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4, o5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		OrderItem oi5 = new OrderItem(o4, p1, 1, p5.getPrice());
		OrderItem oi6 = new OrderItem(o5, p3, 1, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4, oi5, oi6));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		Payment pay2 = new Payment(null, Instant.parse("2019-07-20T06:42:10Z"), o4);
		Payment pay3 = new Payment(null, Instant.parse("2019-07-23T10:51:15Z"), o5);

		o1.setPayment(pay1);
		o4.setPayment(pay2);
		o5.setPayment(pay3);
		
		orderRepository.saveAll(Arrays.asList(o1, o4, o5));
		
		
		
	}
	
	
	
	
	
}
