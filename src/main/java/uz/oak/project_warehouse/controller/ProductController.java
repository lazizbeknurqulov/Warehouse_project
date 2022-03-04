package uz.oak.project_warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uz.oak.project_warehouse.payload.req.ProductDto;
import uz.oak.project_warehouse.service.ProductService;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/get")
    public ResponseEntity<?> getProduct(@RequestBody List<ProductDto> productDtos) {
        return ResponseEntity.ok(productService.getAllInfo(productDtos));
    }



    /**
     * Content-Type: application/json
     *
     * [
     *   {
     *     "name": "Ko'ylak",
     *     "qnt": 30
     *   },
     *   {
     *     "name": "Shim",
     *     "qnt": 20
     *   }
     *
     * ]
     */

}
