package sw2.clase05ej2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase05ej2.entity.Shipper;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

    List<Shipper> findByCompanyName(String nombre);

    @Query(value = "select * from shippers where CompanyName = ?1",
            nativeQuery = true)
    List<Shipper> buscarTransPorCompName(String nombre);


}

