public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private String dataHora;

    public Consulta(Paciente paciente, Medico medico, String dataHora) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public String getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "paciente=" + paciente.getNome() +
                ", medico=" + medico.getNome() +
                ", dataHora='" + dataHora + '\'' +
                '}';
    }
}
