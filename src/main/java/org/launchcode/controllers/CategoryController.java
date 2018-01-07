package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    //This object will be the mechanism with which we interact with objects stored in the database.
    // Recall that Spring will do the work of creating a class that implements CategoryDao
    // and putting one of those objects in the categoryDao field when the application starts up. And all of this is thanks to the @Autowired annotation.
    @Autowired
    private CategoryDao categoryDao;

    //TODO doublecheck this index handler
    @RequestMapping(value="")
    public String index(Model model) {

        Iterable<Category> categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("title", "Categories");

        return "category/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String addDisplay(Model model) {

        model.addAttribute("title", "Add Category");
        model.addAttribute("category", new Category());

        return "category/add";

    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String addProcess(Model model,
                             @ModelAttribute @Valid Category category,
                             Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryDao.save(category);

        return "redirect:";

    }
}
