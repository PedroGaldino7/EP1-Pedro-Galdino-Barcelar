import java.io.*;
import java.util.*;

public class MedicoDAO {
    private static final String ARQUIVO = "medicos.txt";

    // Salva lista de médicos no arquivo
    public static void salvar(List<Medico> medicos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Medico m : medicos) {
                bw.write(m.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar médicos: " + e.getMessage());
        }
    }

    // Carrega lista de médicos do arquivo
    public static List<Medico> carregar() {
        List<Medico> medicos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                medicos.add(Medico.fromCSV(linha));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado, criando novo...");
        } catch (IOException e) {
            System.out.println("Erro ao carregar médicos: " + e.getMessage());
        }
        return medicos;
    }
}

