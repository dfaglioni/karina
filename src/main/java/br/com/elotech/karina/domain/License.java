package br.com.elotech.karina.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class License {

    private Integer cliente;

    private String code;

    private Module module;

    private LocalDate date;

    private String name;

    private String password;

}
