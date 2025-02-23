package com.nt.categoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.UserEntity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

	public Category findByName(String topLevelCategory);

	@Query("Select c from Category c where c.name = :name and c.parentCategory.name = :parentCategoryName")
	public Category findByNameAndParent(@Param("name")String name,@Param("parentCategoryName")String parentCategoryName);

}
