package com.example.guver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.guver.models.Oferta;

@Repository
public interface  OfertaRepository extends JpaRepository<Oferta, Long> {
    
}
