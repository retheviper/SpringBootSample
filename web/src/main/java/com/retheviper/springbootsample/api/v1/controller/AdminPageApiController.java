package com.retheviper.springbootsample.api.v1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retheviper.springbootsample.api.v1.viewmodel.AdminPageViewModel;

import lombok.RequiredArgsConstructor;

/**
 * Admin page API controller class.
 *
 * @author retheviper
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/web/admin")
public class AdminPageApiController {

    /**
     * Access to specified admin page by name.
     *
     * @param name name of page
     * @return view model
     */
    @GetMapping("/{name}")
    public AdminPageViewModel getAdminPage(@PathVariable final String name) {
        final AdminPageViewModel model = new AdminPageViewModel();
        model.setPageName(String
                .format("Page Name: {}. This is Admin page. If your role is not admin, you can't access.", name));
        return model;
    }
}
