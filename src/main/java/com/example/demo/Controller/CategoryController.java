package com.example.demo.Controller;

import com.example.demo.model.DomainObject.Category;
import com.example.demo.model.Exception.UnFoundResultData;
import com.example.demo.model.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category){
        categoryService.saveCategory(category);
        return "index";
    }

    @GetMapping("/categoryForm")
    public String categoryForm(Model model)
    {
        model.addAttribute("category", new Category());
        return "categoryForm";
    }

    @GetMapping("/deleteCategory")
    public String deleteCategoryById(@RequestParam(name="id", required=false, defaultValue="World") int id){
        try {
            categoryService.deleteCategoryById(id);
        }
        catch (UnFoundResultData e){
            return e.redirect_page();
        }
        return "categoryForm";
    }

    @GetMapping("/updateCategoryForm")
    public String updateActorForm(Model model, @RequestParam(name = "id", required = true) int id)
    {
        Category oldCategory =  categoryService.findCategoryById(id);
        model.addAttribute("oldCategory", oldCategory);
        return "categoryUpdateForm";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute Category category, @RequestParam(name = "id", required = true) int id){
        categoryService.updateCategory(id,category);
        return "index";
    }
}
