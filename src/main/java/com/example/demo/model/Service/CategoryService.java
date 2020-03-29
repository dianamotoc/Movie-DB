package com.example.demo.model.Service;

import com.example.demo.model.DomainObject.Category;

public interface CategoryService extends AbstractService {
    void saveCategory(Category newCategory);
    void deleteCategoryById(int id);
    void updateCategory(int id, Category newCategory);

    Category findCategoryById(int id);
}
