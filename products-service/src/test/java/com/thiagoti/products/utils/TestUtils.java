package com.thiagoti.products.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thiagoti.api.products.spec.ProductItem;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

  public static final String MOCK_STRING = "mockString";

  public static final String ANY_STRING = "any string";

  public static List<ProductItem> createListWithOneItem(String id) {
    return new ArrayList<>(Arrays.asList(new ProductItem().withId(id).withName(MOCK_STRING).withBrand(MOCK_STRING)));
  }

  public static boolean set(Object object, String fieldName, Object fieldValue) {
    Class<?> clazz = object.getClass();
    while (clazz != null) {
      try {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, fieldValue);
        return true;
      } catch (NoSuchFieldException e) {
        clazz = clazz.getSuperclass();
      } catch (Exception e) {
        throw new IllegalStateException(e);
      }
    }
    return false;
  }

}
