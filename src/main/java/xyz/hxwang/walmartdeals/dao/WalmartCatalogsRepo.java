package xyz.hxwang.walmartdeals.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;
import xyz.hxwang.walmartdeals.entity.WalmartCatalogs;

@Repository
public interface WalmartCatalogsRepo extends JpaRepository<WalmartCatalogs, Long>{
    public WalmartCatalogs findById(String id);
}
