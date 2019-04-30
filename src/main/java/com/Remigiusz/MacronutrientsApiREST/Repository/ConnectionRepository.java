package com.Remigiusz.MacronutrientsApiREST.Repository;

import com.Remigiusz.MacronutrientsApiREST.DAO.Day;
import com.Remigiusz.MacronutrientsApiREST.DAO.DayProductsConnection;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<DayProductsConnection,Integer> {


    DayProductsConnection getDayProductsConnectionORMByProductAndDayAndAmount(Product product, Day day, int amount);


}
