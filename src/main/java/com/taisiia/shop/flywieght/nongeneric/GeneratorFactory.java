package com.taisiia.shop.flywieght.nongeneric;

import com.taisiia.shop.flywieght.model.FileType;
import com.taisiia.shop.flywieght.nongeneric.strategy.GeneratorStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GeneratorFactory {
    private final List<GeneratorStrategy> generatorStrategiesList;
    private Map<FileType, GeneratorStrategy> generatorStrategyMap;

    @PostConstruct
    void init() {
        generatorStrategyMap = generatorStrategiesList.stream()
                .collect(Collectors.toMap(GeneratorStrategy::getFileType, Function.identity()));
    }


    public GeneratorStrategy getStrategyByFileType(FileType fileType) {
        return generatorStrategyMap.get(fileType);
    }

}
