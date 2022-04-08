import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PacienteProxyTest {

    @BeforeEach
    void setUp() {
        BD.addPaciente(new Paciente("17546733230", "Miguel", 21, "São Paulo", "Exame de Sangue"));
        BD.addPaciente(new Paciente("25566931250", "Frederico", 18, "São Paulo", "Exame de Urina"));
    }

    @Test
    void deveRetornarDadosPaciente() {
        PacienteProxy paciente = new PacienteProxy("17546733230");

        assertEquals(Arrays.asList("Miguel", "São Paulo"), paciente.obterDados());
    }

    @Test
    void deveRetonarExamePaciente() {
        Funcionario funcionario = new Funcionario("Pedro", true);
        PacienteProxy paciente = new PacienteProxy("25566931250");

        assertEquals("Exame de Urina", paciente.obterExame(funcionario));
    }

    @Test
    void deveRetonarExcecaoFuncionarioNaoAutorizadoAcessarDadosPaciente() {
        try {
            Funcionario funcionario = new Funcionario("Bruno", false);
            PacienteProxy paciente = new PacienteProxy("17546733230");

            paciente.obterExame(funcionario);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Funcionário não é médico. Proibido acesso!", e.getMessage());
        }
    }
}