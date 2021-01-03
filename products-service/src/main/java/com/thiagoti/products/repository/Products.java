package com.thiagoti.products.repository;

import java.util.List;
import java.util.Optional;

import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.dto.GetProductsDto;
import com.thiagoti.products.dto.PostProductDto;

public interface Products {

  List<ProductItem> findAll(GetProductsDto param);

  Optional<ProductItem> findById(String id);

  ProductItem update(ProductItem productItem);

  boolean deleteById(String id);

  ProductItem save(PostProductDto param);
}
