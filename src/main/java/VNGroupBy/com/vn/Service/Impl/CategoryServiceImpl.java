package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.Entity.Category;
import VNGroupBy.com.vn.Repository.CategoryRepository;
import VNGroupBy.com.vn.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
