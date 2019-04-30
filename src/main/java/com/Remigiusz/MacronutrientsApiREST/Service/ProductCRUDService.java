package com.Remigiusz.MacronutrientsApiREST.Service;

import java.util.List;

import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
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
    public void saveProduct(Product product) {

        String[] name=productsRepository.checkIfExists(product.getName());

        if(name.length==0) productsRepository.save(product);
        else throw new Exception400_BAD_REQUEST("This product already exists - "+ product.getName());
    }

    @Transactional
    public Product getProductbyId(int id) {
        Product product =productsRepository.findById(id)
                .orElseThrow(() -> new Exception404_NOT_FOUND("Product with id - "+id+" does not exist"));
         return product;
    }


    @Transactional
    public List<Product> getAllProducts() {
        productsRepository.findAll();
        return productsRepository.findAll();
    }
    @Transactional
    public void deleteProduct(String name) {
        productsRepository.deleteByName(name);
    }

    @Transactional
    public Product getProductbyName(String name) {
        Product product =productsRepository.findProductORMSByName(name)
                .orElseThrow(() -> new Exception404_NOT_FOUND("There is not product like - "+name));
        return product;
    }
}
