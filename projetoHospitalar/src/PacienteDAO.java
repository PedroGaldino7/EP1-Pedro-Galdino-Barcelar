import java.io.*;
import java.util.*;

public class PacienteDAO {
    private static final String ARQUIVO = "pacientes.txt";

    public static void salvar(List<Paciente> pacientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Paciente p : pacientes) {
                bw.write(p.toTXT());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar Pacientes: " + e.getMessage());
        }
    }

    public static List<Paciente> carregar() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                pacientes.add(Paciente.fromTXT(linha));
            }
        } catch (FileNotFoundException e) {
            
        } catch (IOException e) {
            System.out.println("Erro ao carregar Pacientes: " + e.getMessage());
        }
        return pacientes;
    }

        public static boolean existeCpf(String cpf) {
        List<Paciente> pacientes = carregar();
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}
