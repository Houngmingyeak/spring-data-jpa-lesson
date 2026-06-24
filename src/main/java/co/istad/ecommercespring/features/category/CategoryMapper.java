package co.istad.ecommercespring.features.category;

import co.istad.ecommercespring.features.category.dto.CategoryResponse;
import co.istad.ecommercespring.features.category.dto.CreateCategoryRequest;
import co.istad.ecommercespring.features.category.dto.UpdateCategoryRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //    return type = target
//    parameter = source
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    CategoryResponse mapCategoryToCategoryResponse(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromRequest(UpdateCategoryRequest request, @MappingTarget Category category);
}
