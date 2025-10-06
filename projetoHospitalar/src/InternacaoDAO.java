import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class InternacaoDAO {
    private static final String ARQUIVO = "internacoes.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static void salvar(List<Internacao> internacoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Internacao i : internacoes) {
                String dataSaidaStr = (i.getDataSaida() != null) ? i.getDataSaida().format(FORMATTER) : "null";
                bw.write(
                    i.getPaciente().getCpf() + "," +
                    i.getDataEntrada().format(FORMATTER) + "," +
                    dataSaidaStr + "," +
                    i.getQuarto() + "," +
                    i.getMotivoInternacao()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar internações: " + e.getMessage());
        }
    }

    public static List<Internacao> carregar(List<Paciente> pacientes) {
        List<Internacao> internacoes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length < 5) continue;

                String cpf = partes[0];
                Paciente paciente = pacientes.stream()
                        .filter(p -> p.getCpf().equals(cpf))
                        .findFirst()
                        .orElse(null);

                if (paciente == null) continue;

                LocalDateTime dataEntrada = LocalDateTime.parse(partes[1], FORMATTER);
                LocalDateTime dataSaida = partes[2].equals("null") ? null : LocalDateTime.parse(partes[2], FORMATTER);
                String quarto = partes[3];
                String motivo = partes[4];

                Internacao internacao = new Internacao(paciente, dataEntrada, dataSaida, quarto, motivo);
                internacoes.add(internacao);
            }
        } catch (FileNotFoundException e) {
            // Primeira vez o arquivo não existe e ai ele cria
        } catch (IOException e) {
            System.out.println("Erro ao carregar internações: " + e.getMessage());
        }

        return internacoes;
    }

    public static boolean quartoOcupado(String quarto, List<Internacao> internacoes) {
        for (Internacao i : internacoes) {
            if (i.getQuarto().equalsIgnoreCase(quarto) && i.getDataSaida() == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean pacienteJaInternado(String cpfPaciente, List<Internacao> internacoes) {
        for (Internacao i : internacoes) {
            if (i.getPaciente().getCpf().equals(cpfPaciente) && i.getDataSaida() == null) {
                return true;
            }
        }
        return false;
    }
}
