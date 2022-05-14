package com.taisiia.shop.service;

import com.taisiia.shop.domain.dao.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProducerService {

    Producer save(Producer producer);

    Producer update(Producer producer, Long id);

    Producer findById(Long id);

    void delete(Long id);

    Page<Producer> getPage(Pageable pageable);

    Producer getByName(String name);

    List<Producer> getByCountry(String country);

    List<Producer> getByCountryCode(String countryCode);
}
