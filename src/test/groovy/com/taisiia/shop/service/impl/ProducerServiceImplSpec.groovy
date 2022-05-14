package com.taisiia.shop.service.impl

import com.taisiia.shop.domain.dao.Producer
import com.taisiia.shop.repository.ProducerRepository
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

class ProducerServiceImplSpec extends Specification {
    def producerRepository = Mock(ProducerRepository)
    def producerServiceImpl = new ProducerServiceImpl(producerRepository)

    def "should save producer"() {
        given:
        def producer = Mock(Producer)

        when:
        producerServiceImpl.save(producer)

        then:
        1 * producerRepository.save(producer)
        0 * _
    }

    def "should update producer"() {
        given:
        def id = 5
        def producerById = Mock(Producer)
        def producer = new Producer(name: "Name", country: "Country", countryCode: "Code")

        when:
        producerServiceImpl.update(producer, id)

        then:
        1 * producerRepository.getById(id) >> producerById
        1 * producerById.setName(producer.getName())
        1 * producerById.setCountry(producer.getCountry())
        1 * producerById.setCountryCode(producer.getCountryCode())
        0 * _
    }

    def "should find producer By Id"() {
        given:
        def id = 5

        when:
        producerServiceImpl.findById(id)

        then:
        1 * producerRepository.getById(id)
        0 * _
    }

    def "should delete producer By Id"() {
        given:
        def id = 5

        when:
        producerServiceImpl.delete(id)

        then:
        1 * producerRepository.deleteById(id)
        0 * _

    }

    def "should get producer page"() {
        given:
        def pageRequest = PageRequest.of(2, 2)

        when:
        producerServiceImpl.getPage(pageRequest)

        then:
        1 * producerRepository.findAll(pageRequest)
        0 * _
    }

    def "should find producer by name"() {
        given:
        def name = "Name"

        when:
        producerServiceImpl.getByName(name)

        then:
        1 * producerRepository.findOneByName(name)
        0 * _
    }

    def "should get producer by country"(){
        given:
        def country = "countryName"

        when:
        producerServiceImpl.getByCountry(country)

        then:
        1*producerRepository.findByCountry(country)
        0*_
    }

    def "should get producer by country code"(){
        given:
        def countryCode = "countryCode"

        when:
        producerServiceImpl.getByCountryCode(countryCode)

        then:
        1*producerRepository.findByCountryCode(countryCode)
        0*_
    }
}
