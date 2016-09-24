package br.com.elotech.karina.domain;

import java.util.Date;

public class License {

    private Long cliente;

    private String code;

    private Module module;

    private Date date;

    private String name;

    private String password;

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public License withCliente(final Long cliente) {
        this.cliente = cliente;
        return this;
    }

    public License withCode(final String code) {

        this.code = code;
        return this;
    }

    public License withModule(final Module module) {
        this.module = module;
        return this;

    }

    public License withDate(final Date date) {

        this.date = date;
        return this;

    }

    public License withName(final String name) {
        this.name = name;
        return this;

    }

    public License withPassword(final String password) {

        this.password = password;
        return this;

    }

    @Override
    public String toString() {
        return "License [code=" + code + ", module=" + module + ", date=" + date + ", name=" + name + ", password="
                + password + "]";
    }

    public boolean hasKey(Long cliente, Module module, String name) {

        return this.cliente.equals(cliente) && this.module == module && this.name.equals(name);
    }

    public boolean isValid() {
        return this.code != null && this.module != null && this.date != null && this.name != null;
    }
}
