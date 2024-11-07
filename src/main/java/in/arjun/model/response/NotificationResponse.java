package in.arjun.model.response;

import in.arjun.model.entity.OrderData;
import in.arjun.model.entity.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {

   private OrderData orderData;
   private List<OrderItems> orderItems;


public static NotificationResponse fromOrderData(OrderData orderData){
return NotificationResponse.builder()
        .orderData(orderData)
        .orderItems(orderData.getOrderItems())
        .build();
}
}
