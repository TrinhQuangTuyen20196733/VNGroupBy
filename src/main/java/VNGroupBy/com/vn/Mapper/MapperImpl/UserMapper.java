package VNGroupBy.com.vn.Mapper.MapperImpl;

import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.Entity.RoleEntity;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Mapper.Mapper;
import VNGroupBy.com.vn.Repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements Mapper<UserEntity, RegisterReq> {

    private final RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    @Override
    public UserEntity toEntity(RegisterReq dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(dto.getPassword());
        RoleEntity role = roleRepository.findByCode(dto.getRole()).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<RegisterReq,UserEntity> typeMap =  modelMapper.createTypeMap(RegisterReq.class,UserEntity.class);
        typeMap.addMappings(mapping->mapping.map(src->role,UserEntity::setRole));
        typeMap.addMappings(mapping->mapping.map(src->password,UserEntity::setPassword));
        return modelMapper.map(dto,UserEntity.class);
    }

    @Override
    public RegisterReq toDTO(UserEntity entity) {
        return null;
    }

    @Override
    public List<RegisterReq> toDTOList(List<UserEntity> entityList) {
        return null;
    }

    @Override
    public List<UserEntity> toEntityList(List<RegisterReq> dtoList) {
        return null;
    }
}
