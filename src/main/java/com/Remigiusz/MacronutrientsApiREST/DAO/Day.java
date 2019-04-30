package com.Remigiusz.MacronutrientsApiREST.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "day")
public class Day {

    public Day() {
    }

    public Day(Date data) {
        this.date =data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "today")
    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH, CascadeType.MERGE
            , CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "day_product"
            ,joinColumns = @JoinColumn(name = "day_of_life_id")
            ,inverseJoinColumns =@JoinColumn(name = "produkt_id") )
    List<Product> products =new ArrayList<>();


    @OneToMany(mappedBy = "day",fetch = FetchType.LAZY)
    List<DayProductsConnection> ListOfConnections =new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade ={ CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    User userORM;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {


        SimpleDateFormat formated=new SimpleDateFormat("dd/MM/E");
        String result=formated.format(date);
        System.out.println(result);

        return ""+ date;
    }
}
