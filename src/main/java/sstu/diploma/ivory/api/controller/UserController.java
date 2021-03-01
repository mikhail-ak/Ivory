package sstu.diploma.ivory.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sstu.diploma.ivory.api.data.UserData;
import sstu.diploma.ivory.api.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserData userData) {
        val id = userService.register(userData);
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return new ResponseEntity<>(uri.toString(), HttpStatus.CREATED);
    }
}
