package com.thiagoti.products.service;

import java.util.List;

import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.dto.GetProductsDto;
import com.thiagoti.products.dto.PostProductDto;
import com.thiagoti.products.dto.UpdateProductDto;

public interface ProductService {

  List<ProductItem> get(GetProductsDto param);

  ProductItem get(String id);

  ProductItem update(UpdateProductDto param);

  Boolean delete(String id);

  ProductItem save(PostProductDto param);

}
