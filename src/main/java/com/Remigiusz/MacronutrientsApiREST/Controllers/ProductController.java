package com.Remigiusz.MacronutrientsApiREST.Controllers;

import com.Remigiusz.MacronutrientsApiREST.DAO.NotAcceptedProduct;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.RequestAndRespone.ProductForm;
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



    @PostMapping("/notAcceptedProduct")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void saveNotAcceptedProduct(@RequestBody ProductForm product)
    {
        //It capitalizes first letter
        product.setName(product.getName().substring(0,1).toUpperCase()+product.getName().substring(1));

        crudService.saveNotAcceptedProduct(product);
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public void saveProduct(@RequestBody ProductForm product)
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

    @DeleteMapping("/notAcceptedProduct")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteNotAcceptedProduct(@RequestParam("name") String name)
    {
        crudService.deleteNotAcceptedProduct(name);
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
    ResponseEntity<List<NotAcceptedProduct>> getAllNotAcceptedProductsByUserID(@PathVariable long id)
    {
        List<NotAcceptedProduct> productList=crudService.getNotAcceptedProductsByUser(id);

        return ResponseEntity.ok(productList);
    }
    @GetMapping("/NotAcceptedProduct")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<NotAcceptedProduct>> getAllNotAcceptedProducts()
    {
        return ResponseEntity.ok(crudService.getNotAcceptedProducts());
    }



}
