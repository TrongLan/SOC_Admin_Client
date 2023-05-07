package com.example.ecommercialclientadmin.models;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class Product {
  protected String id;
  protected String name;
  protected String description;
  protected double price = 0;
  protected String image;
  protected boolean sale = false;
  protected double salePrice = 0;
  protected int views = 0;
  protected boolean featured = false;
  protected String featureImage;
  protected UUID categoryId;
  private LocalDateTime createdAt;
  private String color;
  private String size;
}
