package tz.go.ega.shambamkononibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz.go.ega.shambamkononibackend.model.Fertilizer;

import java.util.Optional;

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
    Optional<Fertilizer> findByCode(String code);
}
