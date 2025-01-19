package com.pintoss.gitftmall.domain.product.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSearchRequest {

    private String keyword;

    private String category;

}
