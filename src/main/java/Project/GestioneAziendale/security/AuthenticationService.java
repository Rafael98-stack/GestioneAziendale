package Project.GestioneAziendale.security;

import Project.GestioneAziendale.Dtos.AuthRequest;
import Project.GestioneAziendale.Dtos.AuthenticationResponse;
import Project.GestioneAziendale.Dtos.RegisterRequest;
import com.example.bankApp.domain.dto.requests.ChangePasswordRequest;
import com.example.bankApp.domain.dto.responses.ErrorResponse;
import com.example.bankApp.domain.dto.responses.GenericResponse;
import com.example.bankApp.domain.entities.Utente;
import com.example.bankApp.domain.enums.Role;
import com.example.bankApp.services.ComuneService;
import com.example.bankApp.services.TokenBlackListService;
import com.example.bankApp.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthenticationService {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenBlackListService tokenBlackListService;
    @Autowired
    private JavaMailSender javaMailSender;

    public AuthenticationResponse register(RegisterRequest request) {
        Utente utente = Utente
                .builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .telefono(request.telefono())
                .codiceFiscale(request.codiceFiscale())
                .dataNascita(request.dataNascita())
                .indirizzo(request.indirizzo())
                .role(Role.TOCONFIRM)
                .comune(comuneService.getById(request.comune_id().id()))
                .build();
        String jwtToken = jwtService.generateToken(utente);
        utente.setRegistrationToken(jwtToken);
        utenteService.insertUtente(utente);
        // TODO invio email di conferma
        String confirmationUrl = "http://localhost:8080/app/v1/auth/confirm?token=" + utente.getRegistrationToken();
        javaMailSender.send(createConfirmationEmail(utente.getEmail(), confirmationUrl));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email().toLowerCase(),
                request.password()
        ));
        Utente utente = utenteService.getByEmail(request.email());
        String token = jwtService.generateToken(utente);
        utente.setLastLogin(LocalDateTime.now());
        utenteService.insertUtente(utente);
        return AuthenticationResponse.builder().token(token).build();
    }

    public GenericResponse logout(Long idUtente, String token) {
        tokenBlackListService.insertToken(idUtente,token);
        return GenericResponse.builder().message("Logout effettuato con successo").build();
    }

    private SimpleMailMessage createConfirmationEmail(String email, String confirmationUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email); // a chi mando la mail
        message.setReplyTo("adriani.marco.93@gmail.com"); // a chi rispondo se faccio "rispondi"
        message.setFrom("adriani.marco.93@gmail.com"); // da chi viene la mail
        message.setSubject("CONFERMA REGISTRAZIONE BANKAPP"); // il TITOLO!
        message.setText("Ciao! Clicca su questo link per confermare la registrazione! " + confirmationUrl); // il testo!
        return message; // ritorno il messaggio
    }

    public GenericResponse confirmRegistration(String token) {
        Utente utente = utenteService.getByRegistrationToken(token);
        utente.setRole(Role.UTENTE);
        utenteService.insertUtente(utente);
        return GenericResponse
                .builder()
                .message("Account verificato con successo!")
                .build();
    }

    public Object changePassword(Long id_utente, ChangePasswordRequest request) {
        Utente utente = utenteService.getById(id_utente);
        if (!passwordEncoder.matches(request.oldPassword(), utente.getPassword())) {
            // se la vecchia password passata non coincide
            return ErrorResponse
                    .builder()
                    .exception("WrongPasswordException")
                    .message("La vecchia password non è corretta")
                    .build();
        }
        utente.setPassword(passwordEncoder.encode(request.newPassword())); // setto la nuova password
        utenteService.insertUtente(utente);
        return GenericResponse
                .builder()
                .message("Password cambiata con successo")
                .build();
    }
}
