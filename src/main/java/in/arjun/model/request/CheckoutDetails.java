package in.arjun.model.request;

import in.arjun.model.entity.OrderData;
import in.arjun.model.entity.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutDetails {

    private Customer customer;
    private OrderData orderData;
    private Address address;
    private List<OrderItems> orderItems;

}
