package it.epicode.EpicEnergyBE.fattura.stato_fattura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatoFatturaService {

    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public StatoFattura createStatoFattura(String nome){
        StatoFattura statoFattura= new StatoFattura();
        statoFattura.setNome(nome);

        return statoFatturaRepository.save(statoFattura);
    }

    public List<StatoFattura> findAll(){
        return statoFatturaRepository.findAll();
    }

    public void deleteStatoFattura(String nome){
        statoFatturaRepository.deleteByNome(nome);
    }

}
