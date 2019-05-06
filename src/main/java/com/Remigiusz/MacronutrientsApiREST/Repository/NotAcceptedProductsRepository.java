package com.Remigiusz.MacronutrientsApiREST.Repository;

import com.Remigiusz.MacronutrientsApiREST.DAO.NotAcceptedProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotAcceptedProductsRepository extends JpaRepository<NotAcceptedProducts,Long> {

    Optional<NotAcceptedProducts> findByName(String name);
}
