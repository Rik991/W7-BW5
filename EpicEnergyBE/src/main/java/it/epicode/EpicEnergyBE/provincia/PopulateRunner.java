//package it.epicode.EpicEnergyBE.provincia;
//
//
//import it.epicode.EpicEnergyBE.provincia.comune.ComuneService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@Transactional
//public class PopulateRunner implements ApplicationRunner {
//
//    @Autowired
//    private ProvinciaService provinciaService;
//
//    @Autowired
//    private ComuneService comuneService;
//
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        provinciaService.importProvinceFromCSV("src/main/resources/assets/province-italiane.csv");
//        comuneService.importComuniFromCSV("src/main/resources/assets/comuni-italiani.csv");
//    }
//}
