package com.devthalisson.dscommerce.services;

import com.devthalisson.dscommerce.dto.UserDTO;
import com.devthalisson.dscommerce.entities.Role;
import com.devthalisson.dscommerce.entities.User;
import com.devthalisson.dscommerce.projections.UserDetailsProjection;
import com.devthalisson.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("User not Found!");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());

        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    //Metodo responsavel por buscr o usuario logdo na sessão e consultar no banco
    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return userRepository.findByEmail(username).get();
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("User not Found!");
        }
    }

    //Chama a função para buscr o usuario
    @Transactional(readOnly = true)
    public UserDTO getMe() {
        User user = authenticated();
        return new UserDTO(user);
    }
}
