package com.example.ecommercialclientadmin.controlers;

import com.example.ecommercialclientadmin.config.API;
import com.example.ecommercialclientadmin.models.Category;
import com.example.ecommercialclientadmin.models.Product;
import com.example.ecommercialclientadmin.models.ProductBaseInfo;
import com.example.ecommercialclientadmin.models.SaveProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/adm/product")
public class ProductController {
  private final RestTemplate restTemplate;

  @GetMapping(value = "/list")
  public String listProduct(Model model) {
    ResponseEntity<Product[]> response =
        restTemplate.getForEntity(API.PRODUCT_ROOT_ENDPOINT.getEndPoint(), Product[].class);
    model.addAttribute("request_status", response.getStatusCode());
    model.addAttribute("product_list", response.getBody());
    return "product_list";
  }

  @GetMapping(value = "/add")
  public String createForm(Model model) {
    ResponseEntity<Category[]> responseForCategoryList =
        restTemplate.getForEntity(API.CATEGORY_ROOT_ENDPOINT.getEndPoint(), Category[].class);
    model.addAttribute("categories", responseForCategoryList.getBody());
    model.addAttribute("request", new SaveProductRequest());
    model.addAttribute("mode", true);
    return "update_product";
  }

  @PostMapping(value = "/add")
  public String sendCreateRequest(Model model, SaveProductRequest request) {
    ResponseEntity<SaveProductRequest> response =
        restTemplate.postForEntity(
            API.PRODUCT_ROOT_ENDPOINT.getEndPoint(), request, SaveProductRequest.class);
    model.addAttribute("response_status", response.getStatusCode());
    return "redirect:/adm/product/list";
  }

  @GetMapping(value = "/delete/{uuid}")
  public String deleteProductRequest(@PathVariable String uuid) {
    restTemplate.delete(API.PRODUCT_ROOT_ENDPOINT.getEndPoint() + "/" + uuid);
    return "redirect:/adm/product/list";
  }

  @GetMapping(value = "/update/{uuid}")
  public String updateRequest(@PathVariable String uuid, Model model) {
    ResponseEntity<ProductBaseInfo> getProductResponse =
        restTemplate.getForEntity(
            API.PRODUCT_ROOT_ENDPOINT.getEndPoint() + "/base-info/" + uuid, ProductBaseInfo.class);
    model.addAttribute("request", getProductResponse.getBody());
    model.addAttribute("mode", false);
    model.addAttribute("uuid", uuid);
    return "update_product";
  }

  @PostMapping(value = "/update/{uuid}")
  public String sendUpdateRequest(@PathVariable String uuid, ProductBaseInfo request) {
    restTemplate.put(
        API.PRODUCT_ROOT_ENDPOINT.getEndPoint() + "/" + uuid, new SaveProductRequest(request));
    return "redirect:/adm/product/list";
  }
}
