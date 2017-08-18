/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kimruan
 */
@Entity
@Table(name = "condominio", catalog = "postgres", schema = "banco_four")
@NamedQueries({
    @NamedQuery(name = "Condominio_1.findAll", query = "SELECT c FROM Condominio_1 c"),
    @NamedQuery(name = "Condominio_1.findById", query = "SELECT c FROM Condominio_1 c WHERE c.id = :id"),
    @NamedQuery(name = "Condominio_1.findByNome", query = "SELECT c FROM Condominio_1 c WHERE c.nome = :nome"),
    @NamedQuery(name = "Condominio_1.findByIdSindico", query = "SELECT c FROM Condominio_1 c WHERE c.idSindico = :idSindico"),
    @NamedQuery(name = "Condominio_1.findByRua", query = "SELECT c FROM Condominio_1 c WHERE c.rua = :rua"),
    @NamedQuery(name = "Condominio_1.findByBairro", query = "SELECT c FROM Condominio_1 c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Condominio_1.findByCidade", query = "SELECT c FROM Condominio_1 c WHERE c.cidade = :cidade")})
public class Condominio_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "id_sindico")
    private int idSindico;
    @Basic(optional = false)
    @Column(name = "rua")
    private String rua;
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;

    public Condominio_1() {
    }

    public Condominio_1(Integer id) {
        this.id = id;
    }

    public Condominio_1(Integer id, String nome, int idSindico, String rua, String bairro, String cidade) {
        this.id = id;
        this.nome = nome;
        this.idSindico = idSindico;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdSindico() {
        return idSindico;
    }

    public void setIdSindico(int idSindico) {
        this.idSindico = idSindico;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condominio_1)) {
            return false;
        }
        Condominio_1 other = (Condominio_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Interface.Condominio_1[ id=" + id + " ]";
    }
    
}
