package com.taisiia.shop.flywieght.generic.strategy.generator.file.impl;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.taisiia.shop.flywieght.generic.strategy.generator.file.FileGeneratorStrategy;
import com.taisiia.shop.flywieght.model.FileType;
import com.taisiia.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;

@Component
@Slf4j
@RequiredArgsConstructor
public class PdfFileGenerator implements FileGeneratorStrategy {
    private final ProductRepository productRepository;

    @Override
    public FileType getType() {
        return FileType.PDF;
    }

    @Override
    public byte[] generateFile() {
        var byteArrayOutputStream = new ByteArrayOutputStream();
        var document = new Document(new PdfDocument(new PdfWriter(byteArrayOutputStream)));
        var table = new Table(7);
        table.addHeaderCell("Id");
        table.addHeaderCell("Name");
        table.addHeaderCell("Description");
        table.addHeaderCell("Price");
        table.addHeaderCell("Producer");
        table.addHeaderCell("Quantity");
        table.addHeaderCell("Photo");
        productRepository.findAll().forEach(product -> {
            table.addCell(product.getId().toString());
            table.addCell(product.getName());
            table.addCell(product.getDescription());
            table.addCell(product.getPrice().toString());
            table.addCell(product.getProducer().getName());
            table.addCell(product.getQuantity().toString());
            try {
                if (product.getPhotoUrl() != null) {
                    table.addCell(new Image(ImageDataFactory.create(product.getPhotoUrl())).setAutoScale(true));
                } else {
                    table.addCell("Brak");
                }

            } catch (MalformedURLException e) {
                log.warn(e.getMessage(), e);
            }


        });
        document.add(table);
        document.close();
        log.info("Generated pdf file");
        return byteArrayOutputStream.toByteArray();
    }
}
