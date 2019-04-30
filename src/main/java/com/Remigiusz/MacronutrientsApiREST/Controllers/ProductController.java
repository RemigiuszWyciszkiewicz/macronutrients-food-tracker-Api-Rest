package com.Remigiusz.MacronutrientsApiREST.Controllers;

import com.Remigiusz.MacronutrientsApiREST.DAO.ProductORM;
import com.Remigiusz.MacronutrientsApiREST.Exceptions.Exception404_NOT_FOUND;
import com.Remigiusz.MacronutrientsApiREST.Service.ProductCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {


    @Autowired
    ProductCRUDService crudService;

    @PostMapping("/product")
    public void saveProduct(@RequestBody ProductORM productORM)
    {
        crudService.saveProduct(productORM);
    }

    @DeleteMapping("/product")
    public void deleteProduct(@RequestParam("name") String name)
    {
        if(crudService.getProductbyName(name)== null) throw new Exception404_NOT_FOUND("There is not product like - "+name);
        else crudService.deleteProduct(name);
    }

    @GetMapping("/product/{id}")
    ProductORM getProductById(@PathVariable int id)
    {
        return crudService.getProductbyId(id);


    }

    @GetMapping("/product")
    ProductORM getProductByName(@RequestParam(value = "name",required = true) String name)
    {
      return crudService.getProductbyName(name);
    }
    @GetMapping("/products")
    List<ProductORM> getAllProducts()
    {
        List<ProductORM> productList=crudService.getAllProducts();
        return productList;

    }
}
