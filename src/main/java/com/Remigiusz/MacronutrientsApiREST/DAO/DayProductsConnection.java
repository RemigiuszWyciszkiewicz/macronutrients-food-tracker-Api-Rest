package com.Remigiusz.MacronutrientsApiREST.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "day_product")
public class DayProductsConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "ilosc")
    int amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produkt_id")
    @JsonIgnore
    Product product;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "day_of_life_id")
    @JsonIgnore
    Day day;

    public DayProductsConnection() {
    }

}
