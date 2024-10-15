package in.arjun.model.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Long phoneNumber;

//    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
//    private OrderData orderData;
}
