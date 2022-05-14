package com.taisiia.shop.flywieght.nongeneric.strategy;

import com.taisiia.shop.flywieght.model.FileType;

public interface GeneratorStrategy {

    FileType getFileType();

    byte[] generateFile();


}
