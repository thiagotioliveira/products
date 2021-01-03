package com.thiagoti.products.routes;

import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.dto.UpdateProductDto;

public interface UpdateProductRouteStart {

  ProductItem execute(UpdateProductDto param);

}
