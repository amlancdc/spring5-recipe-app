package guru.spingframework.spring5recipeapp.repositories;

import guru.spingframework.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}