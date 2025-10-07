import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Internacao {
    private Paciente paciente;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaidaProvisoria;
    private LocalDateTime dataSaida;
    private String quarto;
    private String motivoInternacao;

    public Internacao(Paciente paciente, LocalDateTime dataEntrada, LocalDateTime dataSaidaProvisoria, LocalDateTime dataSaida, String quarto, String motivoInternacao) {
        this.paciente = paciente;
        this.dataEntrada = dataEntrada;
        this.dataSaidaProvisoria = dataSaidaProvisoria;
        this.dataSaida = dataSaida;
        this.quarto = quarto;
        this.motivoInternacao = motivoInternacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Paciente setPaciente(Paciente paciente){
        return this.paciente = paciente;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public LocalDateTime setDataEntrada(LocalDateTime dataEntrada){
        return this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaidaProvisoria() {
        return dataSaidaProvisoria;
    }

    public LocalDateTime setDataSaidaProvisoria(LocalDateTime dataSaidaProvisoria){
        return this.dataSaidaProvisoria = dataSaidaProvisoria;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public LocalDateTime setDataSaida(LocalDateTime dataSaida){
        return this.dataSaida = dataSaida;
    }

    public String getQuarto() {
        return quarto;
    }

    public String setQuarto(String quarto){
        return this.quarto = quarto;
    }

    public String getMotivoInternacao() {
        return motivoInternacao;
    }

    public String setMotivoInternacao(String motivoInternacao){
        return this.motivoInternacao = motivoInternacao;
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

        public String getStatus() {
            LocalDateTime agora = LocalDateTime.now();

            if (agora.isBefore(dataEntrada)) {
                return "Ainda nao internado";
            } else if (dataSaida != null) {
                return "Alta concedida";
            } else if (agora.isAfter(dataEntrada) && agora.isBefore(dataSaidaProvisoria)) {
                return "Paciente internado";
            } else if (agora.isAfter(dataSaidaProvisoria) && dataSaida == null) {
                return "Internação em atraso (não teve alta)";
            } else {
                return "Situação indefinida";
            }
        }

}
