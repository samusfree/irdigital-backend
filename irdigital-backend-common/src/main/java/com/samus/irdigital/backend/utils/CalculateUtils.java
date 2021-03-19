package com.samus.irdigital.backend.utils;

import com.samus.irdigital.backend.api.client.dto.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalculateUtils {
    @Value("${app.peru.life.expectancy}")
    private Integer lifeExpectancy;

    public LocalDate getPossibleDateOfDeath(Integer age) {
        if (age > lifeExpectancy) {
            return LocalDate.now();
        }

        int yearsToLive = lifeExpectancy - age;
        return LocalDate.now().plusYears(yearsToLive);
    }

    public BigDecimal getAverageAgeClients(List<Client> clients) {
        return calculateAverageAge(getAges(clients));
    }

    public BigDecimal getStandardDeviation(List<Client> clients) {
        List<Integer> ages = getAges(clients);
        BigDecimal averageAge = calculateAverageAge(ages);
        BigDecimal sumSquareDistanceToAverage = BigDecimal.ZERO;
        for (Integer age : ages) {
            BigDecimal absoluteValueOfMinus = new BigDecimal(age).subtract(averageAge).abs();
            sumSquareDistanceToAverage = sumSquareDistanceToAverage.add(absoluteValueOfMinus.pow(2));
        }
        return sumSquareDistanceToAverage.divide(new BigDecimal(clients.size()), 2, RoundingMode.HALF_DOWN)
                .sqrt(new MathContext(2));
    }

    private BigDecimal calculateAverageAge(List<Integer> ages) {
        Integer sum = 0;
        for (Integer age : ages) {
            sum += age;
        }
        return new BigDecimal(sum).divide(new BigDecimal(ages.size()), 2, RoundingMode.HALF_DOWN);
    }

    private List<Integer> getAges(List<Client> clientes) {
        return clientes.stream().map(Client::getAge).collect(Collectors.toList());
    }
}
