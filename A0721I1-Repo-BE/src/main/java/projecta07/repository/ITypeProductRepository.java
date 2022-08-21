package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.TypeProduct;

@Repository
public interface ITypeProductRepository extends JpaRepository<TypeProduct , Long> {
}

