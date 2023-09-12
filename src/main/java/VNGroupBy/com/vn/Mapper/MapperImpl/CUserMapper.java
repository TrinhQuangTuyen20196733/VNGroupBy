package VNGroupBy.com.vn.Mapper.MapperImpl;

import VNGroupBy.com.vn.DTO.request.RegisterReq;
import VNGroupBy.com.vn.DTO.response.UserRes;
import VNGroupBy.com.vn.Entity.RoleEntity;
import VNGroupBy.com.vn.Entity.UserEntity;
import VNGroupBy.com.vn.Mapper.Mapper;
import VNGroupBy.com.vn.Repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CUserMapper implements Mapper<UserEntity, UserRes> {

    private final RoleRepository roleRepository;

    public CUserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    @Override
    public UserEntity toEntity(UserRes dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(dto.getPassword());
        RoleEntity role = roleRepository.findByCode(dto.getRole()).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<UserRes,UserEntity> typeMap =  modelMapper.createTypeMap(UserRes.class,UserEntity.class);
        typeMap.addMappings(mapping->mapping.map(src->role,UserEntity::setRole));
        typeMap.addMappings(mapping->mapping.map(src->password,UserEntity::setPassword));
        return modelMapper.map(dto,UserEntity.class);
    }

    @Override
    public UserRes toDTO(UserEntity entity) {
        String role = entity.getRole().getCode();

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<UserEntity,UserRes> typeMap =  modelMapper.createTypeMap(UserEntity.class,UserRes.class);
        typeMap.addMappings(mapping->mapping.map(src->role,UserRes::setRole));
        return modelMapper.map(entity,UserRes.class);
    }

    @Override
    public List<UserRes> toDTOList(List<UserEntity> entityList) {
        return null;
    }

    @Override
    public List<UserEntity> toEntityList(List<UserRes> dtoList) {
        return null;
    }
}
