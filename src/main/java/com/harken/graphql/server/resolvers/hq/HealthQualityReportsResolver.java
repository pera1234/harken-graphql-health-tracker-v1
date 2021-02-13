package com.harken.graphql.server.resolvers.hq;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.harken.graphql.server.domain.hq.Level;
import com.harken.graphql.server.domain.report.Report;
import com.harken.graphql.server.repositories.report.ReportRepository;
//import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class HealthQualityReportsResolver implements GraphQLResolver<Level> {

    private ReportRepository reportRepository;

    public List<Report> reports(Level level) {
        return reportRepository.findReports(level.getReports()).orElse(null);
    }

}
