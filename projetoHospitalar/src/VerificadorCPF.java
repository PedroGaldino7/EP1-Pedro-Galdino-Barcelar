import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerificadorCPF {   

    private static final String ARQUIVO_MEDICOS = "medicos.txt";
    private static final String ARQUIVO_PACIENTES = "pacientes.txt";

    // Método para verificar se já existe esse CPF
    public static boolean cpfExiste(String cpf) {
        return buscarCpfNoArquivo(cpf, ARQUIVO_MEDICOS) ||
               buscarCpfNoArquivo(cpf, ARQUIVO_PACIENTES);
    }

    private static boolean buscarCpfNoArquivo(String cpf, String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length > 1 && dados[1].equals(cpf)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // Se o arquivo não existir ainda, só ignora
        }
        return false;
    }
}
