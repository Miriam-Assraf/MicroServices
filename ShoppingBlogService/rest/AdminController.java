package acs.rest;

import acs.logic.EnhancedBlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private EnhancedBlogPostService enhancedBlogPostService;

    @Autowired
    public AdminController(EnhancedBlogPostService enhancedBlogPostService) {
        this.enhancedBlogPostService = enhancedBlogPostService;
    }

    /*--------------------- DELETE APIS ------------------- */

    @RequestMapping(path = "/blog",
            method = RequestMethod.DELETE)
    public void deleteAllShopping() {
         this.enhancedBlogPostService.deleteAll();
    }

}
