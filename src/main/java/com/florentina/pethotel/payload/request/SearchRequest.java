package com.florentina.pethotel.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class SearchRequest {
    private String cityName;
    private LocalDate startDate;
    private LocalDate endDate;
}
