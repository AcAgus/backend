
package com.portfolio.agusAc.Security.Controller;

import com.portfolio.agusAc.Security.Dto.JwtDto;
import com.portfolio.agusAc.Security.Dto.LoginUsuario;
import com.portfolio.agusAc.Security.Dto.NuevoUsuario;
import com.portfolio.agusAc.Security.Entity.Rol;
import com.portfolio.agusAc.Security.Entity.Usuario;
import com.portfolio.agusAc.Security.Enums.RolNombre;
import com.portfolio.agusAc.Security.Jwt.JwtProvider;
import com.portfolio.agusAc.Security.Service.RolService;
import com.portfolio.agusAc.Security.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider provider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevoUser(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Email invalido o campos mal completados"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existeByNombreUsuario(nuevoUsuario.getNombreUsuario())){
            return new ResponseEntity(new Mensaje("Este usuario ya existe"),HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existeByEmail(nuevoUsuario.getEmail())){
            return new ResponseEntity(new Mensaje("Este email ya esta registrado"),HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(),nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(),passwordEncoder.encode(nuevoUsuario.getPass()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        if(nuevoUsuario.getRoles().contains("admin")){
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario registrado correctamente"),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Campos mal ingresados"),HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),loginUsuario.getPass()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = provider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDTO = new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());
        return new ResponseEntity(jwtDTO,HttpStatus.OK);
    }
}

