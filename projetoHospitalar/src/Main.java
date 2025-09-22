
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        ArrayList<Paciente> pacientes = new ArrayList<>();
        ArrayList<Medico> medicos = new ArrayList<>();


        do {
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

                    System.out.println("Cadastrar paciente:");
                    System.out.print("Nome: ");
                    String nomePaciente = sc.next();
                    System.out.print("CPF: ");
                    String cpfPaciente = sc.next();
                    System.out.print("Idade: ");
                    int idadePaciente = sc.nextInt();

                    Paciente novoPaciente = new Paciente(nomePaciente, cpfPaciente, idadePaciente);
                    pacientes.add(novoPaciente); // 👈 agora fica salvo
                    System.out.println("Paciente cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Cadastrar médico:");
                    System.out.print("Nome: ");
                    String nomeMedico = sc.next();
                    System.out.print("CPF: ");
                    String cpfMedico = sc.next();
                    System.out.print("Idade: ");
                    int idadeMedico = sc.nextInt();
                    System.out.print("Especialidade: ");
                    String especialidade = sc.next();
                    System.out.print("CRM: ");
                    String crm = sc.next();
                    
                    Medico novoMedico = new Medico(nomeMedico, cpfMedico, idadeMedico, especialidade, crm);
                    medicos.add(novoMedico); // 👈 agora fica salvo
                    System.out.println("Médico cadastrado com sucesso!");
                    break;
                case 3:
                    // chamar método para agendar consulta
                    break;
                case 4:
                    // relatórios
                    break;
                case 5:
                    System.out.println("Listar pacientes:");
                    for (Paciente p : pacientes) {
                        System.out.println("Nome: " + p.getNome() + ", CPF: " + p.getCpf() + ", Idade: " + p.getIdade());
                    }
                    break;
                case 6:
                    System.out.println("Listar médicos:");
                    for (Medico m : medicos) {
                        System.out.println("Nome: " + m.getNome() + ", CPF: " + m.getCpf() + ", Idade: " + m.getIdade());
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
