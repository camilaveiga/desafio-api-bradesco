package com.example.desafio_api.service;

import com.example.desafio_api.entity.Usuario;
import com.example.desafio_api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private MockMvc mockMvc;

    private Usuario usuarioMock() throws ParseException {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setFullName("Camila Veiga");
        usuario.setEmail("camilaveiga@email.com.br");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        usuario.setBirthDate(sdf.parse("2002-04-05"));

        usuario.setPhone("+55 11 98765-4321");
        usuario.setUserType(Usuario.UserType.EDITOR);

        return usuario;
    }

    @Test
    void testCadastrarUsuario() throws Exception{
        Usuario novoUsuario = usuarioMock();

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(novoUsuario);

        Usuario usuarioAdicionado = usuarioService.CadastrarUsuario(novoUsuario);

        assertNotNull(usuarioAdicionado);
        assertEquals(usuarioAdicionado.getId(), novoUsuario.getId());
        assertEquals(novoUsuario.getFullName(), usuarioAdicionado.getFullName());
        assertEquals(novoUsuario.getEmail(), usuarioAdicionado.getEmail());
        assertEquals(novoUsuario.getBirthDate(), usuarioAdicionado.getBirthDate());
        assertEquals(novoUsuario.getUserType(), usuarioAdicionado.getUserType());

    }

    @Test
    void testListarUsuarios() throws Exception {
        List<Usuario> usuarios = List.of(usuarioMock());

        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> resultado = usuarioService.listarUsuarios();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Camila Veiga", resultado.get(0).getFullName());

    }

    @Test
    void testBuscarPorId() throws Exception {
        Usuario buscarUsuario = usuarioMock();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(buscarUsuario));
        Optional<Usuario> resultado = usuarioService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Camila Veiga", resultado.get().getFullName());
        assertEquals("camilaveiga@email.com.br", resultado.get().getEmail());
        assertEquals("+55 11 98765-4321", resultado.get().getPhone());
    }

    @Test
    void testAtualizaUsuario() throws Exception {
        Usuario atualizaUsuario = usuarioMock();
        atualizaUsuario.setFullName("Camila Batista Veiga");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(atualizaUsuario));
        Optional<Usuario> resultado = usuarioService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(atualizaUsuario.getFullName(), resultado.get().getFullName());
        assertEquals(atualizaUsuario.getEmail(), resultado.get().getEmail());
        assertEquals(atualizaUsuario.getPhone(), resultado.get().getPhone());
    }

    @Test
    void testDeletarUsuario() throws Exception {
        Usuario deleteUsuario = usuarioMock();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(deleteUsuario));
        usuarioService.deletarUsuario(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);

    }
}