package com.taisiia.shop.flywieght.nongeneric.strategy.impl;

import com.taisiia.shop.flywieght.model.FileType;
import com.taisiia.shop.flywieght.nongeneric.strategy.GeneratorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class XlsGeneratorStrategy implements GeneratorStrategy {
    @Override
    public FileType getFileType() {
        return FileType.XLS;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generated xls file");
        return new byte[0];
    }
}
