package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.DTO.request.UpdatePassReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.DTO.response.UserRes;
import VNGroupBy.com.vn.Entity.UserEntity;

import java.util.Optional;

public interface UserService {
     UserEntity create(RegisterReq registerReq);
     UserEntity findById(long Id);
     UserRes getInfo();

     MessagesResponse Update(UserRes userRes);

     MessagesResponse UpdatePass(UpdatePassReq updatePassReq);
}
