package br.com.pdv.domain.nfe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.pdv.domain.Cliente;
import br.com.pdv.domain.Empresa;


@Entity
public class NfeCabecalho implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;    
    @Column(name = "CODIGO_NUMERICO")
    private String codigoNumerico;
    @Column(name = "NATUREZA_OPERACAO")
    private String naturezaOperacao;    
    @Column(name = "SERIE")
    private String serie;
    @Column(name = "NUMERO")
    private String numero;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_HORA_EMISSAO")
    private Date dataHoraEmissao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_HORA_ENTRADA_SAIDA")
    private Date dataHoraEntradaSaida;
    @Column(name = "TIPO_OPERACAO")
    private Integer tipoOperacao;    
    @Column(name = "FORMATO_IMPRESSAO_DANFE")
    private Integer formatoImpressaoDanfe;
    @Column(name = "TIPO_EMISSAO")
    private Integer tipoEmissao;
    @Column(name = "CHAVE_ACESSO")
    private String chaveAcesso;
    @Column(name = "DIGITO_CHAVE_ACESSO")
    private String digitoChaveAcesso;
    @Column(name = "AMBIENTE")
    private Integer ambiente;
    @Column(name = "FINALIDADE_EMISSAO")
    private Integer finalidadeEmissao;
    @Column(name = "CONSUMIDOR_OPERACAO")
    private Integer consumidorOperacao;
    @Column(name = "CONSUMIDOR_PRESENCA")
    private Integer consumidorPresenca;
    @Column(name = "PROCESSO_EMISSAO")
    private Integer processoEmissao;
    @Column(name = "VERSAO_PROCESSO_EMISSAO")
    private String versaoProcessoEmissao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_ENTRADA_CONTINGENCIA")
    private Date dataEntradaContingencia;
    @Column(name = "JUSTIFICATIVA_CONTINGENCIA")
    private String justificativaContingencia;
    @Column(name = "BASE_CALCULO_ICMS")
    private BigDecimal baseCalculoIcms;
    @Column(name = "VALOR_ICMS")
    private BigDecimal valorIcms;    
    @Column(name = "BASE_CALCULO_ICMS_ST")
    private BigDecimal baseCalculoIcmsSt;
    @Column(name = "VALOR_ICMS_ST")
    private BigDecimal valorIcmsSt;
    @Column(name = "VALOR_TOTAL_PRODUTOS")
    private BigDecimal valorTotalProdutos;    
    @Column(name = "VALOR_IPI")
    private BigDecimal valorIpi;
    @Column(name = "VALOR_PIS")
    private BigDecimal valorPis;
    @Column(name = "VALOR_COFINS")
    private BigDecimal valorCofins;
    @Column(name = "VALOR_DESPESAS_ACESSORIAS")
    private BigDecimal valorDespesasAcessorias;
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Column(name = "VALOR_SERVICOS")
    private BigDecimal valorServicos; 
    @Column(name = "STATUS_NOTA")
    private Integer statusNota;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "codigo")
    @ManyToOne
    private Cliente cliente;    
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne
    private Empresa empresa;
    @OneToMany(mappedBy="nfeCabecalho", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("numeroItem ASC")
    private List<NfeDetalhe> listaNfeDetalhe;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigoNumerico() {
		return codigoNumerico;
	}
	public void setCodigoNumerico(String codigoNumerico) {
		this.codigoNumerico = codigoNumerico;
	}
	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}
	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getDataHoraEmissao() {
		return dataHoraEmissao;
	}
	public void setDataHoraEmissao(Date dataHoraEmissao) {
		this.dataHoraEmissao = dataHoraEmissao;
	}
	public Date getDataHoraEntradaSaida() {
		return dataHoraEntradaSaida;
	}
	public void setDataHoraEntradaSaida(Date dataHoraEntradaSaida) {
		this.dataHoraEntradaSaida = dataHoraEntradaSaida;
	}
	public Integer getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(Integer tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public Integer getFormatoImpressaoDanfe() {
		return formatoImpressaoDanfe;
	}
	public void setFormatoImpressaoDanfe(Integer formatoImpressaoDanfe) {
		this.formatoImpressaoDanfe = formatoImpressaoDanfe;
	}
	public Integer getTipoEmissao() {
		return tipoEmissao;
	}
	public void setTipoEmissao(Integer tipoEmissao) {
		this.tipoEmissao = tipoEmissao;
	}
	public String getChaveAcesso() {
		return chaveAcesso;
	}
	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}
	public String getDigitoChaveAcesso() {
		return digitoChaveAcesso;
	}
	public void setDigitoChaveAcesso(String digitoChaveAcesso) {
		this.digitoChaveAcesso = digitoChaveAcesso;
	}
	public Integer getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(Integer ambiente) {
		this.ambiente = ambiente;
	}
	public Integer getFinalidadeEmissao() {
		return finalidadeEmissao;
	}
	public void setFinalidadeEmissao(Integer finalidadeEmissao) {
		this.finalidadeEmissao = finalidadeEmissao;
	}
	public Integer getConsumidorOperacao() {
		return consumidorOperacao;
	}
	public void setConsumidorOperacao(Integer consumidorOperacao) {
		this.consumidorOperacao = consumidorOperacao;
	}
	public Integer getConsumidorPresenca() {
		return consumidorPresenca;
	}
	public void setConsumidorPresenca(Integer consumidorPresenca) {
		this.consumidorPresenca = consumidorPresenca;
	}
	public Integer getProcessoEmissao() {
		return processoEmissao;
	}
	public void setProcessoEmissao(Integer processoEmissao) {
		this.processoEmissao = processoEmissao;
	}
	public String getVersaoProcessoEmissao() {
		return versaoProcessoEmissao;
	}
	public void setVersaoProcessoEmissao(String versaoProcessoEmissao) {
		this.versaoProcessoEmissao = versaoProcessoEmissao;
	}
	public Date getDataEntradaContingencia() {
		return dataEntradaContingencia;
	}
	public void setDataEntradaContingencia(Date dataEntradaContingencia) {
		this.dataEntradaContingencia = dataEntradaContingencia;
	}
	public String getJustificativaContingencia() {
		return justificativaContingencia;
	}
	public void setJustificativaContingencia(String justificativaContingencia) {
		this.justificativaContingencia = justificativaContingencia;
	}
	public BigDecimal getBaseCalculoIcms() {
		return baseCalculoIcms;
	}
	public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) {
		this.baseCalculoIcms = baseCalculoIcms;
	}
	public BigDecimal getValorIcms() {
		return valorIcms;
	}
	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}
	public BigDecimal getBaseCalculoIcmsSt() {
		return baseCalculoIcmsSt;
	}
	public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
		this.baseCalculoIcmsSt = baseCalculoIcmsSt;
	}
	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}
	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = valorIcmsSt;
	}
	public BigDecimal getValorTotalProdutos() {
		return valorTotalProdutos;
	}
	public void setValorTotalProdutos(BigDecimal valorTotalProdutos) {
		this.valorTotalProdutos = valorTotalProdutos;
	}
	public BigDecimal getValorIpi() {
		return valorIpi;
	}
	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}
	public BigDecimal getValorPis() {
		return valorPis;
	}
	public void setValorPis(BigDecimal valorPis) {
		this.valorPis = valorPis;
	}
	public BigDecimal getValorCofins() {
		return valorCofins;
	}
	public void setValorCofins(BigDecimal valorCofins) {
		this.valorCofins = valorCofins;
	}
	public BigDecimal getValorDespesasAcessorias() {
		return valorDespesasAcessorias;
	}
	public void setValorDespesasAcessorias(BigDecimal valorDespesasAcessorias) {
		this.valorDespesasAcessorias = valorDespesasAcessorias;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValorServicos() {
		return valorServicos;
	}
	public void setValorServicos(BigDecimal valorServicos) {
		this.valorServicos = valorServicos;
	}
	public Integer getStatusNota() {
		return statusNota;
	}
	public void setStatusNota(Integer statusNota) {
		this.statusNota = statusNota;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<NfeDetalhe> getListaNfeDetalhe() {
		return listaNfeDetalhe;
	}
	public void setListaNfeDetalhe(List<NfeDetalhe> listaNfeDetalhe) {
		this.listaNfeDetalhe = listaNfeDetalhe;
	}
	
    
	@Override
    public String toString() {
        return "br.com.pdv.domain.nfe.NfeCabecalho[id=" + id + "]";
    }

}
