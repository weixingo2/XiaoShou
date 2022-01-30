package com.cpt.entity;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private Integer status;

    private String vartar;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVartar() {
        return vartar;
    }

    public void setVartar(String vartar) {
        this.vartar = vartar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
