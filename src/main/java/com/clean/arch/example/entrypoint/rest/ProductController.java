package com.clean.arch.example.entrypoint.rest;

import com.clean.arch.example.entrypoint.converters.ProductConverter;
import com.clean.arch.example.entrypoint.dto.ProductDto;
import com.clean.arch.example.usecase.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor

public class ProductController {

    private final CreateProduct createProduct;
    private final DeleteProduct deleteProduct;
    private final EditProduct editProduct;
    private final FindProductById findProductById;
    private final ListAllProducts listAllProducts;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public void createProduct(@RequestBody ProductDto product) {
        createProduct.execute(ProductConverter.toDomain(product));
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> listAllProducts() {
        return ProductConverter.toDtos(listAllProducts.execute());
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto findProductById(@PathVariable(value = "productId") int productId) {
        return ProductConverter.toDto(findProductById.execute(productId));
    }

    @RequestMapping(value = "/products", method = RequestMethod.PUT)
    public void editProduct(@RequestBody ProductDto product) {
        editProduct.execute(ProductConverter.toDomain(product));
    }

    @RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(value = "productId") Integer productId) {
        deleteProduct.execute(productId);
    }
}
