package com.Remigiusz.MacronutrientsApiREST.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "notacceptedproducts")
public class NotAcceptedProduct {

    public NotAcceptedProduct() {
    }

    public NotAcceptedProduct(String name, int calories, float protein, float fats, float carbohydrates, User user) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "kcal")
    private int calories;

    @Column(name = "protein")
    private float protein;

    @Column(name = "fats")
    private float fats;

    @Column(name = "carbs")
    private float carbohydrates;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;




}
