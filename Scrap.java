package botFAAP;

import com.jaunt.*;
import java.util.Calendar;

public class Scrap {
	public static String resposta = "";

	public static String Pesquisa(String local, String mes) {
		int ano, intMes, calcMes;
		String local2="";
		Calendar data = Calendar.getInstance();
		ano = data.get(Calendar.YEAR);
		intMes = data.get(Calendar.MONTH)+1;
		calcMes=Integer.parseInt(mes)+intMes;
		if (calcMes>12) {
			ano = ano+1;
			mes ="01";}
		else if (calcMes>=10 && calcMes<=12) {
			mes =Integer.toString(calcMes);}
		else if (calcMes<=9) {
			mes ="0"+Integer.toString(calcMes);}
		if(local=="rp") {local = "ribeirao_preto";
						local2 = "Ribeir�o Preto";}
		if(local=="sj") {local = "sao_jose_dos_campos";
						local2 = "S�o Jos� dos Campos";}
		if(local=="sp") {local = "sao_paulo";
						local2 = "S�o Paulo";}
		
		try {
			UserAgent userAgent = new UserAgent();
			userAgent.settings.autoSaveAsHTML = true;
			//userAgent.visit("http://acontece.faap.br/eventos/acontece/"+local);
			//http://acontece.faap.br/eventos/acontece/sao_jose_dos_campos/eventos/2018-04
			userAgent.visit("http://acontece.faap.br/eventos/acontece/"+local+"/eventos/"+ano+"-"+mes);
			Elements horas = userAgent.doc.findEvery("<span class=\"horario\">");
			Elements nomes = userAgent.doc.findEvery("<span class=\"organizador\">");
			Elements titulos = userAgent.doc.findEvery("<p class=\"tit-descricao\">");
			Elements dias = userAgent.doc.findEvery("<span class=\"dia\">");
			for (int contador = 0; contador < horas.size(); contador++) {
				resposta +=("\nT�tulo: "
						+ titulos.getElement(contador).getText().trim().replace("&#231;", "�").replace("&#245;", "�")
								.replace("&#186;", "�").replace("&#202;", "�").replace("&#195;", "�")
								.replace("&quot;", "'").replace("&#218;", "�").replace("&#244;", "�")
								.replace("&#199;", "�").replace("&#192;", "�").replace("&#193;", "�")
								.replace("&#233;", "�").replace("&#227;", "�").replace("&#225;", "�")
								.replace("&#250;", "�").replace("&#227;", "�").replace("&#237;", "�")
								.replace("&#243;", "�").replace("&#224;", "�").replace("&#234;", "�")
								.replace("&#170;", "�").replace("&#226;", "�").replace("&#245;", "�")
								.replace("&#211;", "�").replace("&#39;", "'").replace("&#201;", "�")
						+ "\nPalestrante: "
						+ nomes.getElement(contador).getText().trim().replace("&#231;", "�").replace("&#245;", "�")
								.replace("&#186;", "�").replace("&#202;", "�").replace("&#195;", "�")
								.replace("&quot;", "'").replace("&#218;", "�").replace("&#244;", "�")
								.replace("&#199;", "�").replace("&#192;", "�").replace("&#193;", "�")
								.replace("&#233;", "�").replace("&#227;", "�").replace("&#225;", "�")
								.replace("&#250;", "�").replace("&#227;", "�").replace("&#237;", "�")
								.replace("&#243;", "�").replace("&#224;", "�").replace("&#234;", "�")
								.replace("&#170;", "�").replace("&#226;", "�").replace("&#245;", "�")
								.replace("&#211;", "�").replace("&#39;", "'").replace("&#201;", "�")
						
						+ "\nHora: " + horas.getElement(contador).getText().trim() + "\nDia:"
						+ dias.getElement(contador).getText().trim() +" / "+mes
						+"\nInscri��o: http://acontece.faap.br/eventos/acontece/"+local+"/eventos/"+ano+"-"+mes+"\n"
						+ "\n-----------------------------------------------\n");			
				
			}
			resposta +=("\nFinal da lista de \n" + local2 + "\nno m�s " + mes +" - " + ano 
						+ "\n\n Voc� pode selecionar outra op��o."
						+ "\n==========================\n\n");

		} catch (JauntException e) {
			System.err.println("Erro no arquivo do Jaunt");
		}
		return resposta;
	}
}