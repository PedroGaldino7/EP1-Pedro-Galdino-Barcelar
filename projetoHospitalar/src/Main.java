//Quando for compilar precisa colocar nessa ordem no terminal:
//mkdir (fiz esse antes so uma vez para criar a pasta bin dentro do src) 
//javac -encoding UTF-8 -d bin *.java
//java -cp bin Main

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;


public class Main {

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush(); 
    }

    public static void pausa(Scanner sc) {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        sc.nextLine(); // agora sim espera o ENTER de verdade
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        ArrayList<Paciente> pacientes = new ArrayList<>();
        ArrayList<Medico> medicos = new ArrayList<>();
        HashSet<String> cpfs = new HashSet<>();

        
        do {
            limparTela();
            System.out.println("=== Sistema Hospitalar ===");
            System.out.println("1. Cadastros");
            System.out.println("2. Agendamentos (consultas e internações)");
            System.out.println("3. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    limparTela();
                    System.out.println("=== Cadastros ===");
                    System.out.println("1. Cadastrar paciente");
                    System.out.println("2. Cadastrar médico");
                    System.out.println("0. Voltar");
                    System.out.print("Escolha: ");
                    int cadOpcao = sc.nextInt();

                    switch (cadOpcao) {
                        case 1:
                            limparTela();

                            System.out.println("Cadastrar paciente:");

                            sc.nextLine(); // Limpa o buffer
                            System.out.print("Nome: ");
                            String nomePaciente = sc.nextLine();
                            
                            System.out.print("CPF: ");
                            String cpfPaciente = sc.next();
                            sc.nextLine(); // Limpa o buffer

                            if (cpfs.contains(cpfPaciente)) {
                                System.out.println("Erro: CPF já cadastrado.");
                                pausa(sc);
                                break; // sai do case 1
                            } else {
                                System.out.print("Idade: ");
                                int idadePaciente = sc.nextInt();
                                sc.nextLine(); // Limpa o buffer

                                Paciente novoPaciente = new Paciente(nomePaciente, cpfPaciente, idadePaciente);
                                pacientes.add(novoPaciente); // 👈 agora fica salvo
                                cpfs.add(cpfPaciente);
                                System.out.println("\nPaciente cadastrado com sucesso!");
                            }
                            pausa(sc);
                            break;

                        case 2:
                            limparTela();

                            System.out.println("Cadastrar médico:");

                            System.out.print("Nome: ");
                            String nomeMedico = sc.next();
                            sc.nextLine(); // Limpa o buffer

                            System.out.print("CPF: ");
                            String cpfMedico = sc.next();
                            sc.nextLine(); // Limpa o buffer

                            if (cpfs.contains(cpfMedico)) {
                                System.out.println("Erro: CPF já cadastrado.");
                                pausa(sc);
                                break; // sai do case 2

                            } else {
                                System.out.print("Idade: ");
                                int idadeMedico = sc.nextInt();
                                sc.nextLine(); // Limpa o buffer

                                System.out.print("Especialidade: ");
                                String especialidade = sc.nextLine();

                                System.out.print("CRM: ");
                                String crm = sc.nextLine();

                                Medico novoMedico = new Medico(nomeMedico, cpfMedico, idadeMedico, especialidade, crm);
                                medicos.add(novoMedico); // 👈 agora fica salvo
                                cpfs.add(cpfMedico);
                                System.out.println("\nMédico cadastrado com sucesso!");
                                pausa(sc);
                            }
                            break;
                        }
                case 2:
                    limparTela();
                    
                    // chamar método para agendar consulta

                    pausa(sc);
                    break;

                case 3:
                    limparTela();

                        System.out.println("=== Relatórios ===");
                        System.out.println("1. Relatório dos pacientes");
                        System.out.println("2. Relatório dos médicos");
                        System.out.println("0. Voltar");
                        System.out.print("Escolha: ");
                        int relOpcao = sc.nextInt();
                        switch (relOpcao) {
                            case 1:
                                limparTela();
                                System.out.println("Relatório dos pacientes:");
                                for (Paciente p : pacientes) {
                                    System.out.println("Nome: " + p.getNome() + ", CPF: " + p.getCpf() + ", Idade: " + p.getIdade());
                                }
                                sc.nextLine(); // Limpa o buffer
                                pausa(sc);
                                break;
                            case 2:
                                limparTela();
                                System.out.println("Relatório dos médicos:");
                                for (Medico m : medicos) {
                                    System.out.println("Nome: " + m.getNome() + ", CPF: " + m.getCpf() + ", Idade: " + m.getIdade() + ", Especialidade: " + m.getEspecialidade() + ", CRM: " + m.getCrm());
                                }
                                sc.nextLine(); // Limpa o buffer
                                pausa(sc);
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                sc.nextLine(); // Limpa o buffer
                                pausa(sc);
                        }
                    // chamar método para gerar relatórios
                    break;

                case 0:
                    limparTela();
                    System.out.println("Saindo...");
                    break;

                default:
                    limparTela();
                    System.out.println("Opção inválida.");
                    sc.nextLine(); // Limpa o buffer
                    pausa(sc);
            }

        } while (opcao != 0);

        sc.close();
        }
    }
