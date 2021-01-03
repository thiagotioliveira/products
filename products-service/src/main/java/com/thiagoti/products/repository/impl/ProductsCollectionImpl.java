package com.thiagoti.products.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.dto.GetProductsDto;
import com.thiagoti.products.dto.PostProductDto;
import com.thiagoti.products.exception.ProductNotFoundException;
import com.thiagoti.products.repository.Products;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductsCollectionImpl implements Products {

  private List<ProductItem> list;

  @Override
  public List<ProductItem> findAll(GetProductsDto param) {
    return this.list.stream()
        .filter(
            i -> (param.getName().isPresent() && StringUtils.startsWithIgnoreCase(i.getName(), param.getName().get()))
                || !param.getName().isPresent())
        .filter(
            i -> (param.getBrand().isPresent()
                && StringUtils.startsWithIgnoreCase(i.getBrand(), param.getBrand().get()))
                || !param.getBrand().isPresent())
        .collect(Collectors.toList());
  }

  @Override
  public Optional<ProductItem> findById(String id) {
    return this.list.stream().filter(i -> StringUtils.equals(i.getId(), id)).findFirst();
  }

  @Override
  public ProductItem update(ProductItem productItem) {
    ProductItem finder = this.list.stream().filter(i -> StringUtils.equals(i.getId(), productItem.getId())).findFirst()
        .orElseThrow(ProductNotFoundException::new);
    int index = this.list.indexOf(finder);
    this.list.remove(index);
    this.list.add(index, productItem);
    return productItem;
  }

  @Override
  public boolean deleteById(String id) {
    Optional<ProductItem> optional = findById(id);
    return optional.isPresent() ? this.list.remove(optional.get()) : Boolean.FALSE;
  }

  @Override
  public ProductItem save(PostProductDto param) {
    final ProductItem newItem = new ProductItem().withId(UUID.randomUUID().toString()).withName(param.getName())
        .withBrand(param.getBrand());
    this.list.add(newItem);
    return newItem;
  }
}
