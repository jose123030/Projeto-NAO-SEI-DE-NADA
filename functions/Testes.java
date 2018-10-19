package functions;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

import app.Pessoa;
import app.Endereco;
import app.Turma;
import app.Estudante;
import app.Professor;
import app.Funcionario;

import org.junit.Test;
// import org.junit.AfterClass;
// import org.junit.BeforeClass;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Testes {

    @Test
    public void construtor_endereco() {
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        String message = "Verifique a ordem dos parâmetros do construtor: (logradouro, numero, complemento, bairro, cidade, estado)";
        assertThat(end.logradouro).as(message).isEqualTo("BR 101");
        assertThat(end.numero).as(message).isEqualTo("numero 320");
        assertThat(end.complemento).as(message).isEqualTo("sem complemento");
        assertThat(end.bairro).as(message).isEqualTo("b Saramandaia");
        assertThat(end.cidade).as(message).isEqualTo("c Igarassu");
        assertThat(end.estado).as(message).isEqualTo("e Pernambuco");
    }

    @Test
    public void construtor_pessoa_1() {
        Pessoa tony = new Pessoa();

        String message = "O construtor padrão (sem parâmetros) deve não inicializar nada";
        assertThat(tony.id).as(message).isEqualTo(0);
        assertThat(tony.nome).as(message).isNull();
        assertThat(tony.cpf).as(message).isNull();
        assertThat(tony.endereco).as(message).isNull();
    }

    @Test
    public void construtor_pessoa_2() {
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        Pessoa tony = new Pessoa(10, "Tony Stark", "123098", end);

        String message = "Verifique a ordem dos parâmetros: (id, nome, cpf, endereco)";
        assertThat(tony.id).as(message).isEqualTo(10);
        assertThat(tony.nome).as(message).isEqualTo("Tony Stark");
        assertThat(tony.cpf).as(message).isEqualTo("123098");
        assertThat(tony.endereco).as(message).isEqualTo(end);
    }

    @Test
    public void costrutor_estudante_1() {
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        Pessoa tony = new Pessoa(10, "Tony Stark", "123098", end);
        Estudante tonyStd = new Estudante(tony, "345678");

        String message = "Os dados do primeiro parâmetro (Pessoa) devem ser copiados para o objeto sendo criado";
        assertThat(tonyStd.id).as(message).isEqualTo(10);
        assertThat(tonyStd.nome).as(message).isEqualTo("Tony Stark");
        assertThat(tonyStd.cpf).as(message).isEqualTo("123098");
        assertThat(tonyStd.endereco).as(message).isEqualTo(end);
        assertThat(tonyStd.matricula).as("A matrícula está sendo setada?").isEqualTo("345678");
    }

    @Test
    public void costrutor_estudante_2() {
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        Estudante tonyStd = new Estudante(10, "Tony Stark", "123098", end, "345678");

        String message = "Verifique a ordem dos parâmetros: (id, nome, cpf, endereco, matricula)";
        assertThat(tonyStd.id).as(message).isEqualTo(10);
        assertThat(tonyStd.nome).as(message).isEqualTo("Tony Stark");
        assertThat(tonyStd.cpf).as(message).isEqualTo("123098");
        assertThat(tonyStd.endereco).as(message).isEqualTo(end);
        assertThat(tonyStd.matricula).as("A matrícula está sendo setada?").isEqualTo("345678");
    }

    @Test
    public void costrutor_funcionario_1() {
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        Pessoa bruce = new Pessoa(10, "Bruce Banner", "123789", end);
        Funcionario hulk = new Funcionario(bruce, "tank");

        String message = "Os dados do primeiro parâmetro (Pessoa) devem ser copiados para o objeto sendo criado";
        assertThat(hulk.id).as(message).isEqualTo(10);
        assertThat(hulk.nome).as(message).isEqualTo("Bruce Banner");
        assertThat(hulk.cpf).as(message).isEqualTo("123789");
        assertThat(hulk.endereco).as(message).isEqualTo(end);
        assertThat(hulk.funcao).as("A matrícula está sendo setada?").isEqualTo("tank");
    }

    @Test
    public void costrutor_funcionario_2() {
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        Funcionario hulk = new Funcionario(10, "Bruce Banner", "123789", end, "tank");

        String message = "Verifique a ordem dos parâmetros: (id, nome, cpf, endereco, funcao)";
        assertThat(hulk.id).as(message).isEqualTo(10);
        assertThat(hulk.nome).as(message).isEqualTo("Bruce Banner");
        assertThat(hulk.cpf).as(message).isEqualTo("123789");
        assertThat(hulk.endereco).as(message).isEqualTo(end);
        assertThat(hulk.funcao).as("A matrícula está sendo setada?").isEqualTo("tank");
    }

    @Test
    public void construtor_professor_1() {
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        Pessoa bruce = new Pessoa(10, "Bruce Banner", "123789", end);
        Turma t1 = new Turma("Radiação Gama 1", "", "Manhã");
        Turma t2 = new Turma("Radiação Gama 2", "", "Tarde");
        Professor hulk = new Professor(bruce, new Turma[] {t1, t2});

        String message = "Verifique a ordem dos parâmetros: (id, nome, cpf, endereco, turmas[])";
        assertThat(hulk.id).as(message).isEqualTo(10);
        assertThat(hulk.nome).as(message).isEqualTo("Bruce Banner");
        assertThat(hulk.cpf).as(message).isEqualTo("123789");
        assertThat(hulk.endereco).as(message).isEqualTo(end);
        assertThat(hulk.turmas).as("As turmas recebidas do construtor estão sendo colocadas no atributo?").hasSize(2);
        assertThat(hulk.turmas).as("As turmas recebidas do construtor estão sendo colocadas no atributo?").contains(t1);
        assertThat(hulk.turmas).as("As turmas recebidas do construtor estão sendo colocadas no atributo?").contains(t2);
    }

    @Test
    public void construtor_professor_2() {
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        Turma t1 = new Turma("Radiação Gama 1", "", "Manhã");
        Turma t2 = new Turma("Radiação Gama 2", "", "Tarde");
        Professor hulk = new Professor(10, "Bruce Banner", "123789", end, new Turma[] {t1, t2});

        String message = "Verifique a ordem dos parâmetros: (id, nome, cpf, endereco, turmas[])";
        assertThat(hulk.id).as(message).isEqualTo(10);
        assertThat(hulk.nome).as(message).isEqualTo("Bruce Banner");
        assertThat(hulk.cpf).as(message).isEqualTo("123789");
        assertThat(hulk.endereco).as(message).isEqualTo(end);
        assertThat(hulk.turmas).as("As turmas recebidas do construtor estão sendo colocadas no atributo?").hasSize(2);
        assertThat(hulk.turmas).as("As turmas recebidas do construtor estão sendo colocadas no atributo?").contains(t1);
        assertThat(hulk.turmas).as("As turmas recebidas do construtor estão sendo colocadas no atributo?").contains(t2);
    }

    @Test
    public void endereco_locate_on_map() {
        // logradouro, numero, complemento, bairro, cidade, estado
        Endereco end = new Endereco("BR 101", "numero 320", "sem complemento", "b Saramandaia", "c Igarassu", "e Pernambuco");
        String mapStr = end.locateOnMap();
        assertThat(mapStr).as("String mal formatada").isEqualTo("https://www.google.com/maps/search/?api=1&query=BR+101+numero+320+b+Saramandaia+c+Igarassu+e+Pernambuco");
    }

    @Test
    public void pessoa_esta_no_predio_deve_ser_false_a_principio() {
        Pessoa p = new Pessoa();
        assertThat(p.estaNoPredio()).isEqualTo(false);
    }

    @Test
    public void pessoa_entrar_deve_fazer_esta_no_predio_retornar_true() {
        Pessoa p = new Pessoa();
        p.entrar();
        assertThat(p.estaNoPredio()).isEqualTo(true);
    }

    @Test
    public void pessoa_sair_deve_fazer_esta_no_predio_retornar_false() {
        Pessoa p = new Pessoa();
        p.sair();
        assertThat(p.estaNoPredio()).isEqualTo(false);
        p.entrar();
        p.sair();
        assertThat(p.estaNoPredio()).isEqualTo(false);
    }

    @Test
    public void estudante_get_presencas_deve_retornar_zero_a_principio() {
        Estudante e = new Estudante(new Pessoa(), "");
        assertThat(e.getPresencas()).isEqualTo(0);
    }

    @Test
    public void estudante_get_faltas_deve_retornar_zero_a_principio() {
        Estudante e = new Estudante(new Pessoa(), "");
        assertThat(e.getFaltas()).isEqualTo(0);
    }

    @Test
    public void estudante_contar_presenca_deve_incrementar_a_quantidade_de_presencas_em_uma_unidade_e_nao_interferir_na_quantidade_de_faltas() {
        Estudante e = new Estudante(new Pessoa(), "") {{
            contarPresenca();
        }};
        assertThat(e.getPresencas()).isEqualTo(1);
        assertThat(e.getFaltas()).isEqualTo(0);

        e.contarPresenca();
        assertThat(e.getPresencas()).isEqualTo(2);
        assertThat(e.getFaltas()).isEqualTo(0);
    }

    @Test
    public void estudante_contar_falta_deve_incrementar_a_quantidade_de_faltas_em_uma_unidade_e_nao_interferir_na_quantidade_de_presencas() {
        Estudante e = new Estudante(new Pessoa(), "") {{
            contarFalta();
        }};
        assertThat(e.getFaltas()).isEqualTo(1);
        assertThat(e.getPresencas()).isEqualTo(0);

        e.contarFalta();
        assertThat(e.getFaltas()).isEqualTo(2);
        assertThat(e.getPresencas()).isEqualTo(0);
    }

    @Test
    public void turma_realizar_chamada_deve_aumentar_a_quantidade_de_presencas_ou_faltas_dos_estudantes_que_forem_adicionados_pelo_metodo_adicionar_estudante() {
        Estudante ppp = new Estudante(new Pessoa(), "") {{
            entrar();
        }};

        Estudante paa = new Estudante(new Pessoa(), "") {{
            entrar();
        }};

        Estudante app = new Estudante(new Pessoa(), "");

        Estudante aaa = new Estudante(new Pessoa(), "");

        Turma turma = new Turma("", "", "") {{
            adicionarEstudante(ppp);
            adicionarEstudante(paa);
            adicionarEstudante(app);
            adicionarEstudante(aaa);
        }};

        turma.realizarChamada();

        ppp.entrar();
        paa.sair();
        app.entrar();
        aaa.sair();

        turma.realizarChamada();

        ppp.entrar();
        paa.sair();
        app.entrar();
        aaa.sair();

        turma.realizarChamada();

        assertThat(ppp.getPresencas()).isEqualTo(3);
        assertThat(ppp.getFaltas()).isEqualTo(0);

        assertThat(paa.getPresencas()).isEqualTo(1);
        assertThat(paa.getFaltas()).isEqualTo(2);

        assertThat(app.getPresencas()).isEqualTo(2);
        assertThat(app.getFaltas()).isEqualTo(1);

        assertThat(aaa.getPresencas()).isEqualTo(0);
        assertThat(aaa.getFaltas()).isEqualTo(3);
    }

}