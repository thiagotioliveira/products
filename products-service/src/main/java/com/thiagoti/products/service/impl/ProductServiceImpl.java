package com.thiagoti.products.service.impl;

import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_DELETE_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_GET_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_POST_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_READ_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_UPDATE_EXECUTE;

import java.util.List;
import java.util.Optional;

import org.apache.camel.Consume;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.dto.GetProductsDto;
import com.thiagoti.products.dto.PostProductDto;
import com.thiagoti.products.dto.UpdateProductDto;
import com.thiagoti.products.exception.ProductNotFoundException;
import com.thiagoti.products.repository.Products;
import com.thiagoti.products.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final Products products;

  @Consume(DIRECT_GET_EXECUTE)
  @Override
  public List<ProductItem> get(GetProductsDto param) {
    return products.findAll(param);
  }

  @Consume(DIRECT_READ_EXECUTE)
  @Override
  public ProductItem get(String id) {
    return products.findById(id).orElseThrow(ProductNotFoundException::new);
  }

  @Consume(DIRECT_UPDATE_EXECUTE)
  @Override
  public ProductItem update(UpdateProductDto param) {
    ProductItem productItem = products.findById(param.getId()).orElseThrow(ProductNotFoundException::new);

    if (param.getName().isPresent()) {
      productItem.setName(param.getName().get());
    }
    if (param.getBrand().isPresent()) {
      productItem.setBrand(param.getBrand().get());
    }

    return products.update(productItem);
  }

  @Consume(DIRECT_DELETE_EXECUTE)
  @Override
  public Boolean delete(String id) {
    return products.deleteById(id);
  }

  @Consume(DIRECT_POST_EXECUTE)
  @Override
  public ProductItem save(PostProductDto param) {
    Optional<ProductItem> optional = products
        .findAll(new GetProductsDto(Optional.of(param.getName()), Optional.empty())).stream().findFirst();

    if (Boolean.FALSE.equals(optional.isPresent())) {
      return products.save(param);
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "already exist product with same name");
  }

}
