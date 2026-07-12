package com.luisotaviodias.model;

public class Person {
    private Integer id;
    private String name;
    private String cpf;
    private String email;
    private String telefone;

    public Person(Integer id, String name, String cpf, String email, String telefone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    
    public Person(String name, String cpf, String email, String telefone) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
