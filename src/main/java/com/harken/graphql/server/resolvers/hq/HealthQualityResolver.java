package com.harken.graphql.server.resolvers.hq;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.harken.graphql.server.domain.hq.HealthQuality;
import com.harken.graphql.server.domain.hq.HealthQualityHistory;
import com.harken.graphql.server.domain.hq.HealthQualityHistoryInput;
import com.harken.graphql.server.domain.hq.HistoryPoint;
import com.harken.graphql.server.domain.measure.DiabetesIndicator;
import com.harken.graphql.server.repositories.hq.HealthQualityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class HealthQualityResolver implements GraphQLQueryResolver {

    private HealthQualityRepository healthQualityRepository;

    public List<HealthQuality> healthQualities() {
        return healthQualityRepository.findAllHealthQualitySummaries();
    }

    public HealthQuality healthQualityById(ObjectId id) {
        return healthQualityRepository.findById(id);
    }

    @Cacheable(value = "hq-summary-cache", key = "{#city, #measureDate}")
    public HealthQuality healthQualitySummary(ObjectId city, ZonedDateTime measureDate) {
        return healthQualityRepository.findHealthQuality(city, measureDate);
    }

    //add sort and limit or paging per year - have max of 2 years data + all - or if highcharts then small foot print
    //so no need for paging!
    @Cacheable(value = "hq-history-cache", key = "{#healthQualityHistoryInput.city, #healthQualityHistoryInput.diabetesIndicator}")
    public HealthQualityHistory healthQualityHistory(HealthQualityHistoryInput healthQualityHistoryInput) {
        List<HealthQuality> healthQualities = healthQualityRepository.findHealthQuality(healthQualityHistoryInput.getCity(), healthQualityHistoryInput.getDiabetesIndicator());

        List<HistoryPoint> historySeries = new ArrayList<>();
        healthQualities.forEach(hq -> {
            long measureDateEpoch = hq.getMeasureDate().toInstant().toEpochMilli();
            int dailyLevel = 0;
            List<String> reports = new ArrayList<>();

            if (DiabetesIndicator.CHOLESTEROL ==  healthQualityHistoryInput.getDiabetesIndicator()) {
                if (hq.getCholesterolLevel() != null) {
                    dailyLevel = Math.round(hq.getCholesterolLevel().getTotalLevel() / hq.getCholesterolLevel().getCount());
                    reports.addAll(hq.getCholesterolLevel().getReports());
                }
            } else {
                if (hq.getBloodsugarLevel() != null) {
                    dailyLevel = Math.round(hq.getBloodsugarLevel().getTotalLevel() / hq.getBloodsugarLevel().getCount());
                    reports.addAll(hq.getBloodsugarLevel().getReports());
                }
            }

            historySeries.add(HistoryPoint.builder()
                    .measureDate(measureDateEpoch)
                    .level(dailyLevel)
                    .reports(reports)
                    .build());
        });

        return HealthQualityHistory.builder().city(healthQualityHistoryInput.getCity()).histories(historySeries).build();
    }
}
