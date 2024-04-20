package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Essa anotetion vai permite que eu customise minha consulta
    // estou entrando com a consulta SQL nativeQuery = true
    //UPPER() vai garanti a buscar Maísculas e Menusculas
    @Query(nativeQuery = true, value = "select name from customers "
            + "where UPPER(state) = UPPER(:state)")
    List<CustomerMinProjection> search1(String state);


    // Essa anotetion vai permite que eu customise minha consulta
    // estou entrando com a consulta JPQL
    @Query("select new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name) from Customer obj "
            + "where UPPER(obj.state) = UPPER(:state)")
    List<CustomerMinDTO> search2(String state);
}
