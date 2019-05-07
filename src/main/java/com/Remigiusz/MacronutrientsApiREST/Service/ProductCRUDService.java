package com.Remigiusz.MacronutrientsApiREST.Service;

import java.util.List;

import com.Remigiusz.MacronutrientsApiREST.DAO.NotAcceptedProduct;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
import com.Remigiusz.MacronutrientsApiREST.DAO.User;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception400_BAD_REQUEST;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.Repository.NotAcceptedProductsRepository;
import com.Remigiusz.MacronutrientsApiREST.Repository.ProductsRepository;
import com.Remigiusz.MacronutrientsApiREST.RequestAndRespone.NewProductForm;
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
    public void saveProduct(NewProductForm product) {

        User user=userService.findUserById(product.getUserId());

        notAcceptedProductsRepository.findByName(product.getName()).ifPresent(notAcceptedProduct -> { throw new Exception400_BAD_REQUEST("This product already exists!"); });
        productsRepository.findProductORMSByName(product.getName()).ifPresent(notAcceptedProduct -> { throw new Exception400_BAD_REQUEST("This product already exists!"); });

        NotAcceptedProduct notAcceptedProduct= new NotAcceptedProduct(
                product.getName(),
                product.getCalories(),
                product.getProtein(),
                product.getFats(),
                product.getCarbohydrates(),
                user);

        notAcceptedProductsRepository.save(notAcceptedProduct);

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
        return user.getNotAcceptedProducts();
    }
    @Transactional
    public void deleteProduct(String name) {
        productsRepository.deleteByName(name);
    }
    @Transactional
    public void deleteNotAcceptedProduct(String name) {
        notAcceptedProductsRepository.deleteByName(name);
    }

    @Transactional
    public Product getProductbyName(String name) {
        Product product =productsRepository.findProductORMSByName(name)
                .orElseThrow(() -> new Exception404_NOT_FOUND("There is not product like - "+name));
        return product;
    }
}
