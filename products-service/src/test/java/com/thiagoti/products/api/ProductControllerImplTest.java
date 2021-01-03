package com.thiagoti.products.api;

import static com.thiagoti.products.utils.TestUtils.MOCK_STRING;
import static com.thiagoti.products.utils.TestUtils.createListWithOneItem;
import static com.thiagoti.products.utils.TestUtils.set;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.thiagoti.api.products.spec.PostProductRequestBody;
import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.api.products.spec.PutProductRequestBody;
import com.thiagoti.products.dto.GetProductsDto;
import com.thiagoti.products.dto.PostProductDto;
import com.thiagoti.products.dto.UpdateProductDto;
import com.thiagoti.products.routes.DeleteProductRouteStart;
import com.thiagoti.products.routes.GetProductsRouteStart;
import com.thiagoti.products.routes.PostProductRouteStart;
import com.thiagoti.products.routes.ReadProductRouteStart;
import com.thiagoti.products.routes.UpdateProductRouteStart;
import com.thiagoti.products.spec.api.ProductController;

@SpringBootTest
public class ProductControllerImplTest {

  private ProductController controller;
  
  public ProductControllerImplTest() {
    this.controller = new ProductControllerImpl();
    init();
  }

  private void init() {
    set(controller, "getProductsRouteStart", new GetProductsRouteStart() {
      
      @Override
      public List<ProductItem> execute(GetProductsDto param) {
        return createListWithOneItem(MOCK_STRING);
      }
    });
    set(controller, "readProductRouteStart", new ReadProductRouteStart() {
      
      @Override
      public ProductItem execute(String id) {
        return new ProductItem().withId(id).withName(MOCK_STRING).withBrand(MOCK_STRING);
      }
    });
    set(controller, "updateProductRouteStart", new UpdateProductRouteStart() {
      
      @Override
      public ProductItem execute(UpdateProductDto param) {
        ProductItem updatedItem = new ProductItem();
        updatedItem.setId(param.getId());
        if(param.getName().isPresent()) {
          updatedItem.setName(param.getName().get());
        }
        if(param.getBrand().isPresent()) {
          updatedItem.setBrand(param.getBrand().get());
        }
        return updatedItem;
      }
    });
    set(controller, "deleteProductRouteStart", new DeleteProductRouteStart() {

      @Override
      public boolean execute(String id) {
        return true;
      }
    });
    set(controller, "postProductRouteStart", new PostProductRouteStart() {

      @Override
      public ProductItem execute(PostProductDto param) {
        return new ProductItem().withId(UUID.randomUUID().toString()).withName(param.getName())
            .withBrand(param.getBrand());
      }
    });
  }

  @Test
  public void getProductItemsTest() {
    assertEquals(HttpStatus.OK, this.controller.getProductItems(MOCK_STRING, MOCK_STRING).getStatusCode());
  }

  @Test
  public void getProductItemByIdTest() {
    assertEquals(HttpStatus.OK, this.controller.getProductItemById(MOCK_STRING).getStatusCode());
  }

  @Test
  public void updatePutProductRequestBodyTest() {
    assertEquals(HttpStatus.ACCEPTED, this.controller.updatePutProductRequestBody(MOCK_STRING,
        new PutProductRequestBody().withName(MOCK_STRING).withBrand(MOCK_STRING)).getStatusCode());
  }

  @Test
  public void deleteProductByIdTest() {
    assertEquals(HttpStatus.ACCEPTED, this.controller.deleteProductById(MOCK_STRING).getStatusCode());
  }

  @Test
  public void createPostProductRequestBodyTest() {
    assertEquals(HttpStatus.CREATED,
        this.controller
            .createPostProductRequestBody(new PostProductRequestBody().withName(MOCK_STRING).withBrand(MOCK_STRING))
            .getStatusCode());
  }

}
