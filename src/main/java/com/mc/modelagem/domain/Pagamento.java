package com.mc.modelagem.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mc.modelagem.domain.enums.EstadoPagamento;
import com.mc.modelagem.domain.enums.TipoPagamento;

@Entity
public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer estadoPagamento;
	private Integer tipoPagamento;
	private Date dataVencimento;
	private Date dataPagamento;
	private Integer numeroParcelas;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;	
	
	public Pagamento() {
		
	}

	

	public Pagamento(Integer id, EstadoPagamento estadoPagamento, TipoPagamento tipoPagamento, Date dataVencimento,
			Date dataPagamento, Integer numeroParcelas, Pedido pedido) {
		super();
		this.id = id;
		this.estadoPagamento = estadoPagamento.getCodigo();
		this.tipoPagamento = tipoPagamento.getCodigo();
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.numeroParcelas = numeroParcelas;
		this.pedido = pedido;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento.getCodigo();
	}

	public TipoPagamento getTipoPagamento() {
		return TipoPagamento.toEnum(tipoPagamento);
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento.getCodigo();
		
		if(tipoPagamento == TipoPagamento.BOLETO) {
			this.numeroParcelas = 1;
		}
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}



	public Pedido getPedido() {
		return pedido;
	}



	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
