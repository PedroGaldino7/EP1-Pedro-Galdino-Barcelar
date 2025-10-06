import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Internacao {
    private Paciente paciente;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String quarto;
    private String motivoInternacao;

    public Internacao(Paciente paciente, LocalDateTime dataEntrada, LocalDateTime dataSaida, String quarto, String motivoInternacao) {
        this.paciente = paciente;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.quarto = quarto;
        this.motivoInternacao = motivoInternacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }
    public LocalDateTime getDataSaida() {
        return dataSaida;
    }
    public String getQuarto() {
        return quarto;
    }
    public String getMotivoInternacao() {
        return motivoInternacao;
    }
    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataEntradaStr = dataEntrada.format(formatter);
        String dataSaidaStr = (dataSaida != null) ? dataSaida.format(formatter) : "Ainda internado";
        
        return "Internacao{" +
                "paciente=" + paciente.getNome() +
                ", dataEntrada=" + dataEntradaStr +
                ", dataSaida=" + dataSaidaStr +
                ", quarto='" + quarto + '\'' +
                ", motivoInternacao='" + motivoInternacao + '\'' +
                '}';
    }
}
