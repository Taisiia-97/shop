package com.taisiia.shop.flywieght.generic.strategy.generator.file.impl;

import com.taisiia.shop.flywieght.generic.strategy.generator.file.FileGeneratorStrategy;
import com.taisiia.shop.flywieght.model.FileType;
import org.springframework.stereotype.Component;

@Component
public class CsvFileGenerator implements FileGeneratorStrategy {
    @Override
    public FileType getType() {
        return FileType.CSV;
    }

    @Override
    public byte[] generateFile() {
        return new byte[0];
    }
}
