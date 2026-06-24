package co.istad.ecommercespring.features.product;

import co.istad.ecommercespring.features.product.dto.CreateProductRequest;
import co.istad.ecommercespring.features.product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapCreateProductRequestToProduct(CreateProductRequest createProductRequest);

    ProductResponse mapProductToProductResponse(Product product);

}
