package com.example.lab6_salvador_anais.repository;
import com.example.lab6_salvador_anais.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
public interface SiteRepository extends JpaRepository<Site, Integer>{
}
