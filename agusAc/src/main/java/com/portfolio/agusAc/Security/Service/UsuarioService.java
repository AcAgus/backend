package com.portfolio.agusAc.Security.Service;

import com.portfolio.agusAc.Security.Entity.Usuario;
import com.portfolio.agusAc.Security.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    IUsuarioRepository iUsuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return iUsuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existeByNombreUsuario(String nombreUsuario){
        return iUsuarioRepository.existeByNombreUsuario(nombreUsuario);
    }

    public boolean existeByEmail(String email){
        return iUsuarioRepository.existeByemail(email);
    }

    public void save(Usuario usuario){
        iUsuarioRepository.save(usuario);
    }
}

