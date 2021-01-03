package com.thiagoti.products.routes;

import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_DELETE_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_DELETE_START;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from(DIRECT_DELETE_START).to(DIRECT_DELETE_EXECUTE);
  }


}
