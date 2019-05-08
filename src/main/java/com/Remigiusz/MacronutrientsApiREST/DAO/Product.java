package com.Remigiusz.MacronutrientsApiREST.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    public Product() {

    }

    public Product(String name, int calories, float protein, float fats, float carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nazwa")
    private String name;
    @Column(name = "kcal")
    private int calories;
    @Column(name = "bialko")
    private float protein;
    @Column(name = "tluszcze")
    private float fats;
    @Column(name = "weglowodany")
    private float carbohydrates;


    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH
            , CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "day_product"
            ,joinColumns = @JoinColumn(name = "produkt_id")
            ,inverseJoinColumns = @JoinColumn(name = "day_of_life_id"))
    @JsonIgnore
    private List<Day> daysList=new ArrayList<>();

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private List<DayProductsConnection> connectionsList=new ArrayList<>();




    @Override
    public String toString() {
        return  name;
    }
}
