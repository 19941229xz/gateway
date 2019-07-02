package com.example.gateway.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class User implements Serializable {


    private int id;

    private String userName;


    private String password;


    private String roleId;

}
