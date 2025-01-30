package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Repositories.TokenBlackListRepository;
import com.example.bankApp.domain.entities.TokenBlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService {

    @Autowired
    private TokenBlackListRepository tokenBlackListRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public Boolean isPresentToken(String token) {
        return tokenBlackListRepository.getByToken(token).isPresent();
    }

    public void insertToken(Long id_utente, String token) {
        TokenBlackList tokenBlackList = TokenBlackList
                .builder()
                .token(token)
                .utente(dipendenteService.getById(id_utente))
                .build();
        tokenBlackListRepository.save(tokenBlackList);
    }

}
