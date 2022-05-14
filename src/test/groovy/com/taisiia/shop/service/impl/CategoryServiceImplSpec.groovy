package com.taisiia.shop.service.impl

import com.taisiia.shop.domain.dao.Category
import com.taisiia.shop.repository.CategoryRepository
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

class CategoryServiceImplSpec extends Specification {
    def categoryRepository = Mock(CategoryRepository)
    def categoryServiceImpl = new CategoryServiceImpl(categoryRepository)

    def "should save category"() {
        given:
        def category = Mock(Category)

        when:
        categoryServiceImpl.save(category)

        then:
        1 * categoryRepository.save(category)
        0 * _
    }

    def "should update category"() {
        given:
        def id = 5
        def categoryById = Mock(Category)
        def category = new Category(name: "categoryName")

        when:
        categoryServiceImpl.update(category, id)

        then:
        1 * categoryRepository.getById(id) >> categoryById
        1 * categoryById.setName(category.getName())
        0 * _
    }

    def "should find category by id"() {
        given:
        def id = 5

        when:
        categoryServiceImpl.findById(5)

        then:
        1 * categoryRepository.getById(5)
        0 * _
    }

    def "should delete category by id"() {
        given:
        def id = 5

        when:
        categoryServiceImpl.delete(id)

        then:
        1 * categoryRepository.deleteById(id)
        0 * _
    }

    def "should get category page"() {
        given:
        def pageRequest = PageRequest.of(2, 2)


        when:
        categoryServiceImpl.getPage(pageRequest)

        then:
        1 * categoryRepository.findAll(pageRequest)
        0 * _
    }


    def "should find category by name"() {
        given:
        def name = "categoryName"

        when:
        categoryServiceImpl.getCategoryByName(name)

        then:
        1 * categoryRepository.findOneByName(name)
        0 * _
    }

    def "should get categories"() {
        given:
        def categoriesIds = Set.of(1, 2, 3, 4)


        when:
        categoryServiceImpl.getCategories(categoriesIds)

        then:
        1 * categoryRepository.findByIdIn(categoriesIds)
        0 * _
    }
}
