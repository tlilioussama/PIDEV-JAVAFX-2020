package tn.covid19.entities;


public class Utilisateur {
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String cin;
    private String adresse;
    private String ville;
    private int tel;
    private String mail;
    private int age;
    private int role;
    private String login;
    private String motDePasse;

    public Utilisateur() {
    }
    public Utilisateur(int idUtilisateur, String nom, String prenom, String cin, String adresse, String ville, int tel, String mail, int age, int role, String login, String motDePasse) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.ville = ville;
        this.tel = tel;
        this.mail = mail;
        this.age = age;
        this.role = role;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(String nom, String prenom, String cin, String adresse, int tel, int age, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.tel = tel;
        this.age = age;
        this.mail = mail;
    }

    

    
     public Utilisateur(String nom, String prenom, String cin, String adresse, String ville, int tel, String mail, int age, int role, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.ville = ville;
        this.tel = tel;
        this.mail = mail;
        this.age = age;
        this.role = role;
        this.login = login;
        this.motDePasse = motDePasse;
    }
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }
    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getTel() {
        return tel;
    }
    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
     
    
    @Override
    public String toString() {
        return "Utilisateur{" + "idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", adresse=" + adresse + ", ville=" + ville + ", tel=" + tel + ", mail=" + mail + ", age=" + age + ", role=" + role + ", login=" + login + ", motDePasse=" + motDePasse + '}';
    }

  
    
    
    
}
