package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestRegister;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestRegister;
import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestUpdate;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;

public class TimbraturaMapper
{
    @Autowired
    DipendenteService dipendenteService;

    public Timbratura fromTimbraturaRequestRegister(TimbraturaRequestRegister timbraturaRequestRegister)
    {
        return Timbratura
                .builder()
                .orario_entrata(timbraturaRequestRegister.orario_entrata())
                .inizio_pranzo(timbraturaRequestRegister.inizio_pranzo())
                .fine_pranzo(timbraturaRequestRegister.fine_pranzo())
                .uscita(timbraturaRequestRegister.uscita())
                .data_corrente(timbraturaRequestRegister.data_corrente())
                .build();

    }

    public Timbratura fromTimbraturaRequestUpdate(TimbraturaRequestUpdate timbraturaRequestUpdate)
    {
        return Timbratura
                .builder()
                .orario_entrata(timbraturaRequestUpdate.Orario_entrata())
                .inizio_pranzo(timbraturaRequestUpdate.Inizio_pranzo())
                .fine_pranzo(timbraturaRequestUpdate.Fine_pranzo())
                .uscita(timbraturaRequestUpdate.Uscita())
                .data_corrente(timbraturaRequestUpdate.data_corrente())
                .build();

    }
}
