package VNGroupBy.com.vn.Mapper.MapperImpl;

import VNGroupBy.com.vn.DTO.request.ProductReq;
import VNGroupBy.com.vn.Entity.Product;
import VNGroupBy.com.vn.Mapper.Mapper;
import VNGroupBy.com.vn.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements Mapper<Product, ProductReq> {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Product toEntity(ProductReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ProductReq, Product> typeMap = modelMapper.createTypeMap(ProductReq.class, Product.class);
        var product = modelMapper.map(dto, Product.class);
        var category = categoryRepository.findByName(dto.category).get();
        product.setCategory(category);
        return product;

    }

    @Override
    public ProductReq toDTO(Product entity) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Product, ProductReq> typeMap = modelMapper.createTypeMap(Product.class, ProductReq.class);
        var productReq = modelMapper.map(entity, ProductReq.class);

        productReq.setCategory(entity.getCategory().getName());
        return productReq;
    }

    @Override
    public List<ProductReq> toDTOList(List<Product> entityList) {
        return entityList.stream().map(entity ->
                toDTO(entity)
        ).collect(Collectors.toList());

    }

    @Override
    public List<Product> toEntityList(List<ProductReq> dtoList) {
        return null;
    }
}
