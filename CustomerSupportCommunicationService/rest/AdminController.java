package acs.rest;

import acs.logic.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //DELETE
    @RequestMapping(path = "/",
            method = RequestMethod.DELETE)
    public void deleteAll() {
        this.adminService.deleteAll();
    }
}
