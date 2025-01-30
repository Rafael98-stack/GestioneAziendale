package Project.GestioneAziendale.security;

import Project.GestioneAziendale.Dtos.AuthRequest;
import Project.GestioneAziendale.Dtos.AuthenticationResponse;
import Project.GestioneAziendale.Dtos.ComunicazioneScheduledDtos.GenericResponse;
import Project.GestioneAziendale.Dtos.RegisterRequest;
import Project.GestioneAziendale.Entities.Dipendente;
import Project.GestioneAziendale.Entities.Enums.Role;
import Project.GestioneAziendale.ExceptionHandlers.Exceptions.MyEntityNotFoundException;
import Project.GestioneAziendale.Services.DipendenteService;
import Project.GestioneAziendale.Services.TokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenBlackListService tokenBlackListService;
    @Autowired
    private JavaMailSender javaMailSender;

    public AuthenticationResponse register(RegisterRequest request) {
        Dipendente dipendente = Dipendente
                .builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .telefono(request.telefono())
                .luogo_nascita(request.luogo_nascita())
                .data_nascita(request.data_nascita())
                .immagine_profilo(request.immagine_profilo())
                .role(Role.TOCONFIRM)
                .build();
        String jwtToken = jwtService.generateToken(dipendente);
        dipendente.setRegistrationToken(jwtToken);
        dipendenteService.insertDipendente(dipendente);
        // TODO invio email di conferma
        String confirmationUrl = "http://localhost:8080/app/v1/auth/confirm?token=" + dipendente.getRegistrationToken();
        javaMailSender.send(createConfirmationEmail(dipendente.getEmail(), confirmationUrl));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthRequest request) throws MyEntityNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email().toLowerCase(),
                request.password()
        ));
        Dipendente dipendente = dipendenteService.getByEmail(request.email());
        String token = jwtService.generateToken(dipendente);
        //dipendente.setLastLogin(LocalDateTime.now());
        dipendenteService.insertDipendente(dipendente);
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

    public GenericResponse confirmRegistration(String token) throws MyEntityNotFoundException {
        Dipendente dipendente = dipendenteService.getByRegistrationToken(token);
        dipendente.setRole(Role.UTENTE);
        dipendenteService.insertDipendente(dipendente);
        return GenericResponse
                .builder()
                .message("Account verificato con successo!")
                .build();
    }

    /*
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
    */
}
