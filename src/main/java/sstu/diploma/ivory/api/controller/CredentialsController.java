package sstu.diploma.ivory.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sstu.diploma.ivory.api.data.CredentialsData;
import sstu.diploma.ivory.api.service.CredentialsService;
import sstu.diploma.ivory.exception.IvorySecurityException;

import java.security.Principal;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/credentials")
public class CredentialsController {

    private final CredentialsService credentialsService;

    @PostMapping
    public ResponseEntity<String> postCredentials(@RequestBody CredentialsData data, Principal principal) {
        val name = principal.getName();
        if (name == null || name.isBlank()) {
            throw new IvorySecurityException("Authentication failed");
        }
        long id = credentialsService.saveCredentials(data, name);
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return new ResponseEntity<>(uri.toString(), HttpStatus.CREATED);
    }

    @GetMapping
    public Set<CredentialsData> getCredentials(Principal principal) {
        val name = principal.getName();
        if (name == null || name.isBlank()) {
            throw new IvorySecurityException("Authentication failed");
        }
        return credentialsService.findAllForUserName(name);
    }
}
