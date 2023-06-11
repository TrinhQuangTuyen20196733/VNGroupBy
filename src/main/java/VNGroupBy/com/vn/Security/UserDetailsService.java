package VNGroupBy.com.vn.Security;


import VNGroupBy.com.vn.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);
        if (user==null) {
            throw new UsernameNotFoundException(email);
        }
        return  new UserDetails(user);
    }

    @Transactional
    public org.springframework.security.core.userdetails.UserDetails loadUserById(int id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User can not found with id : " + id)
        );

        return new UserDetails(user);
    }
}
