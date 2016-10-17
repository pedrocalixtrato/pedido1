package br.com.pdv.domain.nfe;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.pdv.domain.Produto;


@Entity
public class NfeDetalhe implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9211545306936427119L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_ITEM")
    private Integer numeroItem;
    @Column(name = "CODIGO_PRODUTO")
    private String codigoProduto;
    @Column(name = "GTIN")
    private String gtin;
    @Column(name = "NOME_PRODUTO")
    private String nomeProduto;
    @Column(name = "NCM")
    private String ncm;    
    @Column(name = "CFOP")
    private Integer cfop;
    @Column(name = "UNIDADE_COMERCIAL")
    private String unidadeComercial;
    @Column(name = "QUANTIDADE_COMERCIAL")
    private BigDecimal quantidadeComercial;
    @Column(name = "VALOR_UNITARIO_COMERCIAL")
    private BigDecimal valorUnitarioComercial;
    @Column(name = "VALOR_BRUTO_PRODUTO")
    private BigDecimal valorBrutoProduto;    
    @Column(name = "UNIDADE_TRIBUTAVEL")
    private String unidadeTributavel;
    @Column(name = "QUANTIDADE_TRIBUTAVEL")
    private BigDecimal quantidadeTributavel;
    @Column(name = "VALOR_UNITARIO_TRIBUTAVEL")
    private BigDecimal valorUnitarioTributavel;
    @Column(name = "VALOR_FRETE")
    private BigDecimal valorFrete;    
    @Column(name = "VALOR_DESCONTO")
    private BigDecimal valorDesconto;
    @Column(name = "VALOR_OUTRAS_DESPESAS")
    private BigDecimal valorOutrasDespesas;   
    @Column(name = "VALOR_SUBTOTAL")
    private BigDecimal valorSubtotal;
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;    
    @Column(name = "INFORMACOES_ADICIONAIS")
    private String informacoesAdicionais;    
    @Column(name = "VALOR_TOTAL_TRIBUTOS")
    private BigDecimal valorTotalTributos;
    @Column(name = "PERCENTUAL_DEVOLVIDO")
    private BigDecimal percentualDevolvido;
    @Column(name = "VALOR_IPI_DEVOLVIDO")
    private BigDecimal valorIpiDevolvido;
    @JoinColumn(name = "ID_NFE_CABECALHO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private NfeCabecalho nfeCabecalho;
    @JoinColumn(name = "ID_PRODUTO", referencedColumnName = "codigo")
    @ManyToOne
    private Produto produto;
    @Column(name = "CSOSN")
    private String csosn;
    @Column(name = "ORIGEM_MERCADORIA")
    private Integer origemMercadoria;
    
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroItem() {
		return numeroItem;
	}
	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = numeroItem;
	}
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public String getGtin() {
		return gtin;
	}
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getNcm() {
		return ncm;
	}
	public void setNcm(String ncm) {
		this.ncm = ncm;
	}
	public Integer getCfop() {
		return cfop;
	}
	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}
	public String getUnidadeComercial() {
		return unidadeComercial;
	}
	public void setUnidadeComercial(String unidadeComercial) {
		this.unidadeComercial = unidadeComercial;
	}
	public BigDecimal getQuantidadeComercial() {
		return quantidadeComercial;
	}
	public void setQuantidadeComercial(BigDecimal quantidadeComercial) {
		this.quantidadeComercial = quantidadeComercial;
	}
	public BigDecimal getValorUnitarioComercial() {
		return valorUnitarioComercial;
	}
	public void setValorUnitarioComercial(BigDecimal valorUnitarioComercial) {
		this.valorUnitarioComercial = valorUnitarioComercial;
	}
	public BigDecimal getValorBrutoProduto() {
		return valorBrutoProduto;
	}
	public void setValorBrutoProduto(BigDecimal valorBrutoProduto) {
		this.valorBrutoProduto = valorBrutoProduto;
	}
	public String getUnidadeTributavel() {
		return unidadeTributavel;
	}
	public void setUnidadeTributavel(String unidadeTributavel) {
		this.unidadeTributavel = unidadeTributavel;
	}
	public BigDecimal getQuantidadeTributavel() {
		return quantidadeTributavel;
	}
	public void setQuantidadeTributavel(BigDecimal quantidadeTributavel) {
		this.quantidadeTributavel = quantidadeTributavel;
	}
	public BigDecimal getValorUnitarioTributavel() {
		return valorUnitarioTributavel;
	}
	public void setValorUnitarioTributavel(BigDecimal valorUnitarioTributavel) {
		this.valorUnitarioTributavel = valorUnitarioTributavel;
	}
	public BigDecimal getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public BigDecimal getValorOutrasDespesas() {
		return valorOutrasDespesas;
	}
	public void setValorOutrasDespesas(BigDecimal valorOutrasDespesas) {
		this.valorOutrasDespesas = valorOutrasDespesas;
	}
	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}
	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}
	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}
	public BigDecimal getValorTotalTributos() {
		return valorTotalTributos;
	}
	public void setValorTotalTributos(BigDecimal valorTotalTributos) {
		this.valorTotalTributos = valorTotalTributos;
	}
	public BigDecimal getPercentualDevolvido() {
		return percentualDevolvido;
	}
	public void setPercentualDevolvido(BigDecimal percentualDevolvido) {
		this.percentualDevolvido = percentualDevolvido;
	}
	public BigDecimal getValorIpiDevolvido() {
		return valorIpiDevolvido;
	}
	public void setValorIpiDevolvido(BigDecimal valorIpiDevolvido) {
		this.valorIpiDevolvido = valorIpiDevolvido;
	}
	public NfeCabecalho getNfeCabecalho() {
		return nfeCabecalho;
	}
	public void setNfeCabecalho(NfeCabecalho nfeCabecalho) {
		this.nfeCabecalho = nfeCabecalho;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String getCsosn() {
		return csosn;
	}
	public void setCsosn(String csosn) {
		this.csosn = csosn;
	}
	public Integer getOrigemMercadoria() {
		return origemMercadoria;
	}
	public void setOrigemMercadoria(Integer origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}
    
    
	@Override
    public String toString() {
        return "br.com.pdv.domain.nfe.NfeDetalhe[id=" + id + "]";
    }
}
