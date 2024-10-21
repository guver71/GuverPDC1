package com.example.guver.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.guver.exceptions.ResourceNotFoundException;
import com.example.guver.models.Oferta;
import com.example.guver.repositories.OfertaRepository;

@Service
@Transactional
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Override
    public Oferta save(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    @Override
    public List<Oferta> findAll() {
        return ofertaRepository.findAll();
    }

    @Override
    public Oferta findById(Long id) {
        return ofertaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Oferta no encontrada con el id: " + id));
    }

    @Override
    public Oferta update(Oferta oferta, Long id) {
        Oferta existingOferta = ofertaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Oferta no encontrada con el id: " + id));
        
        existingOferta.setTitulo(oferta.getTitulo());
        existingOferta.setDescripcion(oferta.getDescripcion());
        existingOferta.setEmpresa(oferta.getEmpresa());
        existingOferta.setFechaPublicacion(oferta.getFechaPublicacion());
        existingOferta.setFechaCierre(oferta.getFechaCierre());
        existingOferta.setSalario(oferta.getSalario());

        return ofertaRepository.save(existingOferta);
    }

    @Override
    public void delete(Long id) {
        Oferta oferta = ofertaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Oferta no encontrada con el id: " + id));
        
        ofertaRepository.delete(oferta);
    }
}
