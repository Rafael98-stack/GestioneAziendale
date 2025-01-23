package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.TimbraturaDtos.TimbraturaRequestRegister;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimbraturaMapper
{
    @Autowired
    DipendenteService dipendenteService;

    public Timbratura fromTimbraturaRequestRegister(TimbraturaRequestRegister timbraturaRequestRegister)
    {
        return Timbratura
                .builder()
                .dipendente(dipendenteService.getDipendenteById(timbraturaRequestRegister.id_dipendente()))
                .build();

    }
    /*
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
    */
}
