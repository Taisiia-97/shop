package com.taisiia.shop.service.impl

import com.taisiia.shop.domain.OrderStatus
import com.taisiia.shop.domain.dao.*
import com.taisiia.shop.helper.AppHelper
import com.taisiia.shop.repository.OrderDetailsRepository
import com.taisiia.shop.repository.OrderRepository
import com.taisiia.shop.service.BasketService
import com.taisiia.shop.service.DiscountService
import com.taisiia.shop.service.UserService
import spock.lang.Specification

import java.time.LocalDate

class OrderServiceImplSpec extends Specification {
    def orderRepository = Mock(OrderRepository)
    def orderDetailsRepository = Mock(OrderDetailsRepository)
    def basketService = Mock(BasketService)
    def userService = Mock(UserService)
    def discountService = Mock(DiscountService)
    def appHelper = Mock(AppHelper)
    def orderServiceImpl = new OrderServiceImpl(orderRepository, orderDetailsRepository, basketService, userService, discountService, appHelper)

    def "should save order without discount code"() {

        given:
        def discountCode = null
        def user = Mock(User)
        def orderDetails = OrderDetails.builder()
                .user(user)
                .orderDate(LocalDate.now())
                .orderStatus(OrderStatus.CREATED)
                .orderNumber("UUID")
                .discountPercent(0.0)
                .build();
        def product = new Product(id: 1, quantity: 10)
        def baskets = List.of(new Basket(id: 1, product: product, quantity: 2))
        def orders = [new Order(product: product, quantity: 2, orderDetails: orderDetails)]

        when:
         orderServiceImpl.createOrder(discountCode)


        then:
        1 * userService.getCurrentUser() >> user
        1 * discountService.findByDiscountCode(discountCode) >> Optional.empty()
        1 * appHelper.generateRandomString() >> "UUID"
        1 * orderDetailsRepository.save(orderDetails)
        1 * basketService.getBasketsByUser(user) >> baskets
        1 * orderRepository.saveAll(orders)
        1 * basketService.deleteAll()
        0 * _

    }

}
