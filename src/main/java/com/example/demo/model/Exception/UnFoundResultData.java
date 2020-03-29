package com.example.demo.model.Exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class UnFoundResultData extends RuntimeException{

    private final String REDIRECT_PAGE = "dataNotFound";

    public String redirect_page(){
        return REDIRECT_PAGE;
    }
}
