package cdg;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class App {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		String path = "C:\\Users\\guilh\\Desktop\\cadastros2.ser";

		Menu.printMenu();
		Controle controle = new Controle();
		int escolha = controle.opcao();

		List<Cliente> cad = new ArrayList<Cliente>();

		while (escolha != 0) {

			// cadastro
			if (escolha == 1) {
				controle = new Controle();

				System.out.print("Nome: ");
				String nome = controle.texto();

				System.out.print("Telefone: ");
				String telefone = controle.texto();

				System.out.print("Data de Nascimento: ");
				String data = controle.texto();

				System.out.print("Genero: ");
				String genero = controle.texto();

				Cliente clientes = new Cliente(nome, telefone, data, genero);
				System.out.println("\nCadastro efetuado! \n");

				cad.add(clientes);

			}

			// edicao
			if (escolha == 2) {
				System.out.println("\n## - Escolha o número correspondente da edição que você queira realizar - ##\n");
				Menu.printEdit();
				Controle selecao = new Controle();
				int escolha2 = selecao.opcao();

				// editando nome
				if (escolha2 == 1) {
					System.out.println("\nDigite o ID do cliente a ser editado:");
					for (Cliente cadastros : cad) {
						System.out.println("\n" + cad.indexOf(cadastros) + " - " + cadastros.getNome());
					}
					Controle editnome = new Controle();
					int idcliente = editnome.opcao();

					System.out.println("\nNovo nome:");
					Controle newnome = new Controle();
					String novonome = newnome.texto();

					for (Cliente nomes : cad) {
						if (idcliente == cad.indexOf(nomes)) {
							nomes.setNome(novonome);

						}
						System.out.println("Cliente renomeado com sucesso!");

					}

				}
				
				// editando telefone
				if (escolha2 == 2) {
					System.out.println("\nDigite o ID do cliente a ser editado:");
					for (Cliente cadastrostelefone : cad) {
						System.out.println("\n" + cad.indexOf(cadastrostelefone) + " - " + cadastrostelefone.getNome()
								+ " - " + cadastrostelefone.getTelefone());
					}
					Controle editTelefone = new Controle();
					int telefoneCliente = editTelefone.opcao();

					System.out.println("\nNovo Telefone: ");
					Controle novoTelefone = new Controle();
					String newTelefone = novoTelefone.texto();

					for (Cliente telefones : cad) {
						if (telefoneCliente == cad.indexOf(telefones)) {
							telefones.setTelefone(newTelefone);
						}
						System.out.println("\nTelefone alterado com sucesso!");
					}

				}

				// editando nascimento
				if (escolha2 == 3) {
					System.out.println("\nDigite o ID do cliente a ser editado:");
					for (Cliente cadastrosnascimento : cad) {
						System.out.println(cad.indexOf(cadastrosnascimento) + " - " + cadastrosnascimento.getNome()
								+ " - " + cadastrosnascimento.getData());
					}
					Controle editData = new Controle();
					int dataCliente = editData.opcao();

					System.out.println("\nNova data de nascimento: ");
					Controle novaData = new Controle();
					String newData = novaData.texto();

					for (Cliente datas : cad) {
						if (dataCliente == cad.indexOf(datas)) {
							datas.setData(newData);
						}
						System.out.println("\nData alterada com sucesso!");
					}
				}

				// editando genero
				if (escolha2 == 4) {
					System.out.println("\nDigite o ID do cliente a ser editado:");
					for (Cliente cadastrosgenero : cad) {
						System.out.println(cad.indexOf(cadastrosgenero) + " - " + cadastrosgenero.getNome() + " - "
								+ cadastrosgenero.getGenero());
					}
					Controle editGenero = new Controle();
					int generoCliente = editGenero.opcao();

					System.out.println("\nNovo genero: ");
					Controle novoGenero = new Controle();
					String newGenero = novoGenero.texto();

					for (Cliente generos : cad) {
						if (generoCliente == cad.indexOf(generos)) {
							generos.setGenero(newGenero);
						}

						System.out.println("\nGenero alterado com sucesso!");

					}

				}

			}

			// exclusao
			if (escolha == 3) {
				System.out.println("\nQuem voce deseja excluir?\n");
				for (Cliente cadastros : cad) {
					System.out.println(cad.indexOf(cadastros) + " - " + cadastros.getNome());
				}

				System.out.println("Escolha o ID do cliente:");
				Controle escolher = new Controle();
				int escolhido = escolher.opcao();
				cad.remove(escolhido);
				System.out.println("\nCadastro removido com sucesso!\n");

			}

			// salvar
			if (escolha == 4) {
				FileOutputStream canal = new FileOutputStream(path);
				ObjectOutputStream escritor = new ObjectOutputStream(canal);
				escritor.writeObject(cad);
				escritor.close();
				System.out.println("\nCadastro(s) salvo(s)!\n");
			}

			// carregar cadastros
			if (escolha == 5) {
				FileInputStream canal = new FileInputStream(path);
				ObjectInputStream leitor = new ObjectInputStream(canal);
				cad = (List<Cliente>) leitor.readObject();
				leitor.close();
				System.out.println("\nCadastros carregados!\n");
			}
			
			//listar com tudo
			if (escolha == 6) {
				cad.forEach(pessoa -> System.out.println("\n" + pessoa + "\n"));
				if (cad.isEmpty()) {
					System.out.println("ERRO: Não existem cadastros.");
				}
			}

			// listar por nome
			if (escolha == 7) {
				System.out.println("\nClientes cadastrados: \n");
				ArrayList<String> alfa = new ArrayList<>();
				for (Cliente cadastros : cad) {
					alfa.add(cadastros.getNome());
					alfa.sort(String.CASE_INSENSITIVE_ORDER);
				}

				System.out.println(alfa);

			}

			// listar por gen
			if (escolha == 8) {
				System.out.println("\nClientes de genero masculino: ");
				for (Cliente m : cad) {
					if (m.getGenero().startsWith("m") == true) {
						System.out.println(m.getNome() + " - " + "Masculino");
					}
				}
				System.out.println("\nClientes de genero feminino: ");
				for (Cliente f : cad) {
					if (f.getGenero().startsWith("f") == true) {
						System.out.println(f.getNome() + " - " + "Feminino");

					}

				}

			}

			Menu.printMenu();
			controle = new Controle();
			escolha = controle.opcao();

		}
	}
}
