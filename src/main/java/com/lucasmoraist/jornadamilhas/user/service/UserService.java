package com.lucasmoraist.jornadamilhas.user.service;

import com.lucasmoraist.jornadamilhas.infra.security.TokenService;
import com.lucasmoraist.jornadamilhas.user.dto.CreateOrUpdateUserDTO;
import com.lucasmoraist.jornadamilhas.user.dto.LoginDTO;
import com.lucasmoraist.jornadamilhas.user.dto.ResponseDTO;
import com.lucasmoraist.jornadamilhas.user.model.User;
import com.lucasmoraist.jornadamilhas.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception{
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("User Not Found"));
    }

    public void createUser(CreateOrUpdateUserDTO dto) throws Exception{
        try{
            Optional<User> verifyIfExistEmail = this.userRepository.findByEmail(dto.email());

            if(verifyIfExistEmail.isEmpty()) {
                String passwordEncrypted = passwordEncoder.encode(dto.password());

                User newUser = User.builder()
                        .name(dto.name())
                        .email(dto.email())
                        .password(passwordEncrypted)
                        .build();

                this.userRepository.save(newUser);
            }else{
                throw new Exception("Este email já existe");
            }
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Campos obrigatórios não preenchidos");
        }
    }

    public ResponseDTO authLogin(LoginDTO dto) throws Exception{
        try{
            User user = this.userRepository.findByEmail(dto.email()).orElseThrow(() -> new Exception("Campos obrigatórios não preenchidos"));

            if(!passwordEncoder.matches(dto.password(), user.getPassword())){
                throw new Exception("Senha incorreta");
            }

            String token = this.tokenService.generateToken(user);
            return new ResponseDTO("Login feito com sucesso", token);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Campos obrigatórios não preenchidos");
        }
    }

    public User updateUser(Long id, CreateOrUpdateUserDTO dto) throws Exception{
        User user = this.userRepository.findById(id).orElseThrow(()->new Exception("User Not Found"));

        var verifyIfExistEmail = this.userRepository.findByEmail(dto.email());
        if(verifyIfExistEmail.isPresent()){
            throw new Exception("Este email já existe");
        }

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        if(dto.name() == null){
            user.setName(user.getName());
        } else if (dto.email() == null) {
            user.setEmail(user.getEmail());
        } else if (dto.password() == null) {
            user.setPassword(user.getPassword());
        }

        return user;
    }

    public void deleteUser(Long id) throws Exception{
        var user = this.userRepository.findById(id).orElseThrow(()-> new Exception(""));
        this.userRepository.delete(user);
    }

}
