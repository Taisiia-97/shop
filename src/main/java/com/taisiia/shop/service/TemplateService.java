package com.taisiia.shop.service;

import com.taisiia.shop.domain.dao.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateService {

    Template findByName(String name);

    Template create(Template template);

   Page<Template> getTemplates(Pageable pageable);

    void deleteTemplateById(Long id);

    Template update(Long id, Template template);
}
