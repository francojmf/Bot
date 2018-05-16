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
						local2 = "Ribeirão Preto";}
		if(local=="sj") {local = "sao_jose_dos_campos";
						local2 = "São José dos Campos";}
		if(local=="sp") {local = "sao_paulo";
						local2 = "São Paulo";}
		
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
				resposta +=("\nTítulo: "
						+ titulos.getElement(contador).getText().trim().replace("&#231;", "ç").replace("&#245;", "õ")
								.replace("&#186;", "°").replace("&#202;", "Ê").replace("&#195;", "Ã")
								.replace("&quot;", "'").replace("&#218;", "Ú").replace("&#244;", "ô")
								.replace("&#199;", "Ç").replace("&#192;", "À").replace("&#193;", "Á")
								.replace("&#233;", "é").replace("&#227;", "ã").replace("&#225;", "á")
								.replace("&#250;", "ú").replace("&#227;", "ã").replace("&#237;", "í")
								.replace("&#243;", "ó").replace("&#224;", "à").replace("&#234;", "ê")
								.replace("&#170;", "ª").replace("&#226;", "â").replace("&#245;", "õ")
								.replace("&#211;", "Ó").replace("&#39;", "'")
						+ "\nPalestrante: "
						+ nomes.getElement(contador).getText().trim().replace("&#231;", "ç").replace("&#245;", "õ")
								.replace("&#186;", "°").replace("&#202;", "Ê").replace("&#195;", "Ã")
								.replace("&quot;", "'").replace("&#218;", "Ú").replace("&#244;", "ô")
								.replace("&#199;", "Ç").replace("&#192;", "À").replace("&#193;", "Á")
								.replace("&#233;", "é").replace("&#227;", "ã").replace("&#225;", "á")
								.replace("&#250;", "ú").replace("&#227;", "ã").replace("&#237;", "í")
								.replace("&#243;", "ó").replace("&#224;", "à").replace("&#234;", "ê")
								.replace("&#170;", "ª").replace("&#226;", "â").replace("&#245;", "õ")
								.replace("&#211;", "Ó").replace("&#39;", "'")
						
						+ "\nHora: " + horas.getElement(contador).getText().trim() + "\nDia:"
						+ dias.getElement(contador).getText().trim() +" / "+mes
						+"\nInscrição: http://acontece.faap.br/eventos/acontece/"+local+"/eventos/"+ano+"-"+mes+"\n"
						+ "\n-----------------------------------------------\n");			
				
			}
			resposta +=("\nFinal da lista de \n" + local2 + "\nno mês " + mes +" - " + ano 
						+ "\n\n Você pode selecionar outra opção."
						+ "\n==========================\n\n");

		} catch (JauntException e) {
			System.err.println("Erro no arquivo do Jaunt");
		}
		return resposta;
	}
}