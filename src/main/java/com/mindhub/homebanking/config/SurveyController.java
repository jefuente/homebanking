package com.mindhub.homebanking.config;


import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.PerfilInversionType;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@Controller
public class SurveyController {


    @Autowired
    private ClientRepository clientRepository;


    @PostMapping("/survey/{id}")
    public ResponseEntity<Object> surveyhandler(@RequestParam("res") int res, @RequestParam("id") Long id, Model model) {

    //System.out.println("total:"+ res);
    //System.out.println("id: "+id);
    Client cliente= this.clientRepository.findById(id).orElse(null);
   // System.out.println("cliente: " +cliente);


    if(res>0 && res<11){

        cliente.setPerfilInversion(PerfilInversionType.CONSERVADOR);
       // System.out.println("Conservador");
       // System.out.println("perfil:"+ cliente.getPerfilInversion());
    }
    if(res>=11 && res<21){
        cliente.setPerfilInversion(PerfilInversionType.MODERADO);
        //System.out.println("Moderado");
       // System.out.println("perfil:"+ cliente.getPerfilInversion());
    }
    if(res>=22 && res<33){
        cliente.setPerfilInversion(PerfilInversionType.CRECIMIENTO);
       // System.out.println("En crecimiento");
      //  System.out.println("perfil:"+ cliente.getPerfilInversion());
    }
    if(res>=34 && res<44){
        cliente.setPerfilInversion(PerfilInversionType.DECIDIDO);
      //  System.out.println("Decidido");
      //  System.out.println("perfil:"+ cliente.getPerfilInversion());
    }
    if(res>=45 && res<54){
        cliente.setPerfilInversion(PerfilInversionType.AUDAZ);
       // System.out.println("Audaz");
       // System.out.println("perfil:"+ cliente.getPerfilInversion());
    }
    if(res>=55){
        cliente.setPerfilInversion(PerfilInversionType.ESPECULATIVO);
       // System.out.println("Especulativo");
       // System.out.println("perfil:"+ cliente.getPerfilInversion());
    }

   clientRepository.save(cliente);

    return new ResponseEntity<>(HttpStatus.CREATED);
}



}
