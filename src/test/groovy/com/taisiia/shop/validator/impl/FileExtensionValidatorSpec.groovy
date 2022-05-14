package com.taisiia.shop.validator.impl

import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

class FileExtensionValidatorSpec extends Specification {
    def fileExtensionValidator = new FileExtensionValidator();

    def "should Test File Extension Validator"() {
        given:
        def multipartFile = Mock(MultipartFile);

        multipartFile.getOriginalFilename() >> fileName

        when:
        def result = fileExtensionValidator.isValid(multipartFile, null)

        then:
        result == expected
        where:
        fileName                  || expected
        "hotel-presidente-4s.jpg" || true
        "hotel-presidente-4s.png" || true
        "hotel-presidente-4s.txt" || false

    }

    def "should Test File Extension Validator 2"() {

        when:
        def result = fileExtensionValidator.isValid(null, null)

        then:
        result


    }
}
