//package br.com.pucminas.hospital.repository;
//
//import br.com.pucminas.hospital.model.entity.Assessment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.Tuple;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//@Repository
//public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
//    @Query("SELECT a FROM Assessment a WHERE a.patient.register = :register")
//    Optional<List<Assessment>> findAssessmentByRegister(@Param("register") String register);
//
//    @Query("SELECT a FROM Assessment a WHERE a.callDay BETWEEN :startDate AND :finalDate")
//    List<Assessment> getStatisticByAssessment(@Param("startDate") LocalDate startDate,
//                                              @Param("finalDate") LocalDate finalDate);
//
//    @Query("SELECT a FROM Assessment a WHERE a.callDay BETWEEN :startDate AND :finalDate")
//    List<Assessment> teste1(@Param("startDate") LocalDate startDate,
//                                              @Param("finalDate") LocalDate finalDate);
//
//    @Query(value = "SELECT stock_akhir.product_id AS productId, stock_akhir.product_code AS productCode, SUM(stock_akhir.qty) as stockAkhir "
//            + "FROM book_stock stock_akhir "
//            + "where warehouse_code = (:warehouseCode) "
//            + "AND product_code IN (:productCodes) "
//            + "GROUP BY product_id, product_code, warehouse_id, warehouse_code", nativeQuery = true)
//    List<Tuple> findStockAkhirPerProductIn(@Param("warehouseCode") String warehouseCode, @Param("productCodes") Set<String> productCode);
//}