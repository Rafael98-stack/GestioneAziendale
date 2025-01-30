package Project.GestioneAziendale.security;

import com.example.bankApp.domain.dto.requests.AuthRequest;
import com.example.bankApp.domain.dto.requests.ChangePasswordRequest;
import com.example.bankApp.domain.dto.requests.RegisterRequest;
import com.example.bankApp.domain.dto.responses.AuthenticationResponse;
import com.example.bankApp.domain.dto.responses.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request) {
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthRequest request) {
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.CREATED);
    }

    @PostMapping("/logout/{id_utente}")
    public ResponseEntity<GenericResponse> logout(@PathVariable Long id_utente, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return new ResponseEntity<>(authenticationService.logout(id_utente, token), HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public ResponseEntity<GenericResponse> confirmRegistration(@RequestParam String token) {
        return new ResponseEntity<>(authenticationService.confirmRegistration(token), HttpStatus.CREATED);
    }

    @PostMapping("/change_pw/{id_utente}")
    public ResponseEntity<?> changePassword(@PathVariable Long id_utente, @RequestBody ChangePasswordRequest request) {
        Object result = authenticationService.changePassword(id_utente, request);
        if (result.getClass() == GenericResponse.class) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    }

}
