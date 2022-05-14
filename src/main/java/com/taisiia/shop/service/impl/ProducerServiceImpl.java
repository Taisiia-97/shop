package com.taisiia.shop.service.impl;

import com.taisiia.shop.domain.dao.Producer;
import com.taisiia.shop.repository.ProducerRepository;
import com.taisiia.shop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;


    @Override
    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    @Transactional
    public Producer update(Producer producer, Long id) {
        Producer producerToUpdate = findById(id);
        producerToUpdate.setName(producer.getName());
        producerToUpdate.setCountry(producer.getCountry());
        producerToUpdate.setCountryCode(producer.getCountryCode());
        return producerToUpdate;
    }

    @Override
    public Producer findById(Long id) {
        return producerRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        producerRepository.deleteById(id);
    }

    @Override
    public Page<Producer> getPage(Pageable pageable) {
        return producerRepository.findAll(pageable);
    }

    @Override
    public Producer getByName(String name) {
        return producerRepository.findOneByName(name);
    }

    @Override
    public List<Producer> getByCountry(String country) {
        return producerRepository.findByCountry(country);
    }

    @Override
    public List<Producer> getByCountryCode(String countryCode) {
        return producerRepository.findByCountryCode(countryCode);
    }

}
