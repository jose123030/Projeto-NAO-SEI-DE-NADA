package structure;

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

    private static final HashMap<String, Class> ENDERECO = new HashMap<String, Class>() {{
        put("logradouro", String.class);
        put("numero", String.class);
        put("complemento", String.class);
        put("bairro", String.class);
        put("cidade", String.class);
        put("estado", String.class);
    }};

    private static final HashMap<String, Class> TURMA = new HashMap<String, Class>() {{
        put("nome", String.class);
        put("descricao", String.class);
        put("turno", String.class);
    }};

    private static final HashMap<String, Class> PESSOA = new HashMap<String, Class>() {{
        put("id", int.class);
        put("nome", String.class);
        put("cpf", String.class);
        put("endereco", Endereco.class);
    }};

    private static final HashMap<String, Class> FUNCIONARIO = new HashMap<String, Class>() {{
        put("id", int.class);
        put("nome", String.class);
        put("cpf", String.class);
        put("endereco", Endereco.class);
        put("funcao", String.class);
    }};

    private static final HashMap<String, Class> ESTUDANTE = new HashMap<String, Class>() {{
        put("id", int.class);
        put("nome", String.class);
        put("cpf", String.class);
        put("endereco", Endereco.class);
        put("matricula", String.class);
    }};

    private static final HashMap<String, Class> PROFESSOR = new HashMap<String, Class>() {{
        put("id", int.class);
        put("nome", String.class);
        put("cpf", String.class);
        put("endereco", Endereco.class);
        put("turmas", ArrayList.class);
    }};





    private static void assertPublicAttributes(Class klass, HashMap<String, Class> map) {
        String attrs[] = map.keySet().toArray(new String[0]);

        assertThat(klass.getFields()).as("Classe '" + klass.getName() + "' deveria ter " + attrs.length + " atributos públicos (incluindo os herdados)").hasSize(attrs.length);

        for (String attr: attrs) {
            assertThat(klass).as("Classe '" + klass.getName() + "' / falta atributo '" + attr + "'").hasFields(attr);
        }
    }

    private static void assertPublicAttributesTypes(Class klass, HashMap<String, Class> map) {
        Field fields[] = klass.getFields();
        for (Field field: fields) {
            String msg = "Classe '" + klass.getName() + "' / atributo '" + field.getName() + "' nao eh '" + map.get(field.getName()) + "'";
            assertThat(field.getType()).as(msg).isEqualTo(map.get(field.getName()));
        }
    }

    private static void assertClass(Class klass, HashMap<String, Class> map) {
        assertPublicAttributes(klass, map);
        assertPublicAttributesTypes(klass, map);
    }

    private static void assertClass(Class klass, HashMap<String, Class> map, Class superklass) {
        assertThat(klass.getSuperclass()).as("Class '"+ klass.getName() + "' deveria estender '" + superklass.getName() + "'").isEqualTo(superklass);
        assertClass(klass, map);
    }

    private static void assertConstructors(Class klass, Class[][] constrParameters) {
        Constructor constructors[] = klass.getConstructors();

        assertThat(constructors).as("Classe '" + klass.getName() + "' deveria ter " + constrParameters.length + " construtor(es)").hasSize(constrParameters.length);

        for(Constructor constructor: constructors) {
            assertThat(constructor.getParameterTypes()).isIn((Object[])constrParameters);
        }
    }

    private static Class getMethodReturn(Class klass, String methodName, Class[] parameters) {
        try {
            return klass.getDeclaredMethod(methodName, parameters).getReturnType();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static void assertMethodSignature(Class klass, String methodName) {
        assertMethodSignature(klass, methodName, void.class, new Class[] {});
    }

    private static void assertMethodSignature(Class klass, String methodName, Class returnType) {
        assertMethodSignature(klass, methodName, returnType, new Class[] {});
    }

    private static void assertMethodSignature(Class klass, String methodName, Class[] parameters) {
        assertMethodSignature(klass, methodName, void.class, parameters);
    }

    private static void assertMethodSignature(Class klass, String methodName, Class returnType, Class[] parameters) {
        List<String> methodNames = Arrays.asList(klass.getDeclaredMethods())
                                         .stream()
                                         .map(m -> m.getName())
                                         .collect(Collectors.toList());

        List<Method> methods = Arrays.asList(klass.getDeclaredMethods())
                                     .stream()
                                     .filter(m -> m.getName().equals(methodName))
                                     .collect(Collectors.toList());

        List<Class[]> mParametersList = methods.stream()
                                           .map(m -> m.getParameterTypes())
                                           .collect(Collectors.toList());

        List<String> paramClassNames = Arrays.asList(parameters)
                                             .stream()
                                             .map(c -> c.getName())
                                             .collect(Collectors.toList());

        String absentMethod = "Classe '" + klass.getName() + "' deveria ter o método '" + methodName + "'";
        assertThat(methodName).as(absentMethod).isIn(methodNames);

        String paramDescr = paramClassNames.isEmpty() ? "sem parâmetros" : "recebendo os parâmetros (" + String.join(", ", paramClassNames) + ")";
        String wrongParameters = "Classe '" + klass.getName() + "' deveria ter um método '" + methodName + "' "+ paramDescr;
        assertThat(parameters).as(wrongParameters).isIn(mParametersList);

        String wrongReturn = "Classe '" + klass.getName() + "' deveria ter um método '" + methodName + "' " + paramDescr + " retornando '" + returnType.getName() + "'";
        assertThat(getMethodReturn(klass, methodName, parameters)).as(wrongReturn).isEqualTo(returnType);

    }







    @Test
    public void atrubutos_da_classe_turma() {
        assertClass(Turma.class, TURMA);
    }

    @Test
    public void atributos_da_classe_endereco() {
        assertClass(Endereco.class, ENDERECO);
    }

    @Test
    public void atributos_da_classe_pessoa() {
        assertClass(Pessoa.class, PESSOA);
    }

    @Test
    public void atributos_da_classe_funcionario() {
        assertClass(Funcionario.class, FUNCIONARIO, Pessoa.class);
    }

    @Test
    public void atributos_da_classe_estudante() {
        assertClass(Estudante.class, ESTUDANTE, Pessoa.class);
    }

    @Test
    public void atributos_da_classe_professor() {
        assertClass(Professor.class, PROFESSOR, Pessoa.class);
    }



    @Test
    public void construtores_da_classe_turma() {
        Class[][] constrParameters = {
            {String.class, String.class, String.class}
        };
        assertConstructors(Turma.class, constrParameters);
    }

    @Test
    public void construtores_da_classe_endereco() {
        Class[][] constrParameters = {
            {String.class, String.class, String.class, String.class, String.class, String.class}
        };
        assertConstructors(Endereco.class, constrParameters);
    }

    @Test
    public void construtores_da_classe_pessoa() {
        Class[][] constrParameters = {
            {},
            {int.class, String.class, String.class, Endereco.class}
        };
        assertConstructors(Pessoa.class, constrParameters);
    }

    @Test
    public void construtores_da_classe_funcionario() {
        Class[][] constrParameters = {
            {Pessoa.class, String.class},
            {int.class, String.class, String.class, Endereco.class, String.class}
        };
        assertConstructors(Funcionario.class, constrParameters);
    }

    @Test
    public void construtores_da_classe_estudante() {
        Class[][] constrParameters = {
            {Pessoa.class, String.class},
            {int.class, String.class, String.class, Endereco.class, String.class}
        };
        assertConstructors(Estudante.class, constrParameters);
    }

    @Test
    public void construtores_da_classe_professor() {
        Class[][] constrParameters = {
            {Pessoa.class, Turma[].class},
            {int.class, String.class, String.class, Endereco.class, Turma[].class}
        };
        assertConstructors(Professor.class, constrParameters);
    }



    @Test
    public void metodos_da_classe_turma() {
        assertMethodSignature(Turma.class, "adicionarEstudante", new Class[] {Estudante.class});
        assertMethodSignature(Turma.class, "realizarChamada");
    }

    @Test
    public void metodos_da_classe_endereco() {
        assertMethodSignature(Endereco.class, "locateOnMap", String.class);
    }

    @Test
    public void metodos_da_classe_pessoa() {
        assertMethodSignature(Pessoa.class, "entrar");
        assertMethodSignature(Pessoa.class, "sair");
        assertMethodSignature(Pessoa.class, "estaNoPredio", boolean.class);
    }

    @Test
    public void metodos_da_classe_estudante() {
        assertMethodSignature(Estudante.class, "contarPresenca");
        assertMethodSignature(Estudante.class, "contarFalta");
        assertMethodSignature(Estudante.class, "getPresencas", int.class);
        assertMethodSignature(Estudante.class, "getFaltas", int.class);
    }
}