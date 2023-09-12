package VNGroupBy.com.vn.DTO.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data

@Component
public class MessagesResponse {
    public  int code = 200;
    public  String message = "Successfully";
    public Object data;
    public MessagesResponse(){
        code=200;
        message="Successfully";
    }


}
