package com.taisiia.shop.flywieght.nongeneric.strategy.impl;

import com.taisiia.shop.flywieght.model.FileType;
import com.taisiia.shop.flywieght.nongeneric.strategy.GeneratorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CsvGeneratorStrategy implements GeneratorStrategy{
    @Override
    public FileType getFileType() {
        return FileType.CSV;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generated csv file");
        return new byte[0];
    }
}
