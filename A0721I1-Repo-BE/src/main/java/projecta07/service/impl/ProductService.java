package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.model.Product;
import projecta07.repository.IProductRepository;
import projecta07.service.IProductService;
import java.util.Optional;
import java.util.List;


@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository iProductRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> findByAll() {
        return iProductRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public List<Product> Search(String codeProduct, String nameProduct) {
        return iProductRepository.findProductByCodeProductContainingAndNameProductContaining(codeProduct, nameProduct);
    }

    @Override
    public void createProduct(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public Page<Product> findByAllPaging(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchPage(String codeProduct, String nameProduct, Pageable pageable) {
        return iProductRepository.findProductByCodeProductContainingAndNameProductContaining(codeProduct, nameProduct, pageable);
    }

    @Override
    public void editProduct(Product product) {
        iProductRepository.save(product);
    }


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public List<Product> findAllProductNew() {
        return iProductRepository.findAllProductNew();
    }

    @Override
    public List<Product> findMostAll() {
        return iProductRepository.findMostAll();
    }

    @Override
    public void subQuantity(Long idProduct, Integer quantity) {
        iProductRepository.subQuantity(idProduct, quantity);
    }


    /* Get products with pagination */
    public List<Product> getProductsWithPagination(int currentPage, int size) {
        return this.productRepository.getProductsWithPagination(currentPage, size);
    }

    /* Get products by product type id */
    public List<Product> getProductsByTypeProductId(Long id, int currentPage, int size) {
        return this.productRepository.getProductsByTypeProductId(id, currentPage, size);
    }

    /* Get amount of products */
    public int getAmountOfProducts() {
        return this.productRepository.getAmountOfProducts();
    }

    /* Get amount of products by id type */
    public int getAmountOfProductsByTypeId(Long id) {
        return this.productRepository.getAmountOfProductsByTypeId(id);
    }

    /* Get product by product id */
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }


}
