package com.taisiia.shop.repository;

import com.taisiia.shop.domain.dao.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer findOneByName(String name);

    List<Producer> findByCountryCode(String countryCode);

    List<Producer> findByCountry(String country);
}
