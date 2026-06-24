package co.istad.ecommercespring.features.category;

import co.istad.ecommercespring.features.category.dto.CategoryResponse;
import co.istad.ecommercespring.features.category.dto.CreateCategoryRequest;
import co.istad.ecommercespring.features.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);

    Page<CategoryResponse> getAllCategories(Integer pageNumber, Integer pageSize);

    CategoryResponse getCategoryById(Integer id);

    void hardDeleteById(Integer id);

    void softDeleteById(Integer id);

    List<CategoryResponse> getSubCategories(Integer parentCategoryId);

    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest);

}
