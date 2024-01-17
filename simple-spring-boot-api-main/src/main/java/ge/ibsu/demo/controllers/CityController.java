package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddCity;
import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.RequestData;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.services.CityService;
import ge.ibsu.demo.services.CustomerService;
import ge.ibsu.demo.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<City> getAll() {
        return cityService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public City getById(@PathVariable Long id) {
        return cityService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public City add(@RequestBody AddCity addCity) throws Exception {
        GeneralUtil.checkRequiredProperties(addCity, Arrays.asList("city"));

        return cityService.addEditCity(addCity, null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public Boolean delete(@PathVariable Long id) {
        return cityService.deleteCity(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public City edit(@RequestBody AddCity addCity, @PathVariable Long id) throws Exception {
        GeneralUtil.checkRequiredProperties(addCity, Arrays.asList("city"));
        return cityService.addEditCity(addCity, id);
    }


}




