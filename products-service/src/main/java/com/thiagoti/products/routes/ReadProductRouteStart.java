package com.thiagoti.products.routes;

import com.thiagoti.api.products.spec.ProductItem;

public interface ReadProductRouteStart {

  ProductItem execute(String id);

}
