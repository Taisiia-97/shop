package com.taisiia.shop.mapper

import com.taisiia.shop.domain.dao.User
import com.taisiia.shop.domain.dto.UserDto
import spock.lang.Specification

class UserMapperImplSpec extends Specification {
    def userMapperImpl = new UserMapperImpl()

    def "should map user to userDto"() {
        given:
        def user = User.builder().id(5)
                .email("ania@gmail.com")
                .firstName("Anna")
                .lastName("Tkaczyk")
                .password("aaaaaaaaaaaaaaaaa")
                .build()
        when:
        def result = userMapperImpl.toDto(user)

        then:
        result.id == user.id
        result.email == user.email
        result.firstName == user.firstName
        result.lastName == user.lastName
        result.password == null

    }

    def "should not map user to userDto"() {
        given:
        def user = null

        when:
        def result = userMapperImpl.toDto(user)

        then:
        result == null
    }

    def "should  not map userDto to user"(){
        given:
        def userDto = null

        when:
        def result = userMapperImpl.toDao(userDto)

        then:
        result == null
    }

    def "should map userDto to user"(){
        given:
        def userDto = UserDto.builder()
        .id(5)
        .firstName("Anna")
        .lastName("Tkaczyk")
        .email("anna@gmail.com")
        .password("annaanna")
        .confirmPassword("annaanna")
        .build()

        when:
        def result = userMapperImpl.toDao(userDto)

        then:
        result.id == userDto.id
        result.firstName == userDto.firstName
        result.lastName == userDto.lastName
        result.email == userDto.email
        result.password == userDto.password
        result.roles == null

    }
}
