package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Mapper.MapperImpl.UserMapper;
import VNGroupBy.com.vn.Repository.UserRepository;
import VNGroupBy.com.vn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  UserMapper userMapper;

    @Override
    public UserEntity create(RegisterReq registerReq) {
        return  userRepository.save(userMapper.toEntity(registerReq));
    }
}
