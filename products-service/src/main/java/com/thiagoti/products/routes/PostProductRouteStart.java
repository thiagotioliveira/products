package com.thiagoti.products.routes;

import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.dto.PostProductDto;

public interface PostProductRouteStart {

  ProductItem execute(PostProductDto param);

}
