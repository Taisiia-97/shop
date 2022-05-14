package com.taisiia.shop.service.impl

import com.taisiia.shop.domain.dao.Discount
import com.taisiia.shop.repository.DiscountRepository
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

class DiscountServiceImplSpec extends Specification {

    def discountRepository = Mock(DiscountRepository)
    def discountServiceImpl = new DiscountServiceImpl(discountRepository)
//
//    def "should create discount"(){
//        given:
//
//
//        when:
//
//
//        then:
//
//
//    }

    def "should get discount by id"() {
        given:
        def id = 5

        when:
        discountServiceImpl.getById(id)

        then:
        1 * discountRepository.getById(id)
        0 * _
    }

    def "should get discount page"() {
        given:
        def pageRequest = PageRequest.of(2, 2)

        when:
        discountServiceImpl.getDiscountPage(pageRequest)

        then:
        1 * discountRepository.findAll(pageRequest)
        0 * _
    }

    def "should delete discount"() {
        given:
        def id = 5

        when:
        discountServiceImpl.deleteById(id)

        then:
        1 * discountRepository.deleteById(id)
        0 * _
    }

    def "should find discount by code"() {
        given:
        def discountCode = "code"
        def optionalDiscount = Optional.of(new Discount())

        when:
        discountServiceImpl.findByDiscountCode(discountCode)

        then:
        1 * discountRepository.findByCode(discountCode) >> optionalDiscount
        0 * _

    }


}
