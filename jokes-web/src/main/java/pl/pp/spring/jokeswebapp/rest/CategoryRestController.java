package pl.pp.spring.jokeswebapp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pp.spring.jokeswebapp.exceptions.NotFoundException;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.services.CategoryService;

import java.net.URI;
import java.util.List;

@RestController
public class CategoryRestController {
    private Logger log = LoggerFactory.getLogger(CategoryRestController.class);

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    public ResponseEntity<List<Category>> getAll() {
        log.info("getAll");

        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/api/categories/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        log.info("getById");
        try {
            Category categoryServiceById = categoryService.findById(id);
            return ResponseEntity.ok(categoryServiceById);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/categories")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        log.info("add");

        Category savedCategory = categoryService.save(category);

        return ResponseEntity.created(URI.create("/api/category" + savedCategory.getId())).body(savedCategory);
    }

    @DeleteMapping("/api/categories/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        log.info("delete");
        try {
            categoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/categories/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id) {
        log.info("update");

        category.setId(id);
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.ok(savedCategory);
    }
}
