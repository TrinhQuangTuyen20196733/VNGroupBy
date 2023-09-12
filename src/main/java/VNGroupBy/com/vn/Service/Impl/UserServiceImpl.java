package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.DTO.request.UpdatePassReq;
import VNGroupBy.com.vn.DTO.response.MessagesResponse;
import VNGroupBy.com.vn.DTO.response.UserRes;
import VNGroupBy.com.vn.Entity.ShoppingCart;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Entity.Wallet;
import VNGroupBy.com.vn.Mapper.MapperImpl.CUserMapper;
import VNGroupBy.com.vn.Mapper.MapperImpl.UserMapper;
import VNGroupBy.com.vn.Repository.ShoppingCartRepository;
import VNGroupBy.com.vn.Repository.UserRepository;
import VNGroupBy.com.vn.Repository.WalletRepository;
import VNGroupBy.com.vn.Security.UserDetails;
import VNGroupBy.com.vn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CUserMapper cuserMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserEntity create(RegisterReq registerReq) {

        var user = userRepository.save(userMapper.toEntity(registerReq));
        Wallet wallet = new Wallet(user);
        walletRepository.save(wallet);
        ShoppingCart shoppingCart = new ShoppingCart(user,0);
        shoppingCartRepository.save(shoppingCart);
        return user;
    }

    @Override
    public UserEntity findById(long Id) {
        var userOptional = userRepository.findById(Id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        ;
        return null;
    }

    @Override
    public UserRes getInfo() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails) authentication.getPrincipal();
        var userId = user.getId();
        var userAccountOptional = userRepository.findById(userId);
        if (userAccountOptional.isPresent()) {
            return cuserMapper.toDTO(userAccountOptional.get());
        }
        return null;
    }

    @Override
    public MessagesResponse Update(UserRes userRes) {
        MessagesResponse ms = new MessagesResponse() ;
        try {
            userRepository.save(cuserMapper.toEntity(userRes));

        }
        catch (Exception e) {
            ms.code = 500;
            ms.message = "Cập nhật tài khoản người dùng thất bại!";
        }
        return  ms;
    }

    @Override
    public MessagesResponse UpdatePass(UpdatePassReq updatePassReq) {
        MessagesResponse ms = new MessagesResponse();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (UserDetails) authentication.getPrincipal();
        var userId = user.getId();
        var userAccountOptional = userRepository.findById(userId);
        if (userAccountOptional.isPresent()) {
            var userAccount = userAccountOptional.get();
            if (passwordEncoder.matches(updatePassReq.currentPassword, userAccount.getPassword())) {
               String password = passwordEncoder.encode(updatePassReq.newPassword);
                userAccount.setPassword(password);
                userRepository.save(userAccount);
            } else {
                ms.code = 400;
                ms.message="Mật khẩu hiện tại bạn nhập không đúng. Vui lòng nhập lại!";
            }
        }
        return  ms;
    }

}
