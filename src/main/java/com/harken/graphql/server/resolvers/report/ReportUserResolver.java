package com.harken.graphql.server.resolvers.report;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.harken.graphql.server.domain.report.Report;
import com.harken.graphql.server.domain.user.User;
import com.harken.graphql.server.repositories.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class ReportUserResolver implements GraphQLResolver<Report> {

    private UserRepository userRepository;

    public User user(Report report) {
        return userRepository.findById(report.getUserId());
    }

}
