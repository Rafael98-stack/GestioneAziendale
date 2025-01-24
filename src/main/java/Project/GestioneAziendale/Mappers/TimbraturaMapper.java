package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestRegister;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestUpdate;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TimbraturaMapper {
    @Autowired
    DipendenteService dipendenteService;

    public Timbratura fromTimbraturaRequestRegister(TimbraturaRequestRegister timbraturaRequestRegister) {
        return Timbratura
                .builder()
                .data_corrente(LocalDate.now())
                .dipendente(dipendenteService.getDipendenteById(timbraturaRequestRegister.id_dipendente()))
                .build();

    }

    public Timbratura fromTimbraturaRequestUpdate(TimbraturaRequestUpdate timbraturaRequestUpdate) {
        // Aggiorna i campi della timbratura in base all'input
        switch (timbraturaRequestUpdate.numero_scelta()) {
            case 1:
                return Timbratura.builder().id(timbraturaRequestUpdate.id_timbratura().longValue()).orario_entrata(LocalDateTime.now()).build();
            case 2:
                return Timbratura.builder().id(timbraturaRequestUpdate.id_timbratura().longValue()).orario_entrata(LocalDateTime.now()).build();
            case 3:
                return Timbratura.builder().id(timbraturaRequestUpdate.id_timbratura().longValue()).orario_entrata(LocalDateTime.now()).build();
            case 4:
                return Timbratura.builder().id(timbraturaRequestUpdate.id_timbratura().longValue()).orario_entrata(LocalDateTime.now()).build();
            default:
                System.out.println("Nessuna scelta impostata o Non valida. Sceglielte 1-4");
        }
        return null;
    }
}
