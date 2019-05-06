package com.Remigiusz.MacronutrientsApiREST.Repository;

import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Integer> {

    @Modifying
    @Query("delete from Product a where a.name=:name")
     void deleteByName(@Param("name") String name);

    @Query("select name from Product a where a.name=:name")
    String[] checkIfExists(@Param("name") String name);


    Optional<Product> findProductORMSByName(String name);
}
