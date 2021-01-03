package com.thiagoti.products.service.impl;

import static com.thiagoti.products.utils.TestUtils.ANY_STRING;
import static com.thiagoti.products.utils.TestUtils.MOCK_STRING;
import static com.thiagoti.products.utils.TestUtils.createListWithOneItem;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.thiagoti.products.dto.GetProductsDto;
import com.thiagoti.products.dto.PostProductDto;
import com.thiagoti.products.dto.UpdateProductDto;
import com.thiagoti.products.exception.ProductNotFoundException;
import com.thiagoti.products.repository.impl.ProductsCollectionImpl;

@SpringBootTest
public class ProductServiceImplTest {

  @Test
  public void get_list() {
    assertFalse(new ProductServiceImpl(new ProductsCollectionImpl(createListWithOneItem(MOCK_STRING)))
        .get(new GetProductsDto(Optional.of(MOCK_STRING), Optional.of(MOCK_STRING))).isEmpty());
  }
  
  @Test
  public void get_byId() {
    assertNotNull(
        new ProductServiceImpl(new ProductsCollectionImpl(createListWithOneItem(MOCK_STRING))).get(MOCK_STRING));
  }

  @Test
  public void update_ok() {
    assertNotNull(new ProductServiceImpl(new ProductsCollectionImpl(createListWithOneItem(MOCK_STRING)))
        .update(new UpdateProductDto(MOCK_STRING, Optional.of(MOCK_STRING), Optional.of(MOCK_STRING))));
  }

  @Test
  public void update_error() {
    assertThrows(ProductNotFoundException.class,
        () -> new ProductServiceImpl(new ProductsCollectionImpl(createListWithOneItem(MOCK_STRING)))
            .update(new UpdateProductDto(ANY_STRING, Optional.of(MOCK_STRING), Optional.of(MOCK_STRING))));
  }

  @Test
  public void delete_found() {
    assertTrue(
        new ProductServiceImpl(new ProductsCollectionImpl(createListWithOneItem(MOCK_STRING))).delete(MOCK_STRING));
  }

  @Test
  public void delete_notFound() {
    assertFalse(
        new ProductServiceImpl(new ProductsCollectionImpl(createListWithOneItem(MOCK_STRING))).delete(ANY_STRING));
  }

  @Test
  public void save_ok() {
    assertNotNull(new ProductServiceImpl(new ProductsCollectionImpl(createListWithOneItem(MOCK_STRING)))
        .save(new PostProductDto(ANY_STRING, MOCK_STRING)));
  }

  @Test
  public void save_error() {
    assertThrows(ResponseStatusException.class,
        () -> new ProductServiceImpl(new ProductsCollectionImpl(createListWithOneItem(MOCK_STRING)))
            .save(new PostProductDto(MOCK_STRING, MOCK_STRING)));
  }

}
