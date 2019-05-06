package com.Remigiusz.MacronutrientsApiREST.Service;

import java.util.List;

import com.Remigiusz.MacronutrientsApiREST.DAO.NotAcceptedProduct;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception400_BAD_REQUEST;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.Repository.NotAcceptedProductsRepository;
import com.Remigiusz.MacronutrientsApiREST.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductCRUDService {

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    NotAcceptedProductsRepository notAcceptedProductsRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void saveProduct(NotAcceptedProduct product) {

        NotAcceptedProduct notAcceptedProducts=notAcceptedProductsRepository.findByName(product.getName())
                .orElseGet(() -> notAcceptedProductsRepository.save(product));

        if (notAcceptedProducts != null) throw new Exception400_BAD_REQUEST("this product already exists");


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
    public List<NotAcceptedProduct> getNotAcceptedProducts(long id)
    {
        User user=userService.findUserById(id);
        return notAcceptedProductsRepository.findAllByUser(user);
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
