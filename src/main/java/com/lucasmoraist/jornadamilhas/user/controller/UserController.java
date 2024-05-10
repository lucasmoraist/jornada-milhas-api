package com.lucasmoraist.jornadamilhas.user.controller;

import com.lucasmoraist.jornadamilhas.user.dto.CreateOrUpdateUserDTO;
import com.lucasmoraist.jornadamilhas.user.dto.LoginDTO;
import com.lucasmoraist.jornadamilhas.user.dto.ResponseDTO;
import com.lucasmoraist.jornadamilhas.user.model.User;
import com.lucasmoraist.jornadamilhas.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/list-users")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(this.service.findAllUsers());
    }

    @GetMapping("/list-user/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findUserById(id));
    }

    @PostMapping("auth/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody CreateOrUpdateUserDTO dto) {
        return ResponseEntity.ok(this.service.createUser(dto));
    }

    @PostMapping("auth/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO dto) {
        return ResponseEntity.ok().body(this.service.authLogin(dto));
    }

    @PutMapping("update-user/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody CreateOrUpdateUserDTO dto) {
        return ResponseEntity.ok().body(this.service.updateUser(id, dto));
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.service.deleteUser(id);
        return ResponseEntity.ok("Excluido com sucesso");
    }
}
