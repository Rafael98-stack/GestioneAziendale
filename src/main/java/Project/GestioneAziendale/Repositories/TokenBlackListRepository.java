package Project.GestioneAziendale.Repositories;

import Project.GestioneAziendale.Entities.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenBlackListRepository extends JpaRepository<TokenBlackList,Long> {
    Optional<TokenBlackList> getByToken(String token);

}
