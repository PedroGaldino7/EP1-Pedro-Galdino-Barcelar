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
                String dataSaidaProvisoriaStr = (i.getDataSaidaProvisoria() != null) ? i.getDataSaidaProvisoria().format(FORMATTER) : "null";
                String dataSaidaStr = (i.getDataSaida() != null) ? i.getDataSaida().format(FORMATTER) : "null";

                bw.write(
                    i.getPaciente().getCpf() + "," +
                    i.getDataEntrada().format(FORMATTER) + "," +
                    dataSaidaProvisoriaStr + "," +
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
                if (partes.length < 6) continue;

                String cpf = partes[0];
                Paciente paciente = pacientes.stream()
                        .filter(p -> p.getCpf().equals(cpf))
                        .findFirst()
                        .orElse(null);

                if (paciente == null) continue;

                LocalDateTime dataEntrada = LocalDateTime.parse(partes[1], FORMATTER);
                LocalDateTime dataSaidaProvisoria = partes[2].equals("null") ? null : LocalDateTime.parse(partes[2], FORMATTER);
                LocalDateTime dataSaida = partes[3].equals("null") ? null : LocalDateTime.parse(partes[3], FORMATTER);
                String quarto = partes[4];
                String motivo = partes[5];

                Internacao internacao = new Internacao(paciente, dataEntrada, dataSaidaProvisoria, dataSaida, quarto, motivo);
                internacoes.add(internacao);
            }
        } catch (FileNotFoundException e) {
            // Primeira execução — arquivo ainda não existe
        } catch (IOException e) {
            System.out.println("Erro ao carregar internações: " + e.getMessage());
        }

        return internacoes;
    }

public static boolean quartoOcupado(String quarto, LocalDateTime novaEntrada, LocalDateTime novaSaidaProvisoria, List<Internacao> internacoes) {
    for (Internacao i : internacoes) {
        if (i.getQuarto().equalsIgnoreCase(quarto)) {

            if (i.getDataSaida() != null && i.getDataSaida().isBefore(novaEntrada)) {
                continue;
            }

            LocalDateTime entradaExistente = i.getDataEntrada();
            LocalDateTime saidaProvisoriaExistente = i.getDataSaidaProvisoria();

            if (saidaProvisoriaExistente == null) {
                saidaProvisoriaExistente = LocalDateTime.MAX;
            }

            boolean sobrepoe = 
                (novaEntrada.isBefore(saidaProvisoriaExistente) && novaSaidaProvisoria.isAfter(entradaExistente));

            if (sobrepoe && i.getDataSaida() == null) {
                return true;
            }
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
