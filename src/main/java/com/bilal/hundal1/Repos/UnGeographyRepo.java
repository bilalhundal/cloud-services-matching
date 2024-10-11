package com.bilal.hundal1.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bilal.hundal1.models.UnGeography;
@Repository
public interface UnGeographyRepo extends JpaRepository<UnGeography, String> {
   Optional<UnGeography> findByUnGeographyId(String UnGeographyId);
}
