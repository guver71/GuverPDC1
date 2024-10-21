package com.example.guver.controllers;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.guver.models.Oferta;
import com.example.guver.repositories.OfertaRepository;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfertaControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private OfertaRepository ofertaRepository;

    private Oferta oferta;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        // Limpiar la base de datos antes de cada prueba
        ofertaRepository.deleteAll();

        // Crear una oferta para usar en las pruebas
        oferta = new Oferta();
        oferta.setTitulo("Desarrollador Java");
        oferta.setDescripcion("Desarrollador Java con 3 años de experiencia.");
        oferta.setEmpresa("Empresa XYZ");
        oferta.setFechaPublicacion(new Date());
        oferta.setFechaCierre(new Date());
        oferta.setSalario(5000.0);
        ofertaRepository.save(oferta);
    }

    @Test
    public void testGetAllOfertas() {
        given()
            .when().get("/api/ofertas")
            .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].titulo", equalTo("Desarrollador Java"));
    }

    @Test
    public void testGetOfertaById() {
        Long id = oferta.getId();
        given()
            .when().get("/api/ofertas/{id}", id)
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Desarrollador Java"))
            .body("empresa", equalTo("Empresa XYZ"));
    }

    @Test
    public void testCreateOferta() {
        Oferta newOferta = new Oferta();
        newOferta.setTitulo("Analista de Datos");
        newOferta.setDescripcion("Analista con experiencia en Python y SQL.");
        newOferta.setEmpresa("Empresa ABC");
        newOferta.setFechaPublicacion(new Date());
        newOferta.setFechaCierre(new Date());
        newOferta.setSalario(4500.0);

        given()
            .contentType(ContentType.JSON)
            .body(newOferta)
            .when().post("/api/ofertas")
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Analista de Datos"))
            .body("empresa", equalTo("Empresa ABC"));
    }

    @Test
    public void testUpdateOferta() {
        Long id = oferta.getId();
        oferta.setTitulo("Desarrollador Full Stack");

        given()
            .contentType(ContentType.JSON)
            .body(oferta)
            .when().put("/api/ofertas/{id}", id)
            .then()
            .statusCode(200)
            .body("titulo", equalTo("Desarrollador Full Stack"));
    }

    @Test
    public void testDeleteOferta() {
        Long id = oferta.getId();

        given()
            .when().delete("/api/ofertas/{id}", id)
            .then()
            .statusCode(204);

        List<Oferta> allOfertas = ofertaRepository.findAll();
        assertEquals(0, allOfertas.size());
    }
}
