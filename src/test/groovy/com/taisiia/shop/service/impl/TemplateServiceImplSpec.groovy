package com.taisiia.shop.service.impl

import com.taisiia.shop.domain.dao.Template
import com.taisiia.shop.repository.TemplateRepository
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

class TemplateServiceImplSpec extends Specification {
    def templateRepository = Mock(TemplateRepository)
    def templateServiceImpl = new TemplateServiceImpl(templateRepository)


    def "should create template"() {
        given:
        def template = Mock(Template)

        when:
        templateServiceImpl.create(template)

        then:
        1 * templateRepository.save(template)
        0 * _
    }

    def "should find template by name"() {
        given:
        def templateName = "Name"
        def optionalTemplate = Optional.of(new Template())

        when:
        templateServiceImpl.findByName(templateName)

        then:
        1 * templateRepository.findByName(templateName) >> optionalTemplate
        0 * _
    }

    def "should not find template by name"() {
        given:
        def templateName = "Name"
        def optionalTemplate = Optional.empty()
        templateRepository.findByName(templateName) >> optionalTemplate

        when:
        templateServiceImpl.findByName(templateName)

        then:
        thrown EntityNotFoundException
    }

    def "should get template page"() {
        given:
        def pageRequest = PageRequest.of(2, 2)

        when:
        templateServiceImpl.getTemplates(pageRequest)

        then:
        1 * templateRepository.findAll(pageRequest)
        0 * _
    }

    def "should delete template by id"() {
        given:
        def id = 5

        when:
        templateServiceImpl.deleteTemplateById(id)

        then:
        1 * templateRepository.deleteById(id)
        0 * _
    }


    def "should update template"() {
        given:
        def id = 5
        def template = new Template(name: "name", subject: "subject", body: "body")
        def templateById = Mock(Template)

        when:
        templateServiceImpl.update(id, template)

        then:
        1 * templateRepository.getById(id) >> templateById
        1 * templateById.setName(template.getName())
        1 * templateById.setSubject(template.getSubject())
        1 * templateById.setBody(template.getBody())
        0 * _
    }
}
