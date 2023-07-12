package ibm.third.test.service;

import ibm.third.test.exceptions.AlreadyParticipatingException;
import ibm.third.test.exceptions.InvalidNameException;
import ibm.third.test.exceptions.NotFoundException;
import ibm.third.test.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CandidateService {
    public static final String APROVADO = "Aprovado";
    public static final String RECEBIDO = "Recebido";
    public static final String QUALIFICADO = "Qualificado";
    public int id;

    public HashMap<Integer, Candidate> info = new HashMap<>();

    public int iniciarProcesso(String name) throws Exception {
        validarNome(name);

        var candidate = new Candidate();
        candidate.id = 1000;
        candidate.name = name;
        candidate.status = RECEBIDO;
        info.put(candidate.id,candidate);
        id++;
        return candidate.id;
    }

    private void validarNome(String name){
        if(name == null|| name.isEmpty()){
            throw new InvalidNameException("Nome inválido");
        }
        for (Candidate candidate : info.values()) {
            if (name.equals(candidate.name)) {
                throw new AlreadyParticipatingException("Candidato já participa do processo.");
            }
        }
    }

    public void marcarEntrevista(int codCandidato) throws Exception {
        validarId(codCandidato);
        var candidato = info.get(codCandidato);
        if(!candidato.status.equals(RECEBIDO)){
            throw new NotFoundException("Candidato não encontrado");
        }
        candidato.status = QUALIFICADO;
    }

    private void validarId(int codCandidato) throws Exception {
        if(!info.containsKey(codCandidato)){
            throw new NotFoundException("Candidato não encontrado");
        }
    }

    public void desqualificarCandidato(int codCandidato) throws Exception {
        validarId(codCandidato);
        info.remove(codCandidato);
    }

    public String verificarStatusCandidato(int codCandidato) throws Exception {
        validarId(codCandidato);
        var candidato = info.get(codCandidato);
        return candidato.status;
    }

    public void aprovarCandidato(int codCandidato) throws Exception {
        validarId(codCandidato);
        var candidato = info.get(codCandidato);
        if(!candidato.status.equals(QUALIFICADO)){
            throw new NotFoundException("Candidato não encontrado");
        }
        candidato.status = APROVADO;
    }

    public List<String> obterAprovados(){
        var aprovados = new ArrayList<String>();
        for (Candidate candidate : info.values()) {
            if (candidate.status.equals(APROVADO)) {
                aprovados.add(candidate.name);
            }
        }
        return aprovados;
    }
}

