package sadba.lab.com.senprof.model;

import java.util.List;

public class Lecon {
    private int id;
    private String nom;
    private String contenu;
    private int matiere_id;
    private int chapitre_id;
    private List<Video> videos;

    public Lecon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getMatiere_id() {
        return matiere_id;
    }

    public void setMatiere_id(int matiere_id) {
        this.matiere_id = matiere_id;
    }

    public int getChapitre_id() {
        return chapitre_id;
    }

    public void setChapitre_id(int chapitre_id) {
        this.chapitre_id = chapitre_id;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
