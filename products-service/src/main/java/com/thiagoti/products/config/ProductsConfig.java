package com.thiagoti.products.config;

import static com.thiagoti.products.utils.Utils.readValueFromFile;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.repository.Products;
import com.thiagoti.products.repository.impl.ProductsCollectionImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProductsConfig {

  private final ObjectMapper mapper;

  @Bean
  public Products products() throws Exception {
    return new ProductsCollectionImpl(
        new ArrayList<>(
            Arrays.asList(mapper.readValue(readValueFromFile("/data/products.json"), ProductItem[].class))));
  }

}
