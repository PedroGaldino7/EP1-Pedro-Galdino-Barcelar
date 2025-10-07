public class Paciente extends Pessoa {

    public Paciente(String nome, String cpf, int idade) {
        super(nome, cpf, idade);
    }

    @Override
        public String toTXT() {
        return super.toTXT();
    }

    public static Paciente fromTXT(String linha) {
        String[] partes = linha.split(",");
        return new Paciente(
            partes[0],
            partes[1],
            Integer.parseInt(partes[2])
            );
    }

    public String toString() {
        return "Paciente{" +
                "nome=" + getNome() +
                ", cpf=" + getCpf() +
                ", idade=" + getIdade() +
                '}';
    }
}
