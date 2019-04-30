package com.Remigiusz.MacronutrientsApiREST.Repository;

import com.Remigiusz.MacronutrientsApiREST.DAO.ProductORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<ProductORM,Integer> {

    @Modifying
    @Query("delete from ProductORM a where a.name=:name")
     void deleteByName(@Param("name") String name);
/*
    @Query("select a from ProductORM a where a.name=:name")
    ProductORM getByName(@Param("name") String name);*/

    @Query("select name from ProductORM a where a.name=:name")
    String[] checkIfExists(@Param("name") String name);

    // void deleteProductORMByName(String name);

    Optional<ProductORM> findProductORMSByName(String name);
}
