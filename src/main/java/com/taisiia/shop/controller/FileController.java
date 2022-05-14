package com.taisiia.shop.controller;

import com.taisiia.shop.flywieght.generic.GenericFactory;
import com.taisiia.shop.flywieght.generic.strategy.generator.file.FileGeneratorStrategy;
import com.taisiia.shop.flywieght.model.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {
    private final GenericFactory<FileType, FileGeneratorStrategy> genericFactory;


    @GetMapping
    public ResponseEntity<byte[]> testGenerateFile(@RequestParam FileType fileType) {
        var file = genericFactory.getStrategyByType(fileType).generateFile();
        var httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length));
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report." + fileType.toString().toLowerCase());
        return ResponseEntity.ok().headers(httpHeaders).body(file);
    }


}
