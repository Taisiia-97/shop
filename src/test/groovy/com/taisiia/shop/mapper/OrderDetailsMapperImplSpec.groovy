package com.taisiia.shop.mapper

import com.taisiia.shop.domain.OrderStatus
import com.taisiia.shop.domain.dao.OrderDetails
import com.taisiia.shop.domain.dao.User
import spock.lang.Specification

import java.time.LocalDate

class OrderDetailsMapperImplSpec extends Specification {
    def orderDetailsMapperImpl = new OrderDetailsMapperImpl()

    def "should not map OrderDetails to OrderDetailsDto"() {
        given:
        def orderDetails = null

        when:
        def result = orderDetailsMapperImpl.toDto(orderDetails)

        then:
        result == null
    }


    def "should map OrderDetailsDto to OrderDetails"() {
        given:
        def user = Mock(User)
        def orderDetails = OrderDetails.builder()
                .id(5)
                .orderNumber(UUID.randomUUID().toString())
                .orderDate(LocalDate.now())
                .orderStatus(OrderStatus.CREATED)
                .discountPercent(5)
                .user(user)
                .build()


        when:
        def result = orderDetailsMapperImpl.toDto(orderDetails)

        then:
        result.id == orderDetails.id
        result.orderNumber == orderDetails.orderNumber
        result.orderDate == orderDetails.orderDate
        result.orderStatus == orderDetails.orderStatus
        result.discountPercent == orderDetails.discountPercent
    }


}
