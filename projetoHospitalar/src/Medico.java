public class Medico extends Pessoa {
    private String especialidade;
    private String crm;

        public Medico(String nome, String cpf, int idade, String especialidade, String crm) {
        super(nome, cpf, idade);
        this.especialidade = especialidade;
        this.crm = crm;
    }

    public String getEspecialidade(){
        return especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }

    public String getCrm(){
        return crm;
    }

    public void setCrm(String crm){
        this.crm = crm;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + especialidade + "," + crm;
    }

    public static Medico fromCSV(String linha) {
        String[] partes = linha.split(",");
        return new Medico(
            partes[0],
            partes[1],
            Integer.parseInt(partes[2]),
            partes[3],
            partes[4]
            );
    }

        @Override
    public String toString() {
        return "Medico{" +
                "nome=" + getNome() +
                ", cpf=" + getCpf() +
                ", idade=" + getIdade() +
                ", especialidade=" + especialidade +
                ", crm=" + crm +
                '}';
    }
}
