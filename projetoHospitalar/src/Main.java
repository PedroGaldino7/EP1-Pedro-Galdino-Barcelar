//Quando for compilar precisa colocar nessa ordem no terminal:
//mkdir (fiz esse antes so uma vez para criar a pasta bin dentro do src) 
//javac -encoding UTF-8 -d bin *.java
//java -cp bin Main

import java.util.Scanner;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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

        List<Paciente> pacientes = PacienteDAO.carregar();
        List<Medico> medicos = MedicoDAO.carregar();
        List<Consulta> consultas = ConsultaDAO.carregar(pacientes, medicos);
        
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

                            // Verifica se o CPF já existe
                            if (PacienteDAO.existeCpf(cpfPaciente) || MedicoDAO.existeCpf(cpfPaciente)) {
                                System.out.println("Erro: CPF já cadastrado.");
                            }else{

                                System.out.print("Idade: ");
                                int idadePaciente = sc.nextInt();
                                sc.nextLine(); // Limpa o buffer

                                pacientes.add(new Paciente(nomePaciente, cpfPaciente, idadePaciente));
                                PacienteDAO.salvar(pacientes); // salva no arquivo
                                System.out.println("\nPaciente " + nomePaciente + " cadastrado com sucesso!");
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

                            // Verifica se o CPF já existe
                            if (MedicoDAO.existeCpf(cpfMedico) || PacienteDAO.existeCpf(cpfMedico)) {
                                System.out.println("Erro: CPF já cadastrado.");
                            }else{

                                System.out.print("Idade: ");
                                int idadeMedico = sc.nextInt();
                                sc.nextLine(); // Limpa o buffer

                                System.out.print("Especialidade: ");
                                String especialidade = sc.nextLine();

                                System.out.print("CRM: ");
                                String crm = sc.nextLine();

                                medicos.add(new Medico(nomeMedico, cpfMedico, idadeMedico, especialidade, crm));
                                MedicoDAO.salvar(medicos); // salva no arquivo
                                System.out.println("\nMédico " + nomeMedico + " cadastrado com sucesso!");
                                }
                                pausa(sc);
                            break;
                        }
                        break;
                        
                case 2:
                    limparTela();
                    
                    System.out.println("=== Agendamentos ===");
                    System.out.println("1. Agendar consulta");
                    System.out.println("2. Agendar internação");
                    System.out.println("0. Voltar");
                    System.out.print("Escolha: ");
                    int agOpcao = sc.nextInt();

                    switch (agOpcao) {
                    
                        case 1:
                        limparTela();

                        System.out.println("Agendar consulta:");


                        if (pacientes.isEmpty()) {
                            System.out.println("Nenhum paciente cadastrado. Cadastre um paciente primeiro.");
                            pausa(sc);
                            break;
                        }

                        if (medicos.isEmpty()) {
                            System.out.println("Nenhum médico cadastrado. Cadastre um médico primeiro.");
                            pausa(sc);
                            break;
                        }


                        System.out.println("Pacientes disponíveis:");
                        limparTela();
                        for (int i = 0; i < pacientes.size(); i++) {
                            System.out.println((i + 1) + ". " + pacientes.get(i).getNome() + " (CPF: " + pacientes.get(i).getCpf() + ")");
                        }
                        System.out.print("Escolha o paciente (número): ");
                        int pacIndex = sc.nextInt() - 1;

                        if (pacIndex < 0 || pacIndex >= pacientes.size()) {
                            System.out.println("Opção inválida.");
                            pausa(sc);  
                            break;
                        }

                        System.out.println("Médicos disponíveis:");
                        limparTela();
                        for (int i = 0; i < medicos.size(); i++) {
                            System.out.println((i + 1) + ". " + medicos.get(i).getNome() + " (Especialidade: " + medicos.get(i).getEspecialidade() + ")");
                        }
                        System.out.print("Escolha o médico (número): ");
                        int medIndex = sc.nextInt() - 1;

                        if (medIndex < 0 || medIndex >= medicos.size()) {
                            System.out.println("Opção inválida.");
                            pausa(sc);
                            break;
                        }

                        sc.nextLine(); // Limpa o buffer
                        limparTela();
                        System.out.print("Data e hora da consulta (DD/MM/AAAA HH:MM): ");
                        String entradaDataHora = sc.nextLine();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dataHora = LocalDateTime.parse(entradaDataHora, formatter);

                        boolean conflito = false;
                        for (Consulta c : consultas) {
                            if (c.getMedico().equals(medicos.get(medIndex)) && c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).equals(entradaDataHora)) {  
                                System.out.println("Esse médico já tem consulta nesse horário!");
                                conflito = true;
                                pausa(sc);
                                break;
                            }
                            if (c.getPaciente().equals(pacientes.get(pacIndex)) && c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).equals(entradaDataHora)) {  
                                System.out.println("Esse paciente já tem consulta nesse horário!");
                                conflito = true;
                                pausa(sc);
                                break;
                            }
                        }

                        if (!conflito) {
                            consultas.add(new Consulta(pacientes.get(pacIndex), medicos.get(medIndex),dataHora));
                            ConsultaDAO.salvar(consultas);
                            System.out.println("\nConsulta agendada com sucesso!");
                            pausa(sc);
                            break;
                        }
                }
                break;

                case 3:
                    limparTela();

                        System.out.println("=== Relatórios ===");
                        System.out.println("1. Relatório dos pacientes");
                        System.out.println("2. Relatório dos médicos");
                        System.out.println("3. Relatório das consultas");
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
                            case 3:
                                limparTela();
                                System.out.println("Relatório das consultas:");
                                for (Consulta c : consultas) {
                                    System.out.println("Paciente: " + c.getPaciente().getNome() + ", Médico: " + c.getMedico().getNome() + ", Data e Hora: " + c.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
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
