package com.taisiia.shop.controller;

import com.taisiia.shop.domain.dto.ProducerDto;
import com.taisiia.shop.mapper.ProducerMapper;
import com.taisiia.shop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/producer")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()&&hasRole('ADMIN')")
public class ProducerController {
    private final ProducerService producerService;
    private final ProducerMapper producerMapper;

    @GetMapping("/{id}")
    public ProducerDto findProducerById(@PathVariable Long id) {
        return producerMapper.toDto(producerService.findById(id));
    }

    @PostMapping
    public ProducerDto saveProducer(@RequestBody @Valid ProducerDto producerDto) {
        return producerMapper.toDto(producerService.save(producerMapper.toDao(producerDto)));
    }

    @PutMapping("/{id}")
    public ProducerDto updateProducer(@RequestBody @Valid ProducerDto producerDto, @PathVariable Long id) {
        return producerMapper.toDto(producerService.update(producerMapper.toDao(producerDto), id));
    }

    @DeleteMapping("/{id}")
    public void deleteProducer(@PathVariable Long id) {
        producerService.delete(id);
    }

    @GetMapping
    public Page<ProducerDto> getProductPage(@RequestParam int page, @RequestParam int size) {
        return producerService.getPage(PageRequest.of(page, size)).map(producerMapper::toDto);
    }


    @GetMapping("/name")
    public ProducerDto getProducerByName(@RequestParam String name) {
        return producerMapper.toDto(producerService.getByName(name));
    }

    @GetMapping("/country")
    public List<ProducerDto> getProducerByCountry(@RequestParam String country) {
        return producerMapper.toListDto(producerService.getByCountry(country));

    }

    @GetMapping("/code")
    public List<ProducerDto> getProducerByCountryCode(@RequestParam String code) {
        return producerMapper.toListDto(producerService.getByCountryCode(code));
    }

}
