package com.Remigiusz.MacronutrientsApiREST.Repository;

import com.Remigiusz.MacronutrientsApiREST.DAO.DayORM;
import com.Remigiusz.MacronutrientsApiREST.DAO.DayProductsConnectionORM;
import com.Remigiusz.MacronutrientsApiREST.DAO.ProductORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<DayProductsConnectionORM,Integer> {


    DayProductsConnectionORM getDayProductsConnectionORMByProductAndDayAndAmount(ProductORM product, DayORM day,int amount);


}
