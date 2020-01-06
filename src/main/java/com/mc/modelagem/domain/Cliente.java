package com.mc.modelagem.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mc.modelagem.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O nome não pode ser vazio.")
	@Length(min = 5,max = 120, message = "O nome tem que ter entre 5 e 120 caracteres.")
	private String nome;
	
	@NotEmpty(message = "O e-mail não pode ser vazio.")
	@Email(message = "E-mail inválido")
	private String email;
	
	private String cpfcnpj;
	private Integer tipo;

	@OneToMany(mappedBy="cliente")
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@ElementCollection
	@CollectionTable(name="telefone")
	private Set<String> telefones =new HashSet<String>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	public Cliente() {
		
	}

	public Cliente(Integer id, String nome, String email, String cpfcnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfcnpj = cpfcnpj;
		this.tipo = tipo.getCodigo();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	 

}
