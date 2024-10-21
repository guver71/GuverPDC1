package com.example.guver.services;

import java.util.List;

import com.example.guver.models.Oferta;

public interface OfertaService {
    
    // Guardar una oferta
    Oferta save(Oferta oferta);
    
    // Listar todas las ofertas
    List<Oferta> findAll();
    
    // Buscar una oferta por su ID
    Oferta findById(Long id);
    
    // Actualizar una oferta
    Oferta update(Oferta oferta, Long id);
    
    // Eliminar una oferta por su ID
    void delete(Long id);
}
