package ibm.third.test.controllers;

import ibm.third.test.dto.ProcessDTO;
import ibm.third.test.dto.StartDTO;
import ibm.third.test.model.Candidate;
import ibm.third.test.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/v1/hiring")
public class CandidateController {
    @Autowired
    CandidateService service;
    @PostMapping("/start")
    public ResponseEntity<Integer> iniciarProcesso(@RequestBody StartDTO initiating) throws Exception {
      var id = service.iniciarProcesso(initiating.getNome());
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/schedule")
    public ResponseEntity marcarEntrevista(@RequestBody ProcessDTO scheduling) throws Exception {
        service.marcarEntrevista(scheduling.getCodCandidato());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/disqualify")
    public ResponseEntity desqualificarCandidato(@RequestBody ProcessDTO disqualifying) throws Exception {
        service.desqualificarCandidato(disqualifying.getCodCandidato());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/approve")
    public ResponseEntity aprovarCandidato(@RequestBody ProcessDTO approving) throws Exception {
        service.aprovarCandidato(approving.getCodCandidato());
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/status/candidate/{codCandidato}")
    public ResponseEntity verificarStatusCandidato(@PathVariable("codCandidato") Integer codCandidato) throws Exception {
        var status = service.verificarStatusCandidato(codCandidato);
        return new ResponseEntity(status,HttpStatus.OK);
    }

   @GetMapping("/approved")
   public ResponseEntity<List<String>> obterAprovados(){
        return new ResponseEntity<>(service.obterAprovados(),HttpStatus.OK);
   }



}
