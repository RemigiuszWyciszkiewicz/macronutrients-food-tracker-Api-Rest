package com.Remigiusz.MacronutrientsApiREST.Repository;

import com.Remigiusz.MacronutrientsApiREST.DAO.NotAcceptedProduct;
import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotAcceptedProductsRepository extends JpaRepository<NotAcceptedProduct,Long> {

    Optional<NotAcceptedProduct> findByName(String name);

    void deleteByName(String name);

    /*List<NotAcceptedProduct> findAllByUser(User user);*/
}
