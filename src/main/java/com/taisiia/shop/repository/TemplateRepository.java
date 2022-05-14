package com.taisiia.shop.repository;

import com.taisiia.shop.domain.dao.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    Optional<Template> findByName(String name);


}
