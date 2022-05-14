package com.taisiia.shop.service.impl

import com.taisiia.shop.config.properties.FilePropertiesConfig
import com.taisiia.shop.domain.dao.Category
import com.taisiia.shop.domain.dao.Producer
import com.taisiia.shop.domain.dao.Product
import com.taisiia.shop.helper.FileHelper
import com.taisiia.shop.repository.ProductRepository
import com.taisiia.shop.service.CategoryService
import com.taisiia.shop.service.ProducerService
import org.springframework.web.multipart.MultipartFile
import spock.lang.Specification

import java.nio.file.Paths

class ProductServiceImplSpec extends Specification {
    def productRepository = Mock(ProductRepository)
    def producerService = Mock(ProducerService)
    def categoryService = Mock(CategoryService)
    def fileHelper = Mock(FileHelper)
    def filePropertiesConfig = Mock(FilePropertiesConfig)
    def productServiceImpl = new ProductServiceImpl(productRepository, producerService, categoryService, fileHelper, filePropertiesConfig)

    def "should save product without photo"() {

        given:
        def product = Mock(Product)
        def photo = null
        def producerId = 5
        def categoriesIds = Set.of(1, 5, 7)
        def producer = Mock(Producer)
        def categories = Set.of(new Category(id: 1), new Category(id: 5))

        when:
        productServiceImpl.save(product, photo, producerId, categoriesIds)


        then:
        1 * producerService.findById(producerId) >> producer
        1 * product.setProducer(producer)
        1 * categoryService.getCategories(categoriesIds) >> categories
        1 * product.setCategories(categories)
        1 * productRepository.save(product)
        0 * _
    }

    def "should save product with photo"() {
        given:
        def product = Mock(Product)
        def photo = Mock(MultipartFile)
        def path = Paths.get("path", "name" + ".png")
        def inputStream = Mock(InputStream)
        def producerId = 5
        def categoriesIds = Set.of(1, 5, 7)
        def producer = Mock(Producer)
        def categories = Set.of(new Category(id: 1), new Category(id: 5))

        when:
        productServiceImpl.save(product, photo, producerId, categoriesIds)

        then:
        1 * filePropertiesConfig.getProduct() >> "path"
        1 * product.getName() >> "name"
        1* photo.getInputStream() >> inputStream
        1 * product.setPhotoUrl(path.toString())
        1 * fileHelper.copy(inputStream, path)
        1 * producerService.findById(producerId) >> producer
        1 * product.setProducer(producer)
        1 * categoryService.getCategories(categoriesIds) >> categories
        1 * product.setCategories(categories)
        1 * productRepository.save(product)
        0 * _
    }

    def "should find product by id"() {
        given:
        def id = 5

        when:
        productServiceImpl.findById(id)

        then:
        1 * productRepository.getById(id)
        0 * _
    }

    def "should delete product by id"() {
        given:
        def id = 5

        when:
        productServiceImpl.delete(id)

        then:
        1 * productRepository.deleteById(id)
        0 * _
    }
}
