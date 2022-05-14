package com.taisiia.shop.validator.impl

import com.taisiia.shop.domain.dto.UserDto
import spock.lang.Specification

class PasswordValidatorSpec extends Specification {
    def passwordValidator = new PasswordValidator()

    def 'should Test password validator'() {
        given:
        def userDto = new UserDto(password: password, confirmPassword: confirmPassword)

        when:
        def result = passwordValidator.isValid(userDto, null)

        then:
        result == expected

        where:
        password  | confirmPassword || expected
        "taechka" | "taechka97"     || false
        "Kalka1"  | "Kalka1"        || true
    }
}
