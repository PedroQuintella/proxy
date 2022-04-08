import java.util.List;

public class PacienteProxy implements IPaciente {

    private Paciente paciente;

    private String cpf;

    public PacienteProxy(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public List<String> obterDados() {
        if (this.paciente == null) {
            this.paciente = new Paciente(this.cpf);
        }
        return this.paciente.obterDados();
    }

    @Override
    public String obterExame(Funcionario funcionario) {
        if (!funcionario.isMedico()) {
            throw new IllegalArgumentException("Funcionário não é médico. Proibido acesso!");
        }
        if (this.paciente == null) {
            this.paciente = new Paciente(this.cpf);
        }
        return this.paciente.obterExame(funcionario);
    }
}
