package net.javaguides.springboot.tutorial.model;

import lombok.Data;

@Data
public class ApiRespone {
    private int statusCode;
    private Object data;

    public ApiRespone(int statusCode, Object data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
