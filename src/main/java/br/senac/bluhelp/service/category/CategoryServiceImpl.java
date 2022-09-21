package br.senac.bluhelp.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.category.CategoryDTO;
import br.senac.bluhelp.exception.category.CategoryNotFoundException;
import br.senac.bluhelp.mapper.category.CategoryMapper;
import br.senac.bluhelp.model.category.Category;
import br.senac.bluhelp.projection.category.CategoryProjection;
import br.senac.bluhelp.projection.category.CategoryWithProjectsProjection;
import br.senac.bluhelp.repository.category.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
		this.categoryRepository = categoryRepository;
		this.categoryMapper = categoryMapper;
	}

	public CategoryDTO save(CategoryDTO categoryDTO) {

		Category category = categoryMapper.toEntity(categoryDTO);
		Category categorySaved = categoryRepository.save(category);

		return categoryMapper.toDTO(categorySaved);
	}

	public void update(Long id, CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category " + id + " was not found"));
		
		category.setName(categoryDTO.name());
	}

	public void delete(Long id) {
		if(!categoryRepository.existsById(id))
			throw new CategoryNotFoundException("Category " + id + " was not found");
		
		categoryRepository.deleteById(id);
	}

	public CategoryProjection findById(Long id) {
		CategoryProjection category = categoryRepository.findCategoryById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category " + id + " was not found"));
		
		return category;
	}

	public List<CategoryProjection> findAll() {
		return categoryRepository.findCategories();
	}

	public CategoryWithProjectsProjection findByIdWithProjects(Long id) {
		CategoryWithProjectsProjection category = categoryRepository.findCategoryWithProjectsById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category " + id + " was not found"));
		
		return category;
	}

}
