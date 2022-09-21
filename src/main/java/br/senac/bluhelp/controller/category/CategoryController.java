package br.senac.bluhelp.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.bluhelp.dto.category.CategoryDTO;
import br.senac.bluhelp.projection.category.CategoryProjection;
import br.senac.bluhelp.projection.category.CategoryWithProjectsProjection;
import br.senac.bluhelp.service.category.CategoryService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable(value = "id") Long id) {
		categoryService.update(id, categoryDTO);
		return ResponseEntity.status(HttpStatus.OK).body("Categoria atualizada");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") Long id) {
		categoryService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada");
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryWithProjectsProjection> getCategoryWithProjects(@PathVariable(value = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findByIdWithProjects(id));
	}

	@GetMapping()
	public ResponseEntity<List<CategoryProjection>> getAllCategories() {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
	}

}
