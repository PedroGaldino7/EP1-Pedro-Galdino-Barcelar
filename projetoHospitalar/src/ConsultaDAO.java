import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConsultaDAO {
    private static final String ARQUIVO = "consultas.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void salvar(List<Consulta> consultas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Consulta c : consultas) {
                bw.write(c.getPaciente().getCpf() + "," +
                    c.getMedico().getCpf() + "," +
                    c.getDataHora().format(formatter));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar Consultas: " + e.getMessage());
        }
    }

    public static List<Consulta> carregar(List<Paciente> pacientes, List<Medico> medicos) {
        List<Consulta> consultas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    String cpfPaciente = partes[0];
                    String cpfMedico = partes[1];
                    LocalDateTime dataHora = LocalDateTime.parse(partes[2], formatter);

                    Paciente paciente = pacientes.stream()
                            .filter(p -> p.getCpf().equals(cpfPaciente))
                            .findFirst()
                            .orElse(null);

                    Medico medico = medicos.stream()
                            .filter(m -> m.getCpf().equals(cpfMedico))
                            .findFirst()
                            .orElse(null);

                    if (paciente != null && medico != null) {
                        consultas.add(new Consulta(paciente, medico, dataHora));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Primeira execução, arquivo ainda não existe
        } catch (IOException e) {
            System.out.println("Erro ao carregar Consultas: " + e.getMessage());
        }
        return consultas;
    }
}
