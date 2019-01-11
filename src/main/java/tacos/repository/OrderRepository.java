package tacos.repository;

import org.springframework.core.annotation.Order;

public interface OrderRepository {
	
	Order save(Order order);

}
