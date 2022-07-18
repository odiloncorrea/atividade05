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

import com.cefet.contas.api.entities.Conta;
import com.cefet.contas.api.services.ContaService;

@RestController
@RequestMapping("/api/conta")
@Api(value = "conta", tags = "Aplicativo 05 - Contas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

	private static final Logger log = LoggerFactory.getLogger(ContaController.class);

	@Autowired
	private ContaService contaService;
	
	public ContaController() {}
	
	
	/**
     * {@code POST  /} : Create a new conta.
     *
     * @param conta the conta to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conta, or with status {@code 400 (Bad Request)} if the conta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Conta> createConta(@Valid @RequestBody Conta conta) throws URISyntaxException {
        log.debug("REST request to save conta : {}", conta);
        if (conta.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Uma nova conta não pode ter um ID");
        }
        Conta result = contaService.save(conta);
        return ResponseEntity.created(new URI("/api/contas/" + result.getId()))
                .body(result);
    }
    
    /**
     * {@code PUT  /conta} : Atualiza um conta existente Update.
     *
     * @param contato o conta a ser atulizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o conta atualizado,
     * ou com status {@code 400 (Bad Request)} se o conta não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o conta não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Conta> updateConta(@Valid @RequestBody Conta conta) throws URISyntaxException {
        log.debug("REST request to update conta : {}", conta);
        if (conta.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid conta id null");
        }
        Conta result = contaService.save(conta);
        return ResponseEntity.ok()
                .body(result);
    }
    
	
    /**
     * {@code GET  /conta/:id} : get the "id" conta.
     *
     * @param id o id do conta que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o conta, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContato(@PathVariable Long id) {
        log.info("REST request to get Contato : {}", id);
        Optional<Conta> conta = contaService.findOne(id);
        if(conta.isPresent()) {
            return ResponseEntity.ok().body(conta.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Conta>> getConta(){
       List<Conta> lista = contaService.findAllList();
       
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
     * {@code DELETE  /conta/:id} : delete pelo "id" conta.
     *
     * @param id o id do conta que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
        log.info("REST request to delete conta : {}", id);
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * {@code GET  /conta/:descricao/exists} : get the "conta" item.
     *
     * @param descricao da conta que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)}, ou com status {@code 204 (Not Found)}.
     */
    @GetMapping("/{descricao}/exists")
    public ResponseEntity<Boolean> isExisting(@PathVariable String descricao){
        log.info("REST request to get conta By Descrição : {}", descricao);

        if(contaService.findByDescricao(descricao).isPresent()) {
            return ResponseEntity.ok().body(Boolean.TRUE);
        }else{
        	return ResponseEntity.ok().body(Boolean.FALSE);
        }
    }
}
