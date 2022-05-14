package com.taisiia.shop.service.impl

import com.taisiia.shop.domain.dao.Basket
import com.taisiia.shop.domain.dao.Product
import com.taisiia.shop.domain.dao.User
import com.taisiia.shop.exceptions.ExceededQuantityException
import com.taisiia.shop.repository.BasketRepository
import com.taisiia.shop.service.ProductService
import com.taisiia.shop.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Specification

class BasketServiceImplSpec extends Specification {
    def basketRepository = Mock(BasketRepository)
    def userService = Mock(UserService)
    def productService = Mock(ProductService)
    def basketServiceImpl = new BasketServiceImpl(basketRepository, userService, productService)

    def "should not add product to basket when quantity is not enough and basket entry exists"() {
        given:
        def productId = 5
        def quantity = 5
        def product = Mock(Product)
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def userName = "ania@gmail.com"
        def basket = Mock(Basket)

        when:
        basketServiceImpl.add(productId, quantity)

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> userName
        1 * basketRepository.findByUserEmailAndProductId(userName, productId) >> Optional.of(basket)
        1 * basket.getProduct() >> product
        1 * product.getQuantity() >> 3
        0 * _
        def exception = thrown ExceededQuantityException
        exception.message == "Not enough quantity for product by id 5"

    }

    def "should add product to basket when quantity is not enough and basket entry exists"() {
        given:
        def productId = 5
        def quantity = 5
        def product = Mock(Product)
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def userName = "ania@gmail.com"
        def basket = Mock(Basket)

        when:
        basketServiceImpl.add(productId, quantity)

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> userName
        1 * basketRepository.findByUserEmailAndProductId(userName, productId) >> Optional.of(basket)
        1 * basket.getProduct() >> product
        1 * product.getQuantity() >> 7
        1 * basket.setQuantity(quantity)
        0 * _
    }

    def "should not add product to basket when quantity is not enough and basket entry doesn't exist"() {
        given:
        def productId = 5
        def quantity = 5
        def product = Mock(Product)
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def userName = "ania@gmail.com"


        when:
        basketServiceImpl.add(productId, quantity)

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> userName
        1 * basketRepository.findByUserEmailAndProductId(userName, productId) >> Optional.empty()
        1 * productService.findById(productId) >> product
        1 * product.getQuantity() >> 3
        0 * _
        def exception = thrown ExceededQuantityException
        exception.message == "Not enough quantity for product by id 5"
    }

    def "should  add product to basket when quantity is not enough and basket entry doesn't exist"() {
        given:
        def productId = 5
        def quantity = 5
        def product = Mock(Product)
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def userName = "ania@gmail.com"
        def user = Mock(User)
        def basket = Basket.builder()
                .product(product)
                .quantity(quantity)
                .user(user)
                .build()


        when:
        basketServiceImpl.add(productId, quantity)

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> userName
        1 * basketRepository.findByUserEmailAndProductId(userName, productId) >> Optional.empty()
        1 * productService.findById(productId) >> product
        1 * product.getQuantity() >> 7
        1 * userService.getCurrentUser() >> user
        1 * basketRepository.save(basket)
        0 * _

    }

    def "should clear basket by user name"() {
        given:
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def userName = "ania@gmail.com"

        when:
        basketServiceImpl.deleteAll()

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> userName
        1 * basketRepository.deleteByUserEmail(userName)
        0 * _
    }

    def "should delete product by productId"() {
        given:
        def securityContext = Mock(SecurityContext)
        SecurityContextHolder.setContext(securityContext)
        def authentication = Mock(Authentication)
        def userName = "ania@gmail.com"
        def productId = 5

        when:
        basketServiceImpl.deleteProductById(productId)

        then:
        1 * securityContext.getAuthentication() >> authentication
        1 * authentication.getName() >> userName
        1 * basketRepository.deleteByUserEmailAndProductId(userName, productId)
        0 * _
    }

    def "should get baskets by user"() {
        given:
        def user = Mock(User)

        when:
        basketServiceImpl.getBasketsByUser(user)

        then:
        1 * basketRepository.findAllByUser(user)
        0 * _
    }

    def "should get products basket"() {
        given:
        def user = Mock(User)
        def baskets = [new Basket(id: 1, product: new Product(id: 1,quantity: 10),quantity: 2), new Basket(id: 2, product: new Product(id: 2,quantity: 5),quantity: 1)]


        when:
        def result = basketServiceImpl.getProductsBasket()

        then:
        1 * userService.getCurrentUser() >> user
        1 * basketRepository.findAllByUser(user) >> baskets
        0*_

        result[0].quantity == baskets[0].quantity
        result[1].quantity == baskets[1].quantity



    }

}
