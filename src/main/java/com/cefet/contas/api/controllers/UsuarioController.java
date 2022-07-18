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

import com.cefet.contas.api.entities.Usuario;
import com.cefet.contas.api.services.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/usuario")
@Api(value = "usuario", tags = "Aplicativo 05 - Contas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioController() {}
	
	/**
     * {@code POST  /} : Create a new usuario.
     *
     * @param usuario the usuario to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usuario, or with status {@code 400 (Bad Request)} if the usuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) throws URISyntaxException {
        log.debug("REST request to save usuario : {}", usuario);
        if (usuario.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo usuario não pode ter um ID");
        }
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.created(new URI("/api/usuario/" + result.getId()))
                .body(result);
    }
    
    /**
     * {@code PUT  /usuario} : Atualiza um usuario existente Update.
     *
     * @param usuario o usuario a ser atulizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o usuario atualizado,
     * ou com status {@code 400 (Bad Request)} se o usuario não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o usuario não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario) throws URISyntaxException {
        log.debug("REST request to update usuario : {}", usuario);
        if (usuario.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid usuario id null");
        }
        Usuario result = usuarioService.save(usuario);
        return ResponseEntity.ok()
                .body(result);
    }
    
	
    /**
     * {@code GET  /usuario/:id} : get the "id" usuario.
     *
     * @param id o id do usuario que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o usuario, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        log.info("REST request to get usuario : {}", id);
        Optional<Usuario> usuario = usuarioService.findOne(id);
        if(usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getUsuario(){
       List<Usuario> lista = usuarioService.findAllList();
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
     * {@code DELETE  /usuario/:id} : delete pelo "id" usuario.
     *
     * @param id o id do usuario que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        log.info("REST request to delete usuario : {}", id);
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code GET  /usuario/:email/exists} : get the "usuário" item.
     *
     * @param email do usuario que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)}, ou com status {@code 204 (Not Found)}.
     */
    @GetMapping("/{email}/exists")
    public ResponseEntity<Boolean> isExisting(@PathVariable String email){
        log.info("REST request to get Compra By Descrição : {}", email);
        if(usuarioService.findByEmail(email).isPresent()) {
            return ResponseEntity.ok().body(Boolean.TRUE);
        }else{
        	return ResponseEntity.ok().body(Boolean.FALSE);
        }
    }
    
    @GetMapping("/{email}/{senha}/authenticate")
    public ResponseEntity<Usuario> authenticateUsuario(@PathVariable  String email, @PathVariable String senha){
        log.debug("REST request to registrar usuario Usuario : {}", email, senha);
        Optional<Usuario> usuario = usuarioService.findUsuarioByEmailAndSenha(email, senha);

        if(usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        }else{
        	return ResponseEntity.ok().body(new Usuario());
        }

    }
    
}
