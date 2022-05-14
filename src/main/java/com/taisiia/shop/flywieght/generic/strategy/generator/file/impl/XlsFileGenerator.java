package com.taisiia.shop.flywieght.generic.strategy.generator.file.impl;

import com.taisiia.shop.flywieght.generic.strategy.generator.file.FileGeneratorStrategy;
import com.taisiia.shop.flywieght.model.FileType;
import com.taisiia.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
@RequiredArgsConstructor
public class XlsFileGenerator implements FileGeneratorStrategy {
    private final ProductRepository productRepository;

    @Override
    public FileType getType() {
        return FileType.XLS;
    }

    @Override
    public byte[] generateFile() {
        try (var workbook = WorkbookFactory.create(false)) {
            var sheet = workbook.createSheet("report");
            var row = sheet.createRow(0);
            row.createCell(0).setCellValue("id");
            row.createCell(1).setCellValue("description");
            row.createCell(2).setCellValue("price");
            row.createCell(3).setCellValue("producent");
            row.createCell(4).setCellValue("quantity");
            row.createCell(5).setCellValue("name");
            var products = productRepository.findAll();
            AtomicInteger integer = new AtomicInteger(1);
            products.forEach(product -> {
                var dataRow = sheet.createRow(integer.getAndIncrement());
                dataRow.createCell(0).setCellValue(product.getId());
                dataRow.createCell(1).setCellValue(product.getDescription());
                dataRow.createCell(2).setCellValue(product.getPrice());
                dataRow.createCell(3).setCellValue(product.getProducer().getName());
                dataRow.createCell(4).setCellValue(product.getQuantity());
                dataRow.createCell(5).setCellValue(product.getName());
            });
            sheet.setAutoFilter(new CellRangeAddress(0, products.size(), 0, 5));
            var byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            log.error("Error creating EXEL");

        }
        return new byte[0];
    }
}
