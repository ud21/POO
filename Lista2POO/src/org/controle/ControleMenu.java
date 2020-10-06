package org.controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelo.Cliente;
import org.modelo.Servico;
import org.modelo.Veiculo;

public class ControleMenu {

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperarDados(List<Cliente> clientes) throws Exception {
		Date data = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String dataformatada = dateFormat.format(data);

		String caminho = "C:\\Users\\roodr\\OneDrive\\Documentos\\cadastros2.ser";
		String caminho2 = "C:\\Users\\roodr\\OneDrive\\Documentos\\cadastros2.ser"
				+ dataformatada + ".ser";

		File original = new File(caminho);
		File backup = new File(caminho2);

		try {
			Files.copy(original.toPath(), backup.toPath());
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}

		FileInputStream canal = new FileInputStream(caminho);
		ObjectInputStream leitor = new ObjectInputStream(canal);
		clientes = (List<Cliente>) leitor.readObject();
		leitor.close();
		System.out.println("Cadastros lidos com sucesso");
		return clientes;
	}

	public void salvarDados(List<Cliente> clientes) throws Exception {
		String caminho = "C:\\Users\\roodr\\\\OneDrive\\\\Documentos\\cadastros2.ser";
		FileOutputStream canal = new FileOutputStream(caminho);
		ObjectOutputStream escritor = new ObjectOutputStream(canal);
		escritor.writeObject(clientes);
		escritor.close();
		System.out.println("Cadastros salvos com sucesso!");
	}

	public void cadastrarCliente(List<Cliente> clientes, Controle controle) {
		Cliente c = new Cliente();
		System.out.println("Insira o nome do cliente:");
		c.nome = controle.texto();
		System.out.println("Insira o telefone:");
		c.telefone = controle.texto();
		System.out.println("Insira a data de nascimento:");
		c.endereco = controle.texto();
		System.out.println("Insira o cpf:");
		c.cpf = controle.texto();
		clientes.add(c);
	}

	public void agendarRevisao(List<Cliente> clientes, Controle controle) {
		System.out.println("Para realizar o agendamento, selecione o cliente e o veículo");
		System.out.println("Digite o cpf do cliente:");
		String numcpf = controle.texto();
		System.out.println("Digite a placa do veículo:");
		String numplaca = controle.texto();
		for (Cliente cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				for (Veiculo veiculo : cliente.veiculos) {
					if (veiculo.numeroplaca.equals(numplaca)) {
						Servico serv = new Servico();
						System.out.println("Insira o tipo de serviço:");
						serv.tipo = controle.texto();
						System.out.println("Insira a data do serviço:");
						serv.data = controle.texto();
						System.out.println("Insira a hora do serviço:");
						serv.hora = controle.texto();
						veiculo.servicos.add(serv);
					}

				}
			}
		}
	}

	public void cadastrarVeiculo(List<Cliente> clientes, Controle controle) {
		System.out.println("Para cadastrar o veiculo, selecione o cliente");
		System.out.println("Cpf do cliente:");
		String numcpf = controle.texto();
		for (Cliente cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				Veiculo v = new Veiculo();
				System.out.println("Insira o número da placa:");
				v.numeroplaca = controle.texto();
				System.out.println("Insira o modelo:");
				v.modelo = controle.texto();
				System.out.println("Insira o ano de fabricação:");
				v.anofabricacao = controle.texto();
				System.out.println("Insira o valor da compra:");
				v.valorcompra = controle.texto();
				cliente.veiculos.add(v);
			}
		}
	}

	public void alterarAgendamento(List<Cliente> clientes, Controle controle) {
		System.out.println("Para alterar o agendamento, selecione o cliente, veículo e a data do agendamento");
		System.out.println("Cpf do cliente:");
		String numcpf = controle.texto();
		System.out.println("Pplaca do veículo:");
		String numplaca = controle.texto();
		System.out.println("Data do agendamento:");
		String datagenda = controle.texto();
		for (Cliente cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				for (Veiculo veiculo : cliente.veiculos) {
					if (veiculo.numeroplaca.equals(numplaca)) {
						for (Servico servico : veiculo.servicos) {
							if (servico.data.equals(datagenda)) {
								System.out.println("Iinsira o tipo de serviço:");
								servico.tipo = controle.texto();
								System.out.println("Insira a data do serviço:");
								servico.data = controle.texto();
								System.out.println("Insira a hora do serviço:");
								servico.hora = controle.texto();
							}
						}
					}

				}
			}
		}
	}
	
	public void relatoriosServicos(List<Cliente> clientes, Controle controle) {
		System.out.println("Nome do cliente:");
		String numcpf = controle.texto();
		for (Cliente cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				for (Veiculo veiculo : cliente.veiculos) {
					if (veiculo.servicos.isEmpty()) {
						System.out.println("Esse cliente não utilizou nenhum serviço");
					} else {
						System.out.println("Serviços registrados do veículo - PLACA: " + veiculo.numeroplaca);
						veiculo.servicos.forEach(servico -> System.out.println(servico));
					}
				}
			}
		}
	
	}
	public void cancelarAgendamento(List<Cliente> clientes, Controle controle) {
		System.out.println("Para alterar o agendamento, selecione o cliente, veículo e a data do agendamento");
		System.out.println("Cpf do cliente:");
		String numcpf = controle.texto();
		System.out.println("Placa do veículo:");
		String numplaca = controle.texto();
		System.out.println("Data do agendamento:");
		String datagenda = controle.texto();
		for (Cliente cliente : clientes) {
			if (cliente.cpf.equals(numcpf)) {
				for (Veiculo veiculo : cliente.veiculos) {
					if (veiculo.numeroplaca.equals(numplaca)) {
						for (Servico servico : veiculo.servicos) {
							if (servico.data.equals(datagenda)) {
								System.out.println("Cancelar o serviço agendado: " + servico.tipo + "?");
								System.out.println("Digite 1 para cancelar ou 0 para continuar busca");
								int cancelar = controle.opcao();
								if (cancelar == 1) {
									veiculo.servicos.remove(servico);
									System.out.println("O servico agendado foi cancelado com sucesso!");
									break;
								}
							}
						}
					}
				}
			}
		}
	}



}
