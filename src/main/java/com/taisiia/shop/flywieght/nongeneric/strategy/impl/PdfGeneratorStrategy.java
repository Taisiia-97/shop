package com.taisiia.shop.flywieght.nongeneric.strategy.impl;

import com.taisiia.shop.flywieght.model.FileType;
import com.taisiia.shop.flywieght.nongeneric.strategy.GeneratorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PdfGeneratorStrategy implements GeneratorStrategy {
    @Override
    public FileType getFileType() {
        return FileType.PDF;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generated pdf file");
        return new byte[0];
    }
}
