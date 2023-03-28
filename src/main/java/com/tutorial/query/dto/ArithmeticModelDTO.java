package com.tutorial.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArithmeticModelDTO {

    private Long id;

    private String fullName;

    private Long salarySetahun;

    private Long salaryPlusBonus;

}
