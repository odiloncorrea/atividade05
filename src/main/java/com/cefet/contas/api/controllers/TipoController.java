package com.cefet.contas.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.Api;

import com.cefet.contas.api.entities.Tipo;
import com.cefet.contas.api.services.TipoService;

@RestController
@RequestMapping("/api/tipo")
@Api(value = "tipo", tags = "Aplicativo 05 - Contas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TipoController {
	
	private static final Logger log = LoggerFactory.getLogger(TipoController.class);

	@Autowired
	private TipoService tipoService;
	
	public TipoController() {
	}


	/**
     * {@code POST  /} : Create a new tipo.
     *
     * @param tipo the tipo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipo, or with status {@code 400 (Bad Request)} if the tipo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Tipo> createTipo(@Valid @RequestBody Tipo tipo) throws URISyntaxException {
        log.debug("REST request to save tipo : {}", tipo);
        if (tipo.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Uma novo tipo não pode ter um ID");
        }
        Tipo result = tipoService.save(tipo);
        return ResponseEntity.created(new URI("/api/tipos/" + result.getId()))
                .body(result);
    }
    
    /**
     * {@code PUT  /tipo} : Atualiza um tipo existente Update.
     *
     * @param tipo o tipo a ser atualizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o tipo atualizado,
     * ou com status {@code 400 (Bad Request)} se o tipo não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o tipo não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Tipo> updateTipo(@Valid @RequestBody Tipo tipo) throws URISyntaxException {
        log.debug("REST request to update Tipo : {}", tipo);
        if (tipo.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Pessoa id null");
        }
        Tipo result = tipoService.save(tipo);
        return ResponseEntity.ok()
                .body(result);
    }
    
	
    /**
     * {@code GET  /tipo/:id} : get the "id" tipo.
     *
     * @param id o id da tipo que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o tipo, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tipo> getTipo(@PathVariable Long id) {
        log.info("REST request to get tipo : {}", id);
        Optional<Tipo> tipo = tipoService.findOne(id);
        if(tipo.isPresent()) {
            return ResponseEntity.ok().body(tipo.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Tipo>> getTipo(){
       List<Tipo> lista = tipoService.findAllList();
       return ResponseEntity.ok().body(lista);
       /*
       if(lista.size() > 0) {
           return ResponseEntity.ok().body(lista);
       }else{
           return ResponseEntity.notFound().build();
       }
       */
    }
    
	
    /**
     * {@code DELETE  /tipo/:id} : delete pelo "id" tipo.
     *
     * @param id o id do tipo que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipo(@PathVariable Long id) {
        log.info("REST request to delete tipo : {}", id);
        tipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * {@code GET  /tipo/:descricao/exists} : get the "descricao" tipo.
     *
     * @param descricao da tipo que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)}, ou com status {@code 204 (Not Found)}.
     */
    @GetMapping("/{descricao}/exists")
    public ResponseEntity<Boolean> isExisting(@PathVariable String descricao){
        log.info("REST request to get tipo By Descrição : {}", descricao);

        if(tipoService.findByDescricao(descricao).isPresent()) {
            return ResponseEntity.ok().body(Boolean.TRUE);
        }else{
        	return ResponseEntity.ok().body(Boolean.FALSE);
        }
    }
  
}
