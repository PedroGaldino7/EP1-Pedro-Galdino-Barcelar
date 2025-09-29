import java.io.*;
import java.util.*;

public class PacienteDAO {
    private static final String ARQUIVO = "pacientes.txt";

    // Salva lista de médicos no arquivo
    public static void salvar(List<Paciente> pacientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Paciente p : pacientes) {
                bw.write(p.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar Pacientes: " + e.getMessage());
        }
    }

    // Carrega lista de médicos do arquivo
    public static List<Paciente> carregar() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                pacientes.add(Paciente.fromCSV(linha));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, criando novo...");
        } catch (IOException e) {
            System.out.println("Erro ao carregar Pacientes: " + e.getMessage());
        }
        return pacientes;
    }
}
