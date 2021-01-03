package com.thiagoti.products.repository.impl;

import static com.thiagoti.products.utils.TestUtils.ANY_STRING;
import static com.thiagoti.products.utils.TestUtils.MOCK_STRING;
import static com.thiagoti.products.utils.TestUtils.createListWithOneItem;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.thiagoti.api.products.spec.ProductItem;
import com.thiagoti.products.dto.GetProductsDto;
import com.thiagoti.products.dto.PostProductDto;
import com.thiagoti.products.exception.ProductNotFoundException;

@SpringBootTest
public class ProductsCollectionImplTest {

  private String targetId;

  public ProductsCollectionImplTest() {
    this.targetId = UUID.randomUUID().toString();
  }

  @Test
  public void save_ok() {
    assertNotNull(
        new ProductsCollectionImpl(createListWithOneItem(targetId)).save(new PostProductDto(ANY_STRING, ANY_STRING)));
  }

  @Test
  public void deleteById_ok() {
    assertTrue(new ProductsCollectionImpl(createListWithOneItem(targetId)).deleteById(targetId));
  }

  @Test
  public void deleteById_notFound() {
    assertFalse(new ProductsCollectionImpl(createListWithOneItem(targetId)).deleteById(ANY_STRING));
  }

  @Test
  public void update_ok() {
    ProductItem updatedItem = new ProductsCollectionImpl(createListWithOneItem(targetId))
        .update(new ProductItem().withId(targetId).withName(ANY_STRING).withBrand(ANY_STRING));
    assertNotNull(updatedItem);
  }

  @Test
  public void update_error() {
    assertThrows(ProductNotFoundException.class, () -> new ProductsCollectionImpl(createListWithOneItem(targetId))
        .update(new ProductItem().withId(ANY_STRING).withName(ANY_STRING).withBrand(ANY_STRING)));
  }

  @Test
  public void findById_found() {
    Optional<ProductItem> optional = new ProductsCollectionImpl(createListWithOneItem(targetId)).findById(targetId);
    assertTrue(optional.isPresent());
  }

  @Test
  public void findById_notFound() {
    Optional<ProductItem> optional = new ProductsCollectionImpl(createListWithOneItem(targetId)).findById(ANY_STRING);
    assertFalse(optional.isPresent());
  }

  @Test
  public void findAll_found() {
    List<ProductItem> found = new ProductsCollectionImpl(createListWithOneItem(
        targetId))
        .findAll(new GetProductsDto(Optional.of(MOCK_STRING), Optional.of(MOCK_STRING)));

    assertFalse(found.isEmpty());
  }

  @Test
  public void findAll_notFound() {
    List<ProductItem> found = new ProductsCollectionImpl(
        createListWithOneItem(targetId))
        .findAll(new GetProductsDto(Optional.of(ANY_STRING), Optional.of(ANY_STRING)));

    assertTrue(found.isEmpty());
  }

}
