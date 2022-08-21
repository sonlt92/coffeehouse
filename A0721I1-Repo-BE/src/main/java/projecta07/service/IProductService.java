package projecta07.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projecta07.model.Product;

import projecta07.service.impl.ProductService;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findByAll();

    void deleteById(Long id);

    List<Product> Search(String codeProduct,String nameProduct);

    void createProduct(Product product);

    Optional<Product> findById(Long id);

    Page<Product> findByAllPaging(Pageable pageable);

    Page<Product> searchPage(String codeProduct,String nameProduct,Pageable pageable);

    Iterable<Product> findAll();

    Product save(Product product);

    void delete(Long id);

    List<Product> findAllProductNew();

    List<Product> findMostAll();

    void subQuantity(Long idProduct,Integer quantity);

    void editProduct(Product product);
}

