package sadba.lab.com.senprof.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserResponse  extends RealmObject{

    private String code;
    private String message;
    @PrimaryKey
    private String ien;
    private String prenom;
    private String nom;
    private String type_ien;
    private String avatar;


    public UserResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIen() {
        return ien;
    }

    public void setIen(String ien) {
        this.ien = ien;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType_ien() {
        return type_ien;
    }

    public void setType_ien(String type_ien) {
        this.type_ien = type_ien;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
