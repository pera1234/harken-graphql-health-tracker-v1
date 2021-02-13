package com.harken.graphql.server.resolvers.user;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.harken.graphql.server.domain.city.City;
import com.harken.graphql.server.domain.user.Address;
import com.harken.graphql.server.repositories.city.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class UserCityResolver implements GraphQLResolver<Address> {

    private CityRepository cityRepository;

    public City city(Address address) {
        return cityRepository.findById(address.getCity());
    }

}
