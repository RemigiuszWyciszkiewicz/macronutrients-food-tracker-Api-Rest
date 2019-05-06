package com.Remigiusz.MacronutrientsApiREST.DAO;

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


    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;




}
