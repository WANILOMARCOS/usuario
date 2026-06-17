package com.javanauta.usuario.business;

import com.javanauta.usuario.business.DTO.UsuarioDTO;
import com.javanauta.usuario.business.converter.UsuarioConverter;
import com.javanauta.usuario.infrastructure.entity.Usuario;
import com.javanauta.usuario.infrastructure.exception.ConflictException;
import com.javanauta.usuario.infrastructure.exception.ResourceNotFoudException;
import com.javanauta.usuario.infrastructure.security.JwtUtil;
import com.javanauta.usuario.infrastructure.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        existeEmail(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );

    }

    public void existeEmail (String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado" , e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioporEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoudException("Email não encontrado" + email));
    }
    public void deleteUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
    public UsuarioDTO atualizaDadosUsuario(String token,UsuarioDTO dto ){
        String email = jwtUtil.extractUsername(token.substring(7));
        Usuario usuarioEntity =usuarioRepository.findByEmail(email).orElseThrow(() ->
            new ResourceNotFoudException("Email não localizado"));

        dto.setSenha (dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);
        Usuario usuario = usuarioConverter.updateUsuario(dto,usuarioEntity);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }



}
