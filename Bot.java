package botFAAP;

import java.util.List;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

@SuppressWarnings("deprecation")
public class Bot {

	public static void main(String[] args) {

		//TelegramBot bot = TelegramBotAdapter.build("574176788:AAGEOb3BX7hWY8c__FVUDhrG5zjMR69JBSg");
		TelegramBot bot = TelegramBotAdapter.build("454775898:AAEPdzcR25cddHP1xa5tCMG-WZeXNLvlypw");
		
		// objeto responsavel por receber as mensagens
					GetUpdatesResponse updatesResponse;
					// objeto responsavel por gerenciar o envio de respostas
					SendResponse sendResponse;
					// objeto responsavel por gerenciar o envio de acoes do chat
					BaseResponse baseResponse;

					// controle de off-set. A partir deste ID serao lidas as mensagens
					// pendentes na fila
					int m = 0;

					// loop infinito pode ser alterado por algum timer de intervalo curto
					while (true) {

						// executa comando no Telegram para obter as mensagens pendentes a partir de um
						// off-set (limite inicial)
						updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

						// lista de mensagens
						List<Update> updates = updatesResponse.updates();

						// analise de cada acao da mensagem
						for (Update update : updates) {

							// atualizacao do off-set
							m = update.updateId() + 1;

							if (update.callbackQuery() != null) {

								sendResponse = bot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
										update.callbackQuery().data()));
							} else {
								System.out.println("Recebendo mensagem:" + update.message().text());

								// envio de "Escrevendo" antes de enviar a resposta
								baseResponse = bot
										.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
								// verificacao de acao de chat foi enviada com sucesso
								System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

								if (update.message().text() != null) {

									// Criação de Keyboard
									if (update.message().text().equals("/keyboard")) {
										sendResponse = bot.execute(new SendMessage(update.message().chat()
												.id(), "Selecione uma opção no Teclado:").replyMarkup(new ReplyKeyboardMarkup(
														new String[] { "/AtualRP","/AtualSJ", "/AtualSP"},
														new String[] { "/ProximoRP", "/ProximoSJ","/ProximoSP"},
														new String[] { "/Start", "/Site"})));
									}
									
									String resposta = gerarResposta(update.message().text());
									System.out.println("Enviando: " + resposta);
									
									sendResponse = bot.execute(new SendMessage(update.message().chat().id(), resposta));

									// verificacao de mensagem enviada com sucesso
									System.out.println("Mensagem Enviada?" + sendResponse.isOk());

							}

						}

					}

				}

			}

			private static String gerarResposta(String Mensagem) {
				Mensagem = Mensagem.toLowerCase();

				if (Mensagem.equals("/start")) {
			return "= Programação FAAP Acontece =\n\n" 
					+ "Consulte todos os eventos que ocorrerão neste mês na FAAP.\n"
					+ "\nVocê pode ver os eventos em Ribeirão Preto, São Paulo e "
					+ "\nSão José dos Campos:\n"
					
					+ "\n Eventos de Ribeirão Preto \nselecione: /AtualRP"
					+ "\n Eventos de São José dos Campos \nselecione: /AtualSJ"
					+ "\n Eventos de São Paulo \nselecione: /AtualSP\n"
					
					+ "\nConsulte os eventos que ocorrerão no próximo mês:\n"
					
					+ "\n Eventos de Ribeirão Preto \nselecione:  /ProximoRP"
					+ "\n Eventos de São José dos Campos \nselecione: /ProximoSJ"
					+ "\n Eventos de São Paulo \nselecione: /ProximoSP"
					+ "\n\nLink da página na Internet: /Site "
					+ "\nSelecione /Start para reiniciar"	
					+ "\nPara abrir um menu: /keyboard";
		}
		try {
			if (Mensagem.equals("/atualrp")) {
				return Scrap.Pesquisa("rp","0");
			}
			else if (Mensagem.equals("/atualsj")) {
				return Scrap.Pesquisa("sj","0");
			}
			else if (Mensagem.equals("/atualsp")) {				
				return Scrap.Pesquisa("sp","0");
			}
			else if (Mensagem.equals("/proximorp")) {				
				return Scrap.Pesquisa("rp","1");
			}
			else if (Mensagem.equals("/proximosj")) {				
				return Scrap.Pesquisa("sj","1");
			}
			else if (Mensagem.equals("/proximosp")) {				
				return Scrap.Pesquisa("sp","1");
			}
			else if (Mensagem.equals("/site")) {				
				return "http://acontece.faap.br/eventos/acontece/";
			}
			

		} catch (Exception p) {
			return "\n Erro ao selecionar um item.\n\n";
		}
		if (Mensagem.equals("/keyboard")) {
			return (" ");
		}
		else
		return "Não entendi...\n Digite uma opção válida.\n\n";
	}
	
}