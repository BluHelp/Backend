package br.senac.bluhelp.mapper.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.bluhelp.dto.category.CategoryDTO;
import br.senac.bluhelp.model.category.Category;

@Service
public class CategoryMapper {

	public CategoryDTO toDTO(Category category) {
		return new CategoryDTO(category.getId(), category.getName());
	}

	public List<CategoryDTO> toDTO(List<Category> categories) {

		final List<CategoryDTO> categoriesDTO = new ArrayList<>();

		for (Category category : categories)
			categoriesDTO.add(toDTO(category));

		return categoriesDTO;
	}

	public Category toEntity(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.id(), categoryDTO.name());
	}

}
