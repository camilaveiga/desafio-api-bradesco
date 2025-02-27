package com.example.desafio_api.service;

import com.example.desafio_api.entity.Usuario;
import com.example.desafio_api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario CadastrarUsuario(@Valid Usuario usuario){

        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email jah cadastrado!");
        }

        if(usuarioRepository.findByPhone(usuario.getPhone()).isPresent()){
            throw new IllegalArgumentException("Telefone jah cadastrado!");
        }

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(@Valid Long id){
        Optional<Usuario> existeUsuario = usuarioRepository.findById(id);

        if(existeUsuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao cadastrado");
        }
        return usuarioRepository.findById(id);
    }

    public Usuario atualizaUsuario(@Valid Long id, @Valid Usuario usuario){
        Optional<Usuario> existeUsuario = usuarioRepository.findById(id);

        if(existeUsuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao cadastrado");
        }

        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(@Valid Long id){
        Optional<Usuario> deletaUsuario = usuarioRepository.findById(id);

        if(deletaUsuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario nao cadastrado");
        }

        usuarioRepository.deleteById(id);

    }


}
