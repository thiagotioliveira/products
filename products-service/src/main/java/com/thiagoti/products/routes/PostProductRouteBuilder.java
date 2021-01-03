package com.thiagoti.products.routes;

import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_POST_EXECUTE;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_POST_START;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PostProductRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from(DIRECT_POST_START).to(DIRECT_POST_EXECUTE);
  }


}
