package com.fileblocker.flow.extensions.repository;


import com.fileblocker.flow.extensions.domain.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension, Long> {
    boolean existsByName(String name);
    long count();
}
