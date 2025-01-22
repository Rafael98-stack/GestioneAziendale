package Project.GestioneAziendale.Mappers;

import Project.GestioneAziendale.Dtos.DipendenteDtos.DipendenteRequestRegister;
import Project.GestioneAziendale.Entities.Timbratura;
import Project.GestioneAziendale.Services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;

public class TimbraturaMapper
{
    @Autowired
    DipendenteService dipendenteService;

    public Timbratura fromDipendenteRequestRegister(DipendenteRequestRegister dipendenteRequestRegister) {

    }
}
