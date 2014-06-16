package br.com.fazendola.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
@NamedQuery(name = "Usuario.buscarUsuario", query = "SELECT u FROM Usuario u WHERE u.email = :email")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String BUSCAR_USUARIO = "Usuario.buscarUsuario";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String matricula;
	private String grupo;
	private String senha;
	@Enumerated(EnumType.STRING)
	private Papel papel;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}
	
	public boolean eProfessor() {
		return Papel.PROFESSOR.equals(papel);
	}
	
	public boolean eAluno() {
		return Papel.ALUNO.equals(papel);
	}

	@Override
	public int hashCode() {
		return (int) getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario usuario = (Usuario) obj;
			return usuario.getId() == id;
		}
		
		return false;
	}

	public enum Papel {
		PROFESSOR, ALUNO
	}
}
