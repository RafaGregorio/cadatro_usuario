package com.rafael.usuario.business;

import com.rafael.usuario.business.converter.UsuarioConverter;
import com.rafael.usuario.business.dto.UsuarioDTO;
import com.rafael.usuario.infrastructure.entity.Usuario;
import com.rafael.usuario.infrastructure.exceptions.ConflictException;
import com.rafael.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO );
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    };

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("Email já cadastrado" + email);
            }
        }catch(ConflictException e){
            throw new ConflictException("Email já cadastrado " + e.getCause());
        }
    }

    // Resposável apenas por chamar o nosso metodo da nossa Repository-
    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }
}
