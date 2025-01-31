package Project.GestioneAziendale.Services;

import Project.GestioneAziendale.Entities.TokenBlackList;
import Project.GestioneAziendale.Repositories.TokenBlackListRepository;
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

    public void insertToken(Long id_dipendente, String token) {
        TokenBlackList tokenBlackList = TokenBlackList
                .builder()
                .token(token)
                .dipendente(dipendenteService.getDipendenteById(id_dipendente))
                .build();
        tokenBlackListRepository.save(tokenBlackList);
    }

}
