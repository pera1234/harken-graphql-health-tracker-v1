package com.harken.graphql.server.resolvers.report;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.harken.graphql.server.domain.city.City;
import com.harken.graphql.server.domain.report.Report;
import com.harken.graphql.server.repositories.city.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class ReportCityResolver implements GraphQLResolver<Report> {

    private CityRepository cityRepository;

    public City city(Report report) {
        return cityRepository.findById(report.getCityId());
    }

}
