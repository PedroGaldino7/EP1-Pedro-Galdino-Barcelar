public class Paciente extends Pessoa {
    private String historicoConsultas;
    private String historicoInternacoes;

    public Paciente(String nome, String cpf, int idade) {
        super(nome, cpf, idade);
    }

    public String getHistoricoConsultas() {
        return historicoConsultas;
    }

    public void setHistoricoConsultas(String historicoConsultas) {
        this.historicoConsultas = historicoConsultas;
    }

    public String getHistoricoInternacoes() {
        return historicoInternacoes;
    }

    public void setHistoricoInternacoes(String historicoInternacoes) {
        this.historicoInternacoes = historicoInternacoes;
    }
}
