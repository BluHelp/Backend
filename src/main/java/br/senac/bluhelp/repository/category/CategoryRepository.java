package br.senac.bluhelp.repository.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.senac.bluhelp.model.category.Category;
import br.senac.bluhelp.projection.category.CategoryProjection;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	boolean existsById(Long id);
	
	Optional <CategoryProjection> findCategoryById(Long id);
	
	@Query(value="SELECT ca.id AS id, ca.name AS name FROM Category AS ca")
	List<CategoryProjection> findCategories();
}
