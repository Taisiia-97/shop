package com.taisiia.shop.service.impl;

import com.taisiia.shop.domain.dao.Template;
import com.taisiia.shop.repository.TemplateRepository;
import com.taisiia.shop.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;

    @Override
    public Template findByName(String name) {
        return templateRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Template create(Template template) {
        return templateRepository.save(template);
    }

    @Override
    public Page<Template> getTemplates(Pageable pageable) {
        return templateRepository.findAll(pageable);
    }

    @Override
    public void deleteTemplateById(Long id) {
        templateRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Template update(Long id, Template template) {
        var templateToUpdate = templateRepository.getById(id);
        templateToUpdate.setName(template.getName());
        templateToUpdate.setBody(template.getBody());
        templateToUpdate.setSubject(template.getSubject());
        return templateToUpdate;
    }
}
