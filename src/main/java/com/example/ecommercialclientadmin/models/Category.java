package com.example.ecommercialclientadmin.models;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  protected UUID id;
  protected String name;
  protected UUID parentId;
}
