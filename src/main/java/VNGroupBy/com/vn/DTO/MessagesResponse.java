package VNGroupBy.com.vn.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class MessagesResponse {
    public  int code = 200;
    public  String message = "Successfully";
    public Object data;
}
