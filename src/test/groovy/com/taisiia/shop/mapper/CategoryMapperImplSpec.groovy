package com.taisiia.shop.mapper

import com.taisiia.shop.domain.dao.Category
import com.taisiia.shop.domain.dto.CategoryDto
import spock.lang.Specification

class CategoryMapperImplSpec extends Specification {
    def categoryMapperImpl = new CategoryMapperImpl()

    def "should not map category to categoryDto"() {
        given:
        def category = null

        when:
        def result = categoryMapperImpl.toDto(category)

        then:
        result == null
    }

    def "should map category to categoryDto"() {
        given:
        def category = Category.builder()
                .id(5)
                .name("categoryName")
                .build()

        when:
        def result = categoryMapperImpl.toDto(category)


        then:
        result.id == category.id
        result.name == category.name
    }

    def "should map categoryDto to category"() {
        given:
        def categoryDto = CategoryDto.builder()
                .id(5)
                .name("categoryName")
                .build()

        when:
        def result = categoryMapperImpl.toDao(categoryDto)

        then:
        result.id == categoryDto.id
        result.name == categoryDto.name

    }

    def "should not map categoryDto to category"() {
        given:
        def categoryDto = null

        when:
        def result = categoryMapperImpl.toDao(categoryDto)

        then:
        result == null
    }

}
