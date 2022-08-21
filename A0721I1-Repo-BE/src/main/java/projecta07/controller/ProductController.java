package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Product;
import projecta07.model.TypeProduct;
import projecta07.service.impl.ProductService;
import projecta07.service.impl.TypeProductService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    TypeProductService typeProductService;

    @GetMapping
    public ResponseEntity<List<Product>> findByAll() {
        List<Product> productList = productService.findByAll();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Product>> findByAllPageGing(Pageable pageable, @RequestParam String page) {
        int page1 = Integer.parseInt(page);
        pageable = PageRequest.of(page1, 8);
        Page<Product> productList1 = productService.findByAllPaging(pageable);
        if (productList1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productList1, HttpStatus.OK);
        }
    }

    @GetMapping("/type")
    public ResponseEntity<List<TypeProduct>> findByAllTypeproduct() {
        List<TypeProduct> typeProducts = typeProductService.findByAll();
        if (typeProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(typeProducts, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable(name = "id") Long id1) {
        productService.deleteById(id1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam(defaultValue = "") String code, @RequestParam(defaultValue = "") String name) {
        List<Product> productList = productService.Search(code, name);
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }

    @GetMapping("/searchPage")
    public ResponseEntity<Page<Product>> search(@RequestParam(defaultValue = "") String code,
                                                @RequestParam(defaultValue = "") String name,
                                                Pageable pageable, @RequestParam String page1) {
        int page2 = Integer.parseInt(page1);
        pageable = PageRequest.of(page2, 8);
        Page<Product> productList2 = productService.searchPage(code, name, pageable);
        if (productList2.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productList2, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Product product) {
        product.setCreateAt(Date.valueOf(LocalDate.now()));
        productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id) {
        Optional<Product> product1 = productService.findById(id);
        return new ResponseEntity<>(product1.get(), HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity edit(@RequestBody Product product) {
        productService.editProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}

