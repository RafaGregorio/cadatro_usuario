package com.rafael.usuario.business.converter;

import com.rafael.usuario.business.dto.EnderecoDTO;
import com.rafael.usuario.business.dto.TelefoneDTO;
import com.rafael.usuario.business.dto.UsuarioDTO;
import com.rafael.usuario.infrastructure.entity.Endereco;
import com.rafael.usuario.infrastructure.entity.Telefone;
import com.rafael.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefones(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    // =================================================================================================

    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefonesDTO(usuarioDTO.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public Usuario usuarioUpdate(UsuarioDTO usuarioDTO,  Usuario usuario){
        return Usuario.builder()
                .id(usuario.getId())
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuario.getNome())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuario.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuario.getEmail())
                .enderecos(usuario.getEnderecos())
                .telefones(usuario.getTelefones())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO dto, Endereco endereco){
        return Endereco.builder()
                .id(endereco.getId())
                .rua(dto.getRua() != null ?  dto.getRua() : endereco.getRua())
                .numero(dto.getNumero() != null ?  dto.getNumero() : endereco.getNumero())
                .cidade(dto.getCidade() != null ? dto.getCidade(): endereco.getCidade())
                .estado(dto.getEstado() != null ? dto.getEstado() : endereco.getEstado())
                .cep(dto.getCep() != null ? dto.getCep() : endereco.getCep())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : endereco.getComplemento())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO dto, Telefone telefone){
        return Telefone.builder()
                .id(telefone.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : telefone.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : telefone.getNumero())
                .build();
    }
}
