package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddCity;
import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.Paging;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.CityRepository;
import ge.ibsu.demo.repositories.CustomerRepository;
import ge.ibsu.demo.util.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City getById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CITY_NOT_FOUND"));
    }

    @Transactional
    public City addEditCity(AddCity addCity, Long id) throws Exception {
        City city = new City();
        if (id != null) {
            city = getById(id);
        }

        GeneralUtil.getCopyOf(addCity, city);


        return cityRepository.save(city);
    }

    @Transactional
    public Boolean deleteCity(Long id) {
        City city = getById(id);
        cityRepository.delete(city);
        return true;
    }

}
