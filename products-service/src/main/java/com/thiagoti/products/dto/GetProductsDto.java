package com.thiagoti.products.dto;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductsDto {

  private Optional<String> name;

  private Optional<String> brand;

}
