package com.retheviper.springbootsample.api.v1.viewmodel;

import java.io.Serializable;

import lombok.Data;

/**
 * Admin page view model.
 *
 * @author retheviper
 */
@Data
public class AdminPageViewModel implements Serializable{

    /**
     * Name of admin page.
     */
    private String pageName;
}
