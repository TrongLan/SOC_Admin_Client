package com.example.ecommercialclientadmin.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum API {
  PRODUCT_ROOT_ENDPOINT("http://localhost:8080/api/products"),
  CATEGORY_ROOT_ENDPOINT("http://localhost:8080/api/category");
  private final String endPoint;
}
