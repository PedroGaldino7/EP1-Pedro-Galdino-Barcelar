import java.io.*;
import java.util.*;

public class MedicoDAO {
    private static final String ARQUIVO = "medicos.txt";

    public static void salvar(List<Medico> medicos) {
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico para salvar.");
            return;
        }

        Medico m = medicos.get(medicos.size() - 1);
        String cpf = m.getCpf();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(m.getNome() + "," + cpf + "," + m.getIdade() + "," + m.getEspecialidade() + "," + m.getCrm());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar médico: " + e.getMessage());
        }
    }

    public static List<Medico> carregar() {
        List<Medico> medicos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                medicos.add(Medico.fromTXT(linha));
            }
        } catch (FileNotFoundException e) {
            
        } catch (IOException e) {
            System.out.println("Erro ao carregar médicos: " + e.getMessage());
        }
        return medicos;
    }

    public static boolean existeCpf(String cpf) {
        List<Medico> medicos = carregar();
        for (Medico m : medicos) {
            if (m.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}

