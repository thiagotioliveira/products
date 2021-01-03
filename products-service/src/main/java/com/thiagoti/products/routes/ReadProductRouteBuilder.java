package com.thiagoti.products.routes;

import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_READ_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_READ_START;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReadProductRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from(DIRECT_READ_START).to(DIRECT_READ_EXECUTE);
  }


}
