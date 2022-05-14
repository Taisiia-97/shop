package com.taisiia.shop.flywieght.generic.strategy.generator.file.impl;

import com.taisiia.shop.flywieght.generic.strategy.generator.file.FileGeneratorStrategy;
import com.taisiia.shop.flywieght.model.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JsonFileGenerator implements FileGeneratorStrategy {
    @Override
    public FileType getType() {
        return FileType.JSON;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generated json file");
        return new byte[0];
    }
}
