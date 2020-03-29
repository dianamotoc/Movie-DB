package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Category;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Persistance.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category newCategory) {
        categoryRepository.save(newCategory);
    }

    @Override
    public void deleteCategoryById(int id) throws UnFoundResultData{
        if(null == categoryRepository.findById(id)){
            throw new UnFoundResultData();
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(int id, Category newCategory) {
        Category oldCategory = categoryRepository.findById(id);
        if(null == oldCategory){
            throw new UnFoundResultData();
        }
        oldCategory.setName(newCategory.getName());


        categoryRepository.save(oldCategory);
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id);
    }
}
