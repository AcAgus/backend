package com.portfolio.agusAc.Security.Service;

import com.portfolio.agusAc.Security.Entity.Rol;
import com.portfolio.agusAc.Security.Enums.RolNombre;
import com.portfolio.agusAc.Security.Repository.IRolRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    IRolRespository iRolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return iRolRepository.findByRolNombre(rolNombre);
    }

    public void guardar(Rol rol){
        iRolRepository.save(rol);
    }

}
