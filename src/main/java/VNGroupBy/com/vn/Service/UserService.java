package VNGroupBy.com.vn.Service;

import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.Entity.UserEntity;

public interface UserService {
     UserEntity create(RegisterReq registerReq);
}
