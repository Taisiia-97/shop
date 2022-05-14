package com.taisiia.shop.controller;

import com.taisiia.shop.domain.dto.ProductDto;
import com.taisiia.shop.mapper.ProductMapper;
import com.taisiia.shop.service.ProductService;
import com.taisiia.shop.validator.FileExtensionValid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return productMapper.toDto(productService.findById(id));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @Validated
    public ProductDto saveProduct(@RequestPart @Valid ProductDto product, @RequestPart(required = false) @Valid @FileExtensionValid MultipartFile photo) throws IOException {
        return productMapper.toDto(productService.save(productMapper.toDao(product), photo,
                product.getProducerId(), product.getCategoryIds()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    @Validated
    public ProductDto updateProduct(@RequestPart @Valid ProductDto productDto, @RequestPart(required = false) @Valid @FileExtensionValid MultipartFile photo, @PathVariable Long id) {
        return productMapper.toDto(productService.update(productMapper.toDao(productDto), photo, productDto.getProducerId(),
                productDto.getCategoryIds(), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() && hasRole('ADMIN')")
    public void deleteProductById(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping()
    @PreAuthorize("permitAll()")
    public Page<ProductDto> getProductPage(@RequestParam int page, @RequestParam int size) {

        return productService.getPage(PageRequest.of(page, size)).map(productMapper::toDto);
    }

    @GetMapping("/name")
    @PreAuthorize("permitAll()")
    public List<ProductDto> getProductByName(@RequestParam String name) {

        return productMapper.toListDto(productService.findByName(name));
    }

    @GetMapping("/range")
    @PreAuthorize("permitAll()")
    public List<ProductDto> getProductByPriceRge(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productMapper.toListDto(productService.findByPriceRange(minPrice, maxPrice));
    }


}
