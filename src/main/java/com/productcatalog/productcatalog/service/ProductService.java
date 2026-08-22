package com.productcatalog.productcatalog.service;

import com.productcatalog.productcatalog.entity.Product;
import com.productcatalog.productcatalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findBySoftDeletedFalse();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long productId, Product updatedProduct) {

        Product existingProduct = getProductById(productId);

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productRepository.save(existingProduct);
    }

    public void softDeleteProduct(Long productId) {

        Product product = getProductById(productId);

        product.setSoftDeleted(true);

        productRepository.save(product);
    }

    public void hardDeleteProduct(Long productId) {

        Product product = getProductById(productId);

        productRepository.delete(product);
    }
}