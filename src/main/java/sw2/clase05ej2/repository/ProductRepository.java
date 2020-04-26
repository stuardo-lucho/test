package sw2.clase05ej2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.clase05ej2.entity.Category;
import sw2.clase05ej2.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    public List<Product> findByCategory(Category category);
}

