package com.Remigiusz.MacronutrientsApiREST.Service;

import java.util.List;

import com.Remigiusz.MacronutrientsApiREST.DAO.ProductORM;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception400_BAD_REQUEST;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCRUDService {
//testeestetwtata
    @Autowired
    ProductsRepository productsRepository;

    @Transactional
    public void saveProduct(ProductORM productORM) {

        String[] name=productsRepository.checkIfExists(productORM.getName());

        if(name.length==0) productsRepository.save(productORM);
        else throw new Exception400_BAD_REQUEST("This product already exists - "+ productORM.getName());
    }

    @Transactional
    public ProductORM getProductbyId(int id) {
        ProductORM productORM=productsRepository.findById(id)
                .orElseThrow(() -> new Exception404_NOT_FOUND("Product with id - "+id+" does not exist"));
         return productORM;
    }


    @Transactional
    public List<ProductORM> getAllProducts() {
        productsRepository.findAll();
        return productsRepository.findAll();
    }
    @Transactional
    public void deleteProduct(String name) {
        productsRepository.deleteByName(name);
    }

    @Transactional
    public ProductORM getProductbyName(String name) {
        ProductORM productORM=productsRepository.findProductORMSByName(name)
                .orElseThrow(() -> new Exception404_NOT_FOUND("There is not product like - "+name));
        return productORM;
    }
}
