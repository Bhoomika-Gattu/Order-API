package in.arjun.service;

import in.arjun.model.entity.OrderItems;
import in.arjun.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository itemsRepository;

    public OrderItems saveOrderItems(OrderItems orderItems){
        return itemsRepository.save(orderItems);
    }

}
