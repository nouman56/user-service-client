package com.ascertia.userserviceclient.repository;


import com.ascertia.userserviceclient.entities.Operations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsRepository extends JpaRepository<Operations,Long> {
}
