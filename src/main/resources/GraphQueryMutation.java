package repository;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import java.util.List;

public class GraphQueryMutation implements GraphQLQueryResolver, GraphQLMutationResolver {

    private OptionBusiness optionBusiness = new OptionBusiness();
    private EtudiantBusiness etudiantBusiness = new EtudiantBusiness();

    // ================= SECTION A : OPTIONS =================

    // A.1 Liste de toutes les options
    public List<Option> getAllOptions() {
        return optionBusiness.getOptions();
    }

    // A.2 Création d'une nouvelle option
    public Boolean addOption(int codeOption, String libelle, String domaine, String responsable, int credits, int semestre, int capacite) {
        Option opt = new Option(codeOption, libelle, domaine, responsable, credits, semestre, capacite);
        return optionBusiness.addOption(opt);
    }

    // A.3 Modification d'une option
    public Boolean updateOption(int codeOption, String libelle, String domaine, String responsable, int credits, int semestre, int capacite) {
        Option opt = new Option(codeOption, libelle, domaine, responsable, credits, semestre, capacite);
        return optionBusiness.updateOption(codeOption, opt);
    }

    // A.4 Options par domaine
    public List<Option> getOptionsByDomaine(String domaine) {
        return optionBusiness.getOptionsByDomaine(domaine);
    }

    // A.5 Option par code
    public Option getOptionByCode(int codeOption) {
        return optionBusiness.getOptionByCode(codeOption);
    }

    // A.6 Suppression d'une option
    public Boolean deleteOption(int codeOption) {
        return optionBusiness.deleteOption(codeOption);
    }

    // ================= SECTION B : ETUDIANTS =================

    // B.1 Création d'un étudiant
    public Boolean addEtudiant(String identifiant, String nom, String prenom, int anneeEtude, String email, int codeOption) {
        Option opt = optionBusiness.getOptionByCode(codeOption);
        Etudiant e = new Etudiant(identifiant, nom, prenom, opt, anneeEtude, email);
        return etudiantBusiness.addEtudiant(e);
    }

    // B.2 Récupération d'un étudiant par identifiant
    public Etudiant getEtudiantByIdentifiant(String identifiant) {
        return etudiantBusiness.getEtudiantByIdentifiant(identifiant);
    }

    // B.3 Récupération des étudiants par code option
    public List<Etudiant> getEtudiantsByOption(int codeOption) {
        Option opt = optionBusiness.getOptionByCode(codeOption);
        return etudiantBusiness.getEtudiantsByOption(opt);
    }

    // B.4 Mise à jour d'un étudiant
    public Boolean updateEtudiant(String identifiant, String nom, String prenom, int anneeEtude, String email, int codeOption) {
        Option opt = optionBusiness.getOptionByCode(codeOption);
        Etudiant e = new Etudiant(identifiant, nom, prenom, opt, anneeEtude, email);
        return etudiantBusiness.updateEtudiant(identifiant, e);
    }

    // B.5 Suppression d'un étudiant
    public Boolean deleteEtudiant(String identifiant) {
        return etudiantBusiness.deleteEtudiant(identifiant);
    }
}