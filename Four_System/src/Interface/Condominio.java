/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;

/**
 *
 * @author kimruan
 */
@Entity
@Table(name = "condominio", catalog = "postgres", schema = "banco_four")
@NamedQueries({
    @NamedQuery(name = "Condominio.findAll", query = "SELECT c FROM Condominio c"),
    @NamedQuery(name = "Condominio.findById", query = "SELECT c FROM Condominio c WHERE c.id = :id"),
    @NamedQuery(name = "Condominio.findByNome", query = "SELECT c FROM Condominio c WHERE c.nome = :nome"),
    @NamedQuery(name = "Condominio.findByIdSindico", query = "SELECT c FROM Condominio c WHERE c.idSindico = :idSindico"),
    @NamedQuery(name = "Condominio.findByRua", query = "SELECT c FROM Condominio c WHERE c.rua = :rua"),
    @NamedQuery(name = "Condominio.findByBairro", query = "SELECT c FROM Condominio c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Condominio.findByCidade", query = "SELECT c FROM Condominio c WHERE c.cidade = :cidade")})
public class Condominio implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

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

    public Condominio() {
    }

    public Condominio(Integer id) {
        this.id = id;
    }

    public Condominio(Integer id, String nome, int idSindico, String rua, String bairro, String cidade) {
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
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public int getIdSindico() {
        return idSindico;
    }

    public void setIdSindico(int idSindico) {
        int oldIdSindico = this.idSindico;
        this.idSindico = idSindico;
        changeSupport.firePropertyChange("idSindico", oldIdSindico, idSindico);
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        String oldRua = this.rua;
        this.rua = rua;
        changeSupport.firePropertyChange("rua", oldRua, rua);
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        String oldBairro = this.bairro;
        this.bairro = bairro;
        changeSupport.firePropertyChange("bairro", oldBairro, bairro);
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        String oldCidade = this.cidade;
        this.cidade = cidade;
        changeSupport.firePropertyChange("cidade", oldCidade, cidade);
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
        if (!(object instanceof Condominio)) {
            return false;
        }
        Condominio other = (Condominio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Interface.Condominio[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
