package in.arjun.model.response;

import in.arjun.model.entity.OrderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashBoardResponse {

    private String orderTrackingNumber;
    private String totalQuantity;
    private String orderStatus;

    public static DashBoardResponse fromOrder(OrderData orders){
        return DashBoardResponse.builder()
                .orderTrackingNumber(orders.getOrderTrackingNumber())
                .totalQuantity(orders.getTotalQuantity())
                .orderStatus(orders.getOrderStatus())
                .build();
    }
}
