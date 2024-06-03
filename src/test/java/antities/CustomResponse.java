package antities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {

    private int category_id;
    private String created;
    private String seller_name;
    private int seller_id;
    private String email;
    private String first_name;
    List<CustomResponse> responses;
    private String responseBody;

}
