package com.thiagoti.products.routes;

import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_UPDATE_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_UPDATE_START;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from(DIRECT_UPDATE_START).to(DIRECT_UPDATE_EXECUTE);
  }


}
