package br.senac.bluhelp.service.category;

import java.util.List;

import br.senac.bluhelp.dto.category.CategoryDTO;
import br.senac.bluhelp.projection.category.CategoryProjection;

public interface CategoryService {
	
	CategoryDTO save(CategoryDTO categoryDTO);
	
	void update(Long id, CategoryDTO categoryDTO);
	
	void delete(Long id);
	
	CategoryProjection findById(Long id);
	
	List<CategoryProjection> findAll();

}
