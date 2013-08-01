package inscricao.faces.mngbeans;

import inscricao.faces.convert.CEPConverter;
import inscricao.faces.convert.CPFConverter;
import inscricao.faces.validator.CPFValidator;
import inscricao.persistence.entity.Candidato;
import inscricao.persistence.entity.Idioma;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import exemplo.faces.support.PageBean;
import exemplo.persistence.controller.IdiomaJpaController;
import exemplo.persistence.controller.JpaController;

/**
 *
 * @author Vitor Yudi Hansen
 */
@ManagedBean
@RequestScoped
public class InscricaoBean extends PageBean {
    private Candidato candidato = new Candidato(new Idioma(1)); // inicialmente ingles
    private boolean linkGRUVisivel = false;
    private CPFConverter cpfConverter = new CPFConverter();
    private CEPConverter cepConverter = new CEPConverter();
    private CPFValidator cpfValidator = new CPFValidator();
    private SimpleDateFormat formatDataVencto = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatCompetencia = new SimpleDateFormat("MM/yyyy");

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public CEPConverter getCepConverter() {
        return cepConverter;
    }

    public CPFConverter getCpfConverter() {
        return cpfConverter;
    }

    public CPFValidator getCpfValidator() {
        return cpfValidator;
    }
 
    private boolean validaCandidato() {
        JpaController ctl = new JpaController();
        EntityManager em = ctl.getEntityManager();
        try {
            Candidato c = em.find(Candidato.class, candidato.getCpf());
            return c == null;
        } finally {
            em.close();
        }
    }
    
    public List<SelectItem> getIdiomaItemList() {
        IdiomaJpaController ctl = new IdiomaJpaController();
        EntityManager em = ctl.getEntityManager();
        
        try {
            List<SelectItem> itens = new ArrayList<SelectItem>();
            List<Idioma> idiomas = ctl.findAll();
            for (Idioma id: idiomas) {
                itens.add(new SelectItem(id.getCodigo(), id.getDescricao()));
            }
            return itens;
        } finally {
            em.close();
        }
    }
    
    public boolean isLinkGRUVisivel() {
        return linkGRUVisivel;
    }
    
    public String getDataVencimento() {
        GregorianCalendar hoje = new GregorianCalendar();
        hoje.add(Calendar.DAY_OF_MONTH, 1);
        return formatDataVencto.format(hoje.getTime());
    }
    
    public String getCompetencia() {
        GregorianCalendar hoje = new GregorianCalendar();
        hoje.add(Calendar.DAY_OF_MONTH, 1);
        return formatCompetencia.format(hoje.getTime());
    }
    
    public void inscricaoAction() {
        JpaController ctl = new JpaController();
        EntityManager em = ctl.getEntityManager();
        try {
            if (validaCandidato()) {
                em.getTransaction().begin();
                em.persist(candidato);
                em.getTransaction().commit();
                info("Inscrição realizada com sucesso");
            } else {
                error("Este CPF já está inscrito");
            }
            linkGRUVisivel = true;
        } catch (Exception e) {
            log("Incrição teste classificatorio", e);
            error("Não foi possível completar a operação: " + e.getLocalizedMessage());
        } finally {
            em.close();
        }
    }
}
