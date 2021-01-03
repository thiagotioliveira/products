package com.thiagoti.products.api;

import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_DELETE_START;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_GET_START;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_POST_START;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_READ_START;
import static com.thiagoti.products.routes.ProductsEndpoints.DIRECT_UPDATE_START;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.camel.Produce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.thiagoti.api.products.spec.PostProductRequestBody;
import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.api.products.spec.PutProductRequestBody;
import com.thiagoti.products.dto.GetProductsDto;
import com.thiagoti.products.dto.PostProductDto;
import com.thiagoti.products.dto.UpdateProductDto;
import com.thiagoti.products.exception.ProductNotFoundException;
import com.thiagoti.products.routes.DeleteProductRouteStart;
import com.thiagoti.products.routes.GetProductsRouteStart;
import com.thiagoti.products.routes.PostProductRouteStart;
import com.thiagoti.products.routes.ReadProductRouteStart;
import com.thiagoti.products.routes.UpdateProductRouteStart;
import com.thiagoti.products.spec.api.ProductController;

@RestController
public class ProductControllerImpl implements ProductController {

  @Produce(DIRECT_GET_START)
  private GetProductsRouteStart getProductsRouteStart;

  @Produce(DIRECT_READ_START)
  private ReadProductRouteStart readProductRouteStart;

  @Produce(DIRECT_UPDATE_START)
  private UpdateProductRouteStart updateProductRouteStart;

  @Produce(DIRECT_DELETE_START)
  private DeleteProductRouteStart deleteProductRouteStart;

  @Produce(DIRECT_POST_START)
  private PostProductRouteStart postProductRouteStart;

  @Override
  public ResponseEntity<List<ProductItem>> getProductItems(String name, String brand) {
    return ResponseEntity
        .ok(getProductsRouteStart.execute(new GetProductsDto(Optional.ofNullable(name), Optional.ofNullable(brand))));
  }

  @Override
  public ResponseEntity<ProductItem> getProductItemById(String id) {
    return ResponseEntity.ok(readProductRouteStart.execute(id));
  }

  @Override
  public ResponseEntity<ProductItem> updatePutProductRequestBody(
      String id,
      @Valid PutProductRequestBody putProductRequestBody) {
    return ResponseEntity.accepted().body(updateProductRouteStart.execute(new UpdateProductDto(
        id,
        Optional.ofNullable(putProductRequestBody.getName()), Optional.ofNullable(putProductRequestBody.getBrand()))));
  }

  @Override
  public ResponseEntity<?> deleteProductById(String id) {
    if (deleteProductRouteStart.execute(id)) {
      return ResponseEntity.accepted().build();
    }
    throw new ProductNotFoundException();
  }

  @Override
  public ResponseEntity<ProductItem> createPostProductRequestBody(
      @Valid PostProductRequestBody postProductRequestBody) {
    ProductItem newItem = postProductRouteStart
        .execute(new PostProductDto(postProductRequestBody.getName(), postProductRequestBody.getBrand()));
    return new ResponseEntity<>(newItem, HttpStatus.CREATED);
  }

}
