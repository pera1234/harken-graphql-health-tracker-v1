package com.harken.graphql.server.resolvers.user;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.harken.graphql.server.domain.report.Report;
import com.harken.graphql.server.domain.user.User;
import com.harken.graphql.server.repositories.report.ReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class UserReportsResolver implements GraphQLResolver<User> {

    private ReportRepository reportRepository;

    public List<Report> reports(User user) {
        return reportRepository.findReportsByUser(user.getId()).orElse(null);
    }

}
