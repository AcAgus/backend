package com.portfolio.agusAc.Security.Repository;

import com.portfolio.agusAc.Security.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer>{

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existeByNombreUsuario(String nombreUsuario);
    boolean existeByemail(String email);
}
