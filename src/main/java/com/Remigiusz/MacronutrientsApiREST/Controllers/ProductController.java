package com.Remigiusz.MacronutrientsApiREST.Controllers;

import com.Remigiusz.MacronutrientsApiREST.DAO.NotAcceptedProduct;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.Service.ProductCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
public class ProductController {


    @Autowired
    ProductCRUDService crudService;



    @PostMapping("/product")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void saveProduct(@RequestBody NotAcceptedProduct product)
    {
        crudService.saveProduct(product);
    }

    @DeleteMapping("/product")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteProduct(@RequestParam("name") String name)
    {
        if(crudService.getProductbyName(name)== null) throw new Exception404_NOT_FOUND("There is not product like - "+name);
        else crudService.deleteProduct(name);

    }

    @GetMapping("/product/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    Product getProductById(@PathVariable int id)
    {
        return crudService.getProductbyId(id);
    }


    @GetMapping("/product")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    Product getProductByName(@RequestParam(value = "name",required = true) String name)
    {
      return crudService.getProductbyName(name);
    }

    @GetMapping("/acceptedProducts")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    List<Product> getAllAcceptedProducts()
    {
        List<Product> productList=crudService.getAllProducts();
        return productList;

    }


    @GetMapping("/NotAcceptedProduct/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<List<NotAcceptedProduct>> getAllNotAcceptedProducts(@PathVariable long id)
    {
        List<NotAcceptedProduct> productList=crudService.getNotAcceptedProducts(id);

        return ResponseEntity.ok(productList);
    }
}
