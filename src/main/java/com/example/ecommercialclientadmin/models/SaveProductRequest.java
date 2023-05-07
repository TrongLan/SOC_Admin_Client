package com.example.ecommercialclientadmin.models;

import java.time.LocalDateTime;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveProductRequest {
  private String name;
  private String description;
  private double price = 0;
  private String image;
  private boolean sale = false;
  private double salePrice = 0;
  private int views = 0;
  private boolean featured = false;
  private String featureImage;
  private LocalDateTime createdAt;
  private String color;
  private String size;
  private String categoryId;

  public SaveProductRequest(ProductBaseInfo info){
    this.name = info.getName();
    this.description = info.getDescription();
    this.price = info.getPrice();
  }
}
