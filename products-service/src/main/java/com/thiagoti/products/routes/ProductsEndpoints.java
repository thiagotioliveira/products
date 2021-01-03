package com.thiagoti.products.routes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductsEndpoints {

  public static final String DIRECT_GET_START = "direct:products.get.start";

  public static final String DIRECT_GET_EXECUTE = "direct:products.get.execute";

  public static final String DIRECT_READ_START = "direct:products.read.start";

  public static final String DIRECT_READ_EXECUTE = "direct:products.read.execute";

  public static final String DIRECT_UPDATE_START = "direct:products.update.start";

  public static final String DIRECT_UPDATE_EXECUTE = "direct:products.update.execute";

  public static final String DIRECT_DELETE_START = "direct:products.delete.start";

  public static final String DIRECT_DELETE_EXECUTE = "direct:products.delete.execute";

  public static final String DIRECT_POST_START = "direct:products.post.start";

  public static final String DIRECT_POST_EXECUTE = "direct:products.post.execute";

}
