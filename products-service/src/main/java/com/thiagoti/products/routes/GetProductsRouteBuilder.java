package com.thiagoti.products.routes;

import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_GET_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_GET_START;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GetProductsRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from(DIRECT_GET_START).to(DIRECT_GET_EXECUTE);
  }


}
