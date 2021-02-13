package com.harken.graphql.server.resolvers.report;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.harken.graphql.server.domain.report.Report;
import com.harken.graphql.server.domain.report.ReportPage;
import com.harken.graphql.server.domain.report.ReportPageInput;
import com.harken.graphql.server.repositories.report.ReportPagingRepository;
import com.harken.graphql.server.repositories.report.ReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class ReportResolver implements GraphQLQueryResolver {
    private final ReportRepository reportRepository;
    private final ReportPagingRepository reportPagingRepository;

    public Report reportById(ObjectId id) {
        return reportRepository.findById(id);
    }

    public List<Report> reportsByUser(ObjectId userId) {
        return reportRepository.findReportsByUser(userId).orElse(null);
    }

    public List<Report> reportsIn(List<String> reportIds) {
        return reportRepository.findReports(reportIds).orElse(null);
    }

    public ReportPage reports(ReportPageInput reportPageInput) throws JsonProcessingException {
        LOG.info("Report input:" + reportPageInput.toString());

        Page<Report> reportsPage = reportPagingRepository.findAll(reportPageInput);

        ReportPage reportPage = ReportPage.builder()
                .totalElements(reportsPage.getTotalElements())
                .totalPages(reportsPage.getTotalPages())
                .page(reportsPage.getNumber())
                .content(reportsPage.getContent())
                .build();

        LOG.info("Report page:" + reportPage.toString());
        return reportPage;
    }

    /**
     * Currently not working with com.graphql-java (try com.graphql-java-kickstart)

     public Connection<Report> reportsPaginatedByCursor(int first, String after, int last, String before,
     DataFetchingEnvironment environment) {
     return new SimpleListConnection<>(Collections.singletonList(Report.builder().build())).get(environment);
     } */

}
