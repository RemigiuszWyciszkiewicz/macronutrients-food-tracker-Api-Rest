package com.Remigiusz.MacronutrientsApiREST.RequestAndRespone;

import lombok.Data;

import javax.persistence.Column;

@Data
public class NewProductForm {

    public NewProductForm() {
    }

    private String name;
    private int calories;
    private float protein;
    private float fats;
    private float carbohydrates;
    private long userId;
}
