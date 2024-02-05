package xyz.hxwang.walmartdeals.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.hxwang.walmartdeals.entity.WalmartProducts;

import java.util.List;

@Repository
public interface WalmartProductsRepo extends JpaRepository<WalmartProducts, Long> {
    @Query(value = "SELECT * FROM walmart_products where name LIKE %:search% LIMIT 25 ", nativeQuery = true)
    List<WalmartProducts> searchByName(@Param("search") String search);

//	@Query(value="ALTER TABLE  dd.walmart_products\r\n"
//			+ "ADD FULLTEXT INDEX wProductsIndex (name);",nativeQuery = true)
//	@Query(value="CREATE FULLTEXT INDEX wProductsIndex ON dd.walmart_products(name);",nativeQuery = true)
//    public void createFullTextIndex();
//        String sql = "CREATE FULLTEXT INDEX product_fulltext_idx ON product(name)";

    @Query(value="SELECT COUNT(*) > 0 FROM information_schema.STATISTICS WHERE table_schema = 'dd' AND table_name = 'walmart_products' AND index_name = 'wProductsIndex';",nativeQuery = true)
    public Integer showFullTextindex();

    @Query(value="SELECT * FROM walmart_products\r\n"
            + "WHERE MATCH(name) AGAINST (:search) LIMIT 25;", nativeQuery = true)
    List<WalmartProducts>indexSearch(@Param("search") String search);

    @Query(value="SELECT *\r\n"
            + "FROM walmart_products\r\n"
            + "where original_price>=300\r\n"
            + "ORDER BY discount_percentage desc\r\n"
            + "LIMIT 25;",nativeQuery=true)
    List<WalmartProducts>findDiscountProducts();

    public WalmartProducts getByCatalogItemId(String cId);

    @Query(value="SELECT *\r\n"
            + "FROM walmart_products\r\n"
            + "WHERE catalog_id = :cIId AND current_price > 200\r\n"
            + "ORDER BY discount_percentage DESC;",nativeQuery=true)
    public List<WalmartProducts> getDiscountProductsBycatalog(@Param("cIId")String cIId);


}
