package com.example.guver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.guver.models.Oferta;
import com.example.guver.services.OfertaService;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    // Crear una nueva oferta
    @PostMapping
    public ResponseEntity<Oferta> createOferta(@RequestBody Oferta oferta) {
        Oferta createdOferta = ofertaService.save(oferta);
        return ResponseEntity.ok(createdOferta);
    }

    // Obtener todas las ofertas
    @GetMapping
    public ResponseEntity<List<Oferta>> getAllOfertas() {
        List<Oferta> ofertas = ofertaService.findAll();
        return ResponseEntity.ok(ofertas);
    }

    // Obtener una oferta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Oferta> getOfertaById(@PathVariable Long id) {
        Oferta oferta = ofertaService.findById(id);
        return ResponseEntity.ok(oferta);
    }

    // Actualizar una oferta existente
    @PutMapping("/{id}")
    public ResponseEntity<Oferta> updateOferta(@RequestBody Oferta oferta, @PathVariable Long id) {
        Oferta updatedOferta = ofertaService.update(oferta, id);
        return ResponseEntity.ok(updatedOferta);
    }

    // Eliminar una oferta por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOferta(@PathVariable Long id) {
        ofertaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
