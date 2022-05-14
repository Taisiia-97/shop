package com.taisiia.shop.mapper

import com.taisiia.shop.domain.DiscountStatus
import com.taisiia.shop.domain.dao.Discount
import com.taisiia.shop.domain.dto.DiscountDto
import spock.lang.Specification

import java.time.LocalDateTime

class DiscountMapperImplSpec extends Specification {
    def discountMapperImpl = new DiscountMapperImpl()

    def "should not map discount do discountDto"() {
        given:
        def discount = null

        when:
        def result = discountMapperImpl.toDto(discount)

        then:
        result == null
    }

    def "should map discount to discountDto"() {
        given:
        def discountStatus = DiscountStatus.ACTIVE.name()
        def discount = Discount.builder()
                .id(5)
                .name("discountName")
                .code("discountCode")
                .percent(5)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(4))
                .status(DiscountStatus.ACTIVE).build()


        when:
        def result = discountMapperImpl.toDto(discount)

        then:
        result.id == discount.id
        result.name == discount.name
        result.code == discount.code
        result.percent == discount.percent
        result.startDate == discount.startDate
        result.endDate == discount.endDate
        result.status == "ACTIVE"
    }

    def "should not map discountDto to discount"() {
        given:
        def discountDto = null

        when:
        def result = discountMapperImpl.toDao(discountDto)

        then:
        result == null
    }

}
