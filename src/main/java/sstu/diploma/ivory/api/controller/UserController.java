package sstu.diploma.ivory.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sstu.diploma.ivory.api.service.UserService;
import sstu.diploma.ivory.api.data.UserData;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RestController
@RequestMapping(path = "user/")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserData userData) {
        return userService.saveIfNotExists(userData)
                .map(savedUserId -> {
                    val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}").buildAndExpand(savedUserId).toUri();
                    return ResponseEntity.created(uri).build();
                })
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));    // TODO: add explanation of failure to register
                                                                       // which may fix the ResponseEntity type parameter
    }
}
