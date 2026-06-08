package co.istad.ecommercespring.controller;

import co.istad.ecommercespring.dto.CategoryResponse;
import co.istad.ecommercespring.dto.CreateCategoryRequest;
import co.istad.ecommercespring.dto.UpdateCategoryRequest;
import co.istad.ecommercespring.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew( @Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.createNew(createCategoryRequest);
    }

    @GetMapping
    public Page<CategoryResponse> allCategories(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "25") Integer pageSize) {
        return categoryService.getAllCategories(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{id}/subcategories")
    public List<CategoryResponse> getSubCategories(@PathVariable Integer id) {
        return categoryService.getSubCategories(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void hardDeleteById(@PathVariable Integer id) {
        categoryService.hardDeleteById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void softDeleteById(@PathVariable Integer id) {
        categoryService.softDeleteById(id);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategoryById(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCategoryRequest request) {
        return categoryService.updateCategoryById(id, request);
    }

}
