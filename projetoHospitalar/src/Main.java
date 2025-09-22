import java.util.Scanner;
import java.util.ArrayList;


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


        do {
            limparTela();
            System.out.println("=== Sistema Hospitalar ===");
            System.out.println("1. Cadastrar paciente");
            System.out.println("2. Cadastrar médico");
            System.out.println("3. Agendar consulta");
            System.out.println("4. Listar relatórios");
            System.out.println("5. Listar pacientes");
            System.out.println("6. Listar médicos");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    limparTela();

                    System.out.println("Cadastrar paciente:");

                    System.out.print("Nome: ");
                    String nomePaciente = sc.next();
                    sc.nextLine(); // Limpa o buffer
                    
                    System.out.print("CPF: ");
                    String cpfPaciente = sc.next();
                    sc.nextLine(); // Limpa o buffer

                    System.out.print("Idade: ");
                    int idadePaciente = sc.nextInt();
                    sc.nextLine(); // Limpa o buffer

                    Paciente novoPaciente = new Paciente(nomePaciente, cpfPaciente, idadePaciente);
                    pacientes.add(novoPaciente); // 👈 agora fica salvo
                    System.out.println("\nPaciente cadastrado com sucesso!");

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

                    System.out.print("Idade: ");
                    int idadeMedico = sc.nextInt();
                    sc.nextLine(); // Limpa o buffer

                    System.out.print("Especialidade: ");
                    String especialidade = sc.next();
                    sc.nextLine(); // Limpa o buffer

                    System.out.print("CRM: ");
                    String crm = sc.next();
                    sc.nextLine(); // Limpa o buffer
                    
                    Medico novoMedico = new Medico(nomeMedico, cpfMedico, idadeMedico, especialidade, crm);
                    medicos.add(novoMedico); // 👈 agora fica salvo
                    System.out.println("\nMédico cadastrado com sucesso!");

                    pausa(sc);

                    break;
                case 3:
                    limparTela();
                    
                    // chamar método para agendar consulta

                    pausa(sc);
                    break;

                case 4:
                    limparTela();

                    // relatórios

                    pausa(sc);
                    break;

                case 5:
                    limparTela();

                    System.out.println("Listar pacientes:");
                    for (Paciente p : pacientes) {
                        System.out.println("Nome: " + p.getNome() + ", CPF: " + p.getCpf() + ", Idade: " + p.getIdade());
                    }
                    sc.nextLine(); // Limpa o buffer

                    pausa(sc);
                    break;

                case 6:
                    limparTela();

                    System.out.println("Listar médicos:");
                    for (Medico m : medicos) {
                        System.out.println("Nome: " + m.getNome() + ", CPF: " + m.getCpf() + ", Idade: " + m.getIdade() + ", Especialidade: " + m.getEspecialidade() + ", CRM: " + m.getCrm());
                    }
                    sc.nextLine(); // Limpa o buffer

                    pausa(sc);
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
