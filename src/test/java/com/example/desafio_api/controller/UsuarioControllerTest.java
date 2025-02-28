package com.example.desafio_api.controller;

import com.example.desafio_api.entity.Usuario;
import com.example.desafio_api.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

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

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    void testAdicionaUsuario() throws Exception {
        Usuario novoUsuario = usuarioMock();

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novoUsuario)))
                .andExpect(status().isOk());
    }

    @Test
    void testListarUsuarios() throws Exception {
        List<Usuario> usuarios = List.of(usuarioMock());

        when(usuarioService.listarUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].fullName").value("Camila Veiga"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscarPorId() throws Exception {
        Usuario buscaUsuario = usuarioMock();
        when(usuarioService.buscarPorId(1L)).thenReturn(Optional.of(buscaUsuario));

        mockMvc.perform(get("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fullName").value("Camila Veiga"))
                .andExpect(status().isOk());
    }

    @Test
    void testAualizaUsuario() throws Exception {
        Usuario atualizaUsuario = usuarioMock();
        atualizaUsuario.setFullName("Camila Batista Veiga");

        when(usuarioService.atualizaUsuario(eq(1L), any(Usuario.class))).thenReturn(atualizaUsuario);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atualizaUsuario)))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fullName").value("Camila Batista Veiga"))
                .andExpect(jsonPath("$.email").value("camilaveiga@email.com.br"));

    }

    @Test
    void testDeletarUsuario() throws Exception  {
        Usuario deletarUsuario = usuarioMock();

        doNothing().when(usuarioService).deletarUsuario(1L);

        mockMvc.perform(delete("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}