package com.bilal.hundal1.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilal.hundal1.models.CanonicalServiceTaxonomy;
@Repository
public interface CanonicalServiceTaxonomyRepo extends JpaRepository<CanonicalServiceTaxonomy, String> {
    Optional<CanonicalServiceTaxonomy> findByCanonicalServiceName(String CanonicalServiceName);
}
