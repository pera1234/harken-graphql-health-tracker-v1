package com.harken.graphql.server.resolvers.city;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.harken.graphql.server.domain.city.City;
import com.harken.graphql.server.repositories.city.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class CityResolver implements GraphQLQueryResolver {
    public static final String CITIES_CACHE_KEY = "citiesCacheId";

    private CityRepository cityRepository;

    public City cityById(ObjectId id) {
        return cityRepository.findById(id);
    }

    public City city(String city) {
        return cityRepository.findOne(city);
    }

    @Cacheable(value = "city-cache", key = "#root.target.CITIES_CACHE_KEY")
    public List<City> cities() {
        return cityRepository.findAll(City.class);
    }
}
