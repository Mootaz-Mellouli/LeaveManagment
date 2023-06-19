package com.leaveManagment.services.Jwt;

import com.leaveManagment.dto.JwtResponse;
import com.leaveManagment.entities.User;
import com.leaveManagment.dto.LoginDTO;
import com.leaveManagment.repositories.UserRepository;
import com.leaveManagment.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtService implements UserDetailsService {
    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JwtResponse createJwtToken(LoginDTO loginDTO) throws Exception {
        String matricule = loginDTO.getMatricule();
        String userPassword = loginDTO.getPassword();
        authenticate(matricule, userPassword);

        UserDetails userDetails = loadUserByUsername(matricule);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userRepository.findUserByMatricule(matricule);
        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String matricule) throws UsernameNotFoundException {
        User user = userRepository.findUserByMatricule(matricule);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getMatricule(),
                    user.getPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with matricule: " + matricule);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

    private void authenticate(String matricule, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(matricule, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
