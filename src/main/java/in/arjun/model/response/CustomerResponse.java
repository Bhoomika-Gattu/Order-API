package in.arjun.model.response;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Long phoneNumber;
}
