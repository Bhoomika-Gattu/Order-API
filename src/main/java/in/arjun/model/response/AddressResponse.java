package in.arjun.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {

    private Long id;
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private Long zipcode;
    private String country;

}
