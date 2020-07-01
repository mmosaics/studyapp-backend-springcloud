package org.example.dao;

import org.example.pojo.TechCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechCategoryDAO extends JpaRepository<TechCategory, String> {

}
