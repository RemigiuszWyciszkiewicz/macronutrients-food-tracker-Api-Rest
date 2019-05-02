
package com.Remigiusz.MacronutrientsApiREST.Controllers;

import com.Remigiusz.MacronutrientsApiREST.DAO.Day;
import com.Remigiusz.MacronutrientsApiREST.DAO.DayProductsConnection;
import com.Remigiusz.MacronutrientsApiREST.DAO.Product;

import com.Remigiusz.MacronutrientsApiREST.Service.DayCRUDService;
import com.Remigiusz.MacronutrientsApiREST.Service.ProductCRUDService;
import com.Remigiusz.MacronutrientsApiREST.Service.UserService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("/")
public class DayController {

    @Autowired
    DayCRUDService dayCRUDService;
    @Autowired
    ProductCRUDService productCRUDService;
    @Autowired
    UserService userService;

    @PostMapping("/day/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createDayForSpecificUser(@PathVariable(name = "id", required = true) long userId,@RequestParam(name = "date",required = true) String s)
    {
        System.out.println(s);

        dayCRUDService.addNewDay(userId,s);
    }

    @GetMapping("/days/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    List<Day> getDays(@PathVariable(name = "id", required = true) int userId)
    {
        List<Day> days=userService.getDaysByUser(userId);
        return days;
    }

    @PostMapping("/productOfDay/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    void createConnection(@RequestBody JSONObject s,@PathVariable(name = "id",required = true) int userId) throws ParseException {

        System.out.println(s);
        dayCRUDService.saveConnection(s,userId);
    }

    @GetMapping("/productOfDay/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    JSONArray getProducts(@RequestParam(name = "date") String s,@PathVariable(name = "id",required = true) int userId)
    {
        return dayCRUDService.getProductsOfDay(s,userId);
    }

    @DeleteMapping("/productsOfDay/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    void deleteProduct(@RequestParam(name = "name") String productName,@RequestParam(name = "date") String date,@RequestParam(name = "amount") int amount,@PathVariable(name = "id",required = true) int userId)
    {
        dayCRUDService.deleteConnection(productName,date,amount,userId);
    }
}

