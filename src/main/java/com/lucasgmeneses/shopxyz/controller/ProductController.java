package com.lucasgmeneses.shopxyz.controller;

import com.lucasgmeneses.shopxyz.data.dto.product.RProductRequestDto;
import com.lucasgmeneses.shopxyz.data.dto.product.RProductResponseDto;
import com.lucasgmeneses.shopxyz.data.model.ProductModel;
import com.lucasgmeneses.shopxyz.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController implements ICrudController<RProductRequestDto, RProductRequestDto> {
    @Autowired
    private IProductRepository IProductRepository;


    @Override
    @PostMapping
    public ResponseEntity create(RProductRequestDto request) {
        try {
            var productModel = new ProductModel();
            productModel.setName(request.name());
            productModel.setDescription(request.description());
            productModel.setPrice(request.price());
            IProductRepository.save(productModel);
            return ResponseEntity.ok(new RProductResponseDto(
                    productModel.getId(),
                    productModel.getName(),
                    productModel.getDescription(),
                    productModel.getPrice()));
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity update(UUID id, RProductRequestDto request) {
        var product = IProductRepository.findById(id).orElse(null);
        if(product == null) return ResponseEntity.notFound().build();

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());

        IProductRepository.save(product);
        return ResponseEntity.ok(new RProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(UUID id) {
        var product = IProductRepository.findById(id).orElse(null);
        if(product == null) return ResponseEntity.notFound().build();

        IProductRepository.delete(product);

        return ResponseEntity.ok(new RProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity get(UUID id) {
        var product = IProductRepository.findById(id).orElse(null);
        if(product == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new RProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()));
    }

    @Override
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(IProductRepository.findAll().stream()
                .map(product -> new RProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()))
                .toList());
    }
}
