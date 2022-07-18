package com.cefet.contas.api.services;

import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.contas.api.entities.Usuario;
import com.cefet.contas.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		log.info("Persistindo usuario: {}", usuario);
		return usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> findOne(Long id) {
		log.info("Buscando usuario por id: {}", id);
		return usuarioRepository.findById(id);
	}
	
	public List<Usuario> findAllList() {
		log.info("Listando os usuarios");
		return usuarioRepository.findAll();
	}
	
	public void delete(Long id) {
		log.info("Excluindo usuario: {}", id);
		usuarioRepository.deleteById(id);
	}	
	
	public Optional<Usuario> findByEmail(String email) {
		log.info("Buscando um Usuário para o email {}", email);
		return usuarioRepository.findByEmail(email);
	}
	
    public Optional<Usuario> findUsuarioByEmailAndSenha(String email, String senha) {
        log.debug("Buscando um usuário por Email and Senha : {}", email, senha);
        return usuarioRepository.findUsuarioByEmailAndSenha(email, senha);
    }
    
}
