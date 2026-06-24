package co.istad.ecommercespring.features.product;

import co.istad.ecommercespring.features.product.dto.CreateProductRequest;
import co.istad.ecommercespring.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {


//    /**
//     * Creates a new product.
//     * @param createProductRequest is requesting data for creating product
//     * @return {@link ProductResponse}
//     * @since 2026-06-23
//     * @author mingyeak
//     * */

    Page<ProductResponse> findAll(int pageNumber, int pageSize);

    ProductResponse createNew(CreateProductRequest createProductRequest);

}
