package com.taisiia.shop.flywieght.generic.strategy.generator.file;

import com.taisiia.shop.flywieght.generic.strategy.GenericStrategy;
import com.taisiia.shop.flywieght.model.FileType;

public interface FileGeneratorStrategy extends GenericStrategy<FileType> {
    byte[] generateFile();
}
