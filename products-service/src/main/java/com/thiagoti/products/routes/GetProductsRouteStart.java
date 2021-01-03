package com.thiagoti.products.routes;

import java.util.List;

import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.dto.GetProductsDto;

public interface GetProductsRouteStart {

  List<ProductItem> execute(GetProductsDto param);

}
