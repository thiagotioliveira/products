package com.thiagoti.products.dto;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@With
public class UpdateProductDto {

  @NotNull
  private String id;

  private Optional<String> name;

  private Optional<String> brand;
}
