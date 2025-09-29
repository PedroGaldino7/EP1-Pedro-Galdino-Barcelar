import java.io.*;
import java.util.*;

public class MedicoDAO {
    private static final String ARQUIVO = "medicos.txt";

    // Salva lista de médicos no arquivo
// Salva apenas o último médico adicionado na lista
    public static void salvar(List<Medico> medicos) {
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico para salvar.");
            return;
        }

        // Pega apenas o último médico da lista
        Medico m = medicos.get(medicos.size() - 1);
        String cpf = m.getCpf();

        // Verifica duplicidade
        if (VerificadorCPF.cpfExiste(cpf)) {
            System.out.println("⚠ Erro: CPF " + cpf + " já cadastrado! Não foi salvo.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(m.getNome() + ";" + cpf + ";" + m.getIdade() + ";" 
                    + m.getEspecialidade() + ";" + m.getCrm());
            bw.newLine();
            System.out.println("✅ Médico " + m.getNome() + " salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar médico: " + e.getMessage());
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

