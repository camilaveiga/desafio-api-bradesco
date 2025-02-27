package com.example.desafio_api.controller;

import com.example.desafio_api.entity.Usuario;
import com.example.desafio_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/users")
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.CadastrarUsuario(usuario));
    }

    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PutMapping("users/{id}")
    public ResponseEntity<Usuario> atualizaUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.atualizaUsuario(id, usuario));
    }

    @DeleteMapping("/users/{id}")
    public void deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
    }
}
