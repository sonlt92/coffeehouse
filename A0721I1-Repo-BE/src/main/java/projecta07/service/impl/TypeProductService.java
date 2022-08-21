package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.TypeProduct;
import projecta07.repository.ITypeProductRepository;
import projecta07.service.ITypeProductService;

import java.util.List;

@Service
public class TypeProductService implements ITypeProductService {
    @Autowired
    private ITypeProductRepository iTypeProductRepository;
    @Autowired
    private ITypeProductRepository typeProductRepository;
    @Override
    public List<TypeProduct> findByAll() {
        return iTypeProductRepository.findAll();
    }

    public List<TypeProduct> getTypesProduct() {
        return typeProductRepository.findAll();
    }

}

