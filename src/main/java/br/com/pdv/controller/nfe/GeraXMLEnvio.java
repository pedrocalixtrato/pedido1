package br.com.pdv.controller.nfe;

import java.io.StringWriter;
import java.security.KeyStore;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import br.com.pdv.domain.Cliente;
import br.com.pdv.domain.Empresa;
import br.com.pdv.domain.nfe.NfeCabecalho;
import br.com.pdv.domain.nfe.NfeDetalhe;
import br.com.pdv.util.nfe.Biblioteca;
import br.inf.portalfiscal.nfe.envinfe.ObjectFactory;
import br.inf.portalfiscal.nfe.envinfe.TEnderEmi;
import br.inf.portalfiscal.nfe.envinfe.TEndereco;
import br.inf.portalfiscal.nfe.envinfe.TEnviNFe;
import br.inf.portalfiscal.nfe.envinfe.TNFe;
import br.inf.portalfiscal.nfe.envinfe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;
import br.inf.portalfiscal.nfe.envinfe.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.inf.portalfiscal.nfe.envinfe.TUf;
import br.inf.portalfiscal.nfe.envinfe.TUfEmi;

public class GeraXMLEnvio {

	public String gerarXmlEnvio(Empresa empresa, NfeCabecalho nfeCabecalho, String alias, KeyStore ks, char[] senha)
			throws Exception {
		Cliente destinatario = nfeCabecalho.getCliente();		
		List<NfeDetalhe> listaNfeDetalhe = nfeCabecalho.getListaNfeDetalhe();

		SimpleDateFormat formatoDataHora = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		DecimalFormatSymbols simboloDecimal = DecimalFormatSymbols.getInstance();
		simboloDecimal.setDecimalSeparator('.');
		DecimalFormat formatoQuantidade = new DecimalFormat("0.0000", simboloDecimal);
		DecimalFormat formatoValor = new DecimalFormat("0.00", simboloDecimal);

		ObjectFactory factory = new ObjectFactory();

		TNFe nfe = factory.createTNFe();

		TNFe.InfNFe infNfe = new TNFe.InfNFe();
		infNfe.setId("NFe" + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso());
		infNfe.setVersao("3.10");
		nfe.setInfNFe(infNfe);

		TNFe.InfNFe.Ide ide = new TNFe.InfNFe.Ide();
		nfe.getInfNFe().setIde(ide);

		// cabecalho

		ide.setCUF(empresa.getCodigoIbgeUf().toString());
		ide.setCNF(nfeCabecalho.getCodigoNumerico());
		ide.setNatOp(nfeCabecalho.getNaturezaOperacao());
		ide.setIndPag("2");
		ide.setMod("55");
		ide.setSerie(Integer.valueOf(nfeCabecalho.getSerie()).toString());
		ide.setNNF(Integer.valueOf(nfeCabecalho.getNumero()).toString());
		ide.setDhEmi(formatoDataHora.format(nfeCabecalho.getDataHoraEmissao()));
		ide.setDhSaiEnt(formatoDataHora.format(nfeCabecalho.getDataHoraEntradaSaida()));
		ide.setTpNF(String.valueOf(nfeCabecalho.getTipoOperacao()));
		ide.setCMunFG(empresa.getMunicipioIbge().toString());
		ide.setTpImp(String.valueOf(nfeCabecalho.getFormatoImpressaoDanfe()));
		ide.setTpEmis(String.valueOf(nfeCabecalho.getTipoEmissao()));
		ide.setVerProc(nfeCabecalho.getVersaoProcessoEmissao());
		ide.setTpAmb(String.valueOf(nfeCabecalho.getAmbiente()));
		ide.setFinNFe(String.valueOf(nfeCabecalho.getFinalidadeEmissao()));
		ide.setProcEmi(String.valueOf(nfeCabecalho.getProcessoEmissao()));
		ide.setCDV(nfeCabecalho.getDigitoChaveAcesso());
		ide.setIdDest("2");
		ide.setIndFinal(String.valueOf(nfeCabecalho.getConsumidorOperacao()));
		ide.setIndPres(String.valueOf(nfeCabecalho.getConsumidorPresenca()));

		// NFe CabeÃ§alho -- Totais
		TNFe.InfNFe.Total total = new TNFe.InfNFe.Total();
		nfe.getInfNFe().setTotal(total);

		TNFe.InfNFe.Total.ICMSTot icmsTot = new TNFe.InfNFe.Total.ICMSTot();
		nfe.getInfNFe().getTotal().setICMSTot(icmsTot);

		total.getICMSTot().setVBC(formatoValor.format(nfeCabecalho.getBaseCalculoIcms()));
		total.getICMSTot().setVICMS(formatoValor.format(nfeCabecalho.getValorIcms()));
		total.getICMSTot().setVBCST(formatoValor.format(nfeCabecalho.getBaseCalculoIcmsSt()));
		total.getICMSTot().setVST(formatoValor.format(nfeCabecalho.getValorIcmsSt()));
		total.getICMSTot().setVProd(formatoValor.format(nfeCabecalho.getValorTotalProdutos()));
		total.getICMSTot().setVFrete("0.00");
		total.getICMSTot().setVSeg("0.00");
		total.getICMSTot().setVDesc("0.00");
		total.getICMSTot().setVII("0.00");
		total.getICMSTot().setVIPI("0.00");
		total.getICMSTot().setVPIS("0.00");
		total.getICMSTot().setVCOFINS("0.00");
		total.getICMSTot().setVOutro("0.00");
		total.getICMSTot().setVNF(formatoValor.format(nfeCabecalho.getValorTotal()));
		total.getICMSTot().setVICMSDeson("0.00");

		// emitente
		TNFe.InfNFe.Emit emit = new TNFe.InfNFe.Emit();
		nfe.getInfNFe().setEmit(emit);
		TEnderEmi enderecoEmi = new TEnderEmi();
		nfe.getInfNFe().getEmit().setEnderEmit(enderecoEmi);

		emit.setCNPJ(empresa.getCnpj());
		emit.setXNome(empresa.getRazaoSocial());
		emit.setXFant(empresa.getNomeFantasia());
		emit.getEnderEmit().setXLgr(empresa.getLogradouro());
		emit.getEnderEmit().setNro(empresa.getNumero());
		emit.getEnderEmit().setXCpl(empresa.getComplemento());
		emit.getEnderEmit().setXBairro(empresa.getBairro());
		emit.getEnderEmit().setCMun(empresa.getCodigoIbgeCidade().toString());
		emit.getEnderEmit().setXMun(empresa.getCidade());
		emit.getEnderEmit().setUF(TUfEmi.fromValue(empresa.getUf()));
		emit.getEnderEmit().setCEP(empresa.getCep());
		emit.getEnderEmit().setCPais("1058");
		emit.getEnderEmit().setXPais("BRASIL");
		emit.getEnderEmit().setFone(empresa.getFone());
		emit.setIE(empresa.getInscricaoEstadual());
		emit.setIM(empresa.getInscricaoMunicipal());
		emit.setCRT(empresa.getCrt().toString());

		// destinatario
		TNFe.InfNFe.Dest dest = new TNFe.InfNFe.Dest();
		nfe.getInfNFe().setDest(dest);
		TEndereco enderecoDest = new TEndereco();
		nfe.getInfNFe().getDest().setEnderDest(enderecoDest);

		dest.setCPF(destinatario.getCpf());

		if (nfeCabecalho.getAmbiente().intValue() == 2) {
			dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		} else {
			dest.setXNome(destinatario.getNome());
		}
		dest.getEnderDest().setXLgr(destinatario.getPessoa().getRua());
		dest.getEnderDest().setNro(destinatario.getPessoa().getNumero());
		dest.getEnderDest().setXCpl(destinatario.getPessoa().getComplemento());
		dest.getEnderDest().setXBairro(destinatario.getPessoa().getBairro());
		dest.getEnderDest().setCMun("5208707");
		dest.getEnderDest().setXMun(destinatario.getPessoa().getCidade());
		dest.getEnderDest().setUF(TUf.fromValue(destinatario.getPessoa().getEstado()));
		dest.getEnderDest().setCEP(destinatario.getPessoa().getCep());
		dest.getEnderDest().setCPais("1058");
		dest.getEnderDest().setXPais("BRASIL");

		dest.setIndIEDest("2");
		// dest.setIE("ISENTO");

		// Transporte
		TNFe.InfNFe.Transp transp = new TNFe.InfNFe.Transp();
		infNfe.setTransp(transp);

		transp.setModFrete("1");

		// detalhes
		List<TNFe.InfNFe.Det> listaDet = nfe.getInfNFe().getDet();
		TNFe.InfNFe.Det det;
		for (NfeDetalhe nfeDetalhe : listaNfeDetalhe) {

			det = new TNFe.InfNFe.Det();
			TNFe.InfNFe.Det.Prod prod = new TNFe.InfNFe.Det.Prod();
			det.setNItem(nfeDetalhe.getNumeroItem().toString());
			det.setProd(prod);

			det.getProd().setCProd(nfeDetalhe.getGtin());
			det.getProd().setCEAN(nfeDetalhe.getGtin());
			det.getProd().setXProd(nfeDetalhe.getNomeProduto());
			det.getProd().setNCM(nfeDetalhe.getNcm());
			// det.getProd().setEXTIPI(null);
			det.getProd().setCFOP(nfeDetalhe.getCfop().toString());
			det.getProd().setUCom(nfeDetalhe.getUnidadeComercial());
			det.getProd().setQCom(formatoQuantidade.format(nfeDetalhe.getQuantidadeComercial()));
			det.getProd().setVUnCom(nfeDetalhe.getValorUnitarioComercial().toPlainString());
			det.getProd().setVProd(formatoValor.format(nfeDetalhe.getValorTotal()));
			det.getProd().setUTrib(nfeDetalhe.getUnidadeTributavel());
			det.getProd().setQTrib(formatoQuantidade.format(nfeDetalhe.getQuantidadeTributavel()));
			det.getProd().setVUnTrib(nfeDetalhe.getValorUnitarioTributavel().toPlainString());
			det.getProd().setIndTot("1");

			// Detalhes -- Impostos
			TNFe.InfNFe.Det.Imposto imposto = new TNFe.InfNFe.Det.Imposto();
			det.setImposto(imposto);

			TNFe.InfNFe.Det.Imposto.ICMS icms = new TNFe.InfNFe.Det.Imposto.ICMS();
			det.getImposto().getContent().add(factory.createTNFeInfNFeDetImpostoICMS(icms));

			if (empresa.getCrt().equals("1")) {// Simples Nacional
				String csosn = nfeDetalhe.getCsosn();

				if (csosn.equals("101")) {
					TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101 icms101 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101();
					icms.setICMSSN101(icms101);

					icms101.setCSOSN(csosn);
					icms101.setOrig(String.valueOf(nfeDetalhe.getOrigemMercadoria()));
				}
				if (csosn.equals("102") || csosn.equals("103") || csosn.equals("300") || csosn.equals("400")) {
					TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102 icms102 = new TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102();
					icms.setICMSSN102(icms102);

					icms102.setCSOSN(csosn);
					icms102.setOrig(String.valueOf(nfeDetalhe.getOrigemMercadoria()));
					total.getICMSTot().setVPIS(formatoValor.format(nfeCabecalho.getValorPis()));
					total.getICMSTot().setVCOFINS(formatoValor.format(nfeCabecalho.getValorCofins()));

					// Testando adicionar tags(FUNCIONOU!)
					TNFe.InfNFe.Det.Imposto.PIS pis = new TNFe.InfNFe.Det.Imposto.PIS();
					det.getImposto().getContent().add(factory.createTNFeInfNFeDetImpostoPIS(pis));

					PISOutr pisOutr = new PISOutr();
					pis.setPISOutr(pisOutr);

					pis.getPISOutr().setCST("99");
					pis.getPISOutr().setVBC("0.00");
					pis.getPISOutr().setPPIS("0.00");
					pis.getPISOutr().setVPIS("0.00");

					// Testando adicionar tags(FUNCIONOU)

					TNFe.InfNFe.Det.Imposto.COFINS cofins = new TNFe.InfNFe.Det.Imposto.COFINS();
					imposto.getContent().add(factory.createTNFeInfNFeDetImpostoCOFINS(cofins));

					COFINSOutr cofinsOutr = new COFINSOutr();
					cofins.setCOFINSOutr(cofinsOutr);

					cofins.getCOFINSOutr().setCST("99");
					cofins.getCOFINSOutr().setVBC("0.00");
					cofins.getCOFINSOutr().setPCOFINS("0.00");
					cofins.getCOFINSOutr().setVCOFINS("0.00");

				}

			}

				listaDet.add(det);
		}
		
				TEnviNFe enviNfe = new TEnviNFe();
				enviNfe.setIdLote("1");
				enviNfe.setVersao("3.10");
				enviNfe.setIndSinc("0");
				enviNfe.getNFe().add(nfe);
		
				JAXBContext jc = JAXBContext.newInstance("br.inf.portalfiscal.nfe.envinfe");
				Marshaller marshaller = jc.createMarshaller();
		
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
				JAXBElement<TEnviNFe> element = factory.createEnviNFe(enviNfe);
		
				StringWriter writer = new StringWriter();
				marshaller.marshal(element, writer);
		
				String xmlEnviNfe = writer.toString();
				xmlEnviNfe = xmlEnviNfe.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
				xmlEnviNfe = xmlEnviNfe.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");
		
				return Biblioteca.assinaXML(xmlEnviNfe, alias, ks, senha,
						"#NFe" + nfeCabecalho.getChaveAcesso() + nfeCabecalho.getDigitoChaveAcesso(), "NFe", "infNFe", "Id");

	}

}
