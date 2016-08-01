package br.edu.ifrn.flavio.quizzfis.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BancoDadosPerguntas extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "banco_quizzfis";
    private static final String TB_PERGUNTAS = "tb_perguntas";
    private static final String CODIGO_PERGUNTA = "codigo_pergunta";
    private static final String PERGUNTA = "pergunta";
    private static final String RESPOSTA = "resposta";
    private static final String ASSERTIVA_A = "assertiva_a";
    private static final String ASSERTIVA_B = "assertiva_b";
    private static final String ASSERTIVA_C = "assertiva_c";
    private static final String ASSERTIVA_D = "assertiva_d";
    private static final String NIVEL_PERGUNTA = "nivel_pergunta";
    private SQLiteDatabase bancoDadosQuizzfis;

    String pergunta1 = "Com relação à massa e ao peso de um corpo, é correto afirmar que:";
    String resposta1 = "A massa é uma propriedade invariante de um corpo;";
    String alt1_1 = "A massa é uma propriedade invariante de um corpo;";
    String alt1_2 = "A massa de um corpo depende do valor local da gravidade g;";
    String alt1_3 = "Massa e peso dependem do valor local da aceleração da gravidade g;";
    String alt1_4 = "Massa e peso independem do valor local da aceleração da gravidade g;";

    String pergunta2 = "Num ônibus, o motorista acelera o veículo, fazendo com que os " +
            "passageiros experimentem uma força que os impele para trás. Assinale a " +
            "alternativa correta:";
    String resposta2 = "A força que os passageiros experimentam é de natureza fictícia;";
    String alt2_1 = "A força que os passageiros experimentam é de natureza fictícia;";
    String alt2_2 = "A força que os passageiros experimentam depende do peso de cada passageiro;";
    String alt2_3 = "A força que os passageiros experimentam é real;";
    String alt2_4 = "A força que os passageiros experimentam é elétrica;";

    String pergunta3 = "Na natureza existem vários tipos de forças que atuam sobre a" +
            " matéria, algumas são de atração e outras são de repulsão. A força que atua na" +
            " matéria para manter todos os átomos unidos, é a força:";
    String resposta3 = "elétrica;";
    String alt3_1 = "elétrica;";
    String alt3_2 = "gravitacional;";
    String alt3_3 = "magnética;";
    String alt3_4 = "nuclear;";

    String pergunta4 = "Um cavaleiro é lançado para frente quando o cavalo pára de repente. " +
            "O fato dele não parar ao mesmo tempo que o cavalo pode ser atribuído a seu(sua):";
    String resposta4 = "massa;";
    String alt4_1 = "massa;";
    String alt4_2 = "peso;";
    String alt4_3 = "altura;";
    String alt4_4 = "impulso;";

    String pergunta5 = "Na constituição dos átomos, os prótons e os nêutrons concentram-se " +
            "no núcleo atômico. Se os prótons se repelem mutuamente, pode-se dizer que os " +
            "núcleos atômicos conseguem se manter coesos principalmente devido:";
    String resposta5 = "ao aparecimento de uma força nuclear atrativa;";
    String alt5_1 = "ao aparecimento de uma força nuclear atrativa;";
    String alt5_2 = "à ação dos elétrons presentes na eletrosfera;";
    String alt5_3 = "à carga elétrica dos nêutrons;";
    String alt5_4 = "à formação de uma cápsula nuclear muito resistente;";

    String pergunta6 = "A unidade de força, no Sistema Internacional de unidades, é o:";
    String resposta6 = "newton;";
    String alt6_1 = "newton;";
    String alt6_2 = "pascal;";
    String alt6_3 = "dinamômetro;";
    String alt6_4 = "kelvin;";

    String pergunta7 = "Um bloco se encontra em repouso, apoiado sobre um piso plano" +
            " e horizontal, sem atrito. É correto afirmar que a menor força capaz de " +
            "deslocar esse bloco é:";
    String resposta7 = "qualquer força, desde que haja uma componente horizontal;";
    String alt7_1 = "qualquer força, desde que haja uma componente horizontal;";
    String alt7_2 = "uma força que depende da natureza das superfícies de contato;";
    String alt7_3 = "igual à força de atrito estático máxima;";
    String alt7_4 = "uma força proporcional à reação normal de apoio;";

    String pergunta8 = "Em um ônibus com velocidade constante, os passageiros em seu " +
            "interior encontram-se de pé e em repouso entre eles. Em uma freada brusca, os " +
            "passageiros deslocam-se para frente do ônibus. Esse deslocamento se deu porque:";
    String resposta8 = "os passageiros em movimento retilíneo e uniforme tendem a continuar " +
            "esse movimento;";
    String alt8_1 = "os passageiros em movimento retilíneo e uniforme tendem a continuar" +
            " esse movimento;";
    String alt8_2 = "agiu uma força em cada um deles, provocando movimento;";
    String alt8_3 = "houve ação do assoalho do ônibus sobre cada passageiro;";
    String alt8_4 = "cada passageiro possuía energia armazenada com o movimento do ônibus;";

    String pergunta9 = "Assinale a alternativa que apresenta o conceito ou princípio que " +
            "explica porque os passageiros de ônibus vão para frente quando esse ônibus freia.";
    String resposta9 = "Inércia;";
    String alt9_1 = "Inércia;";
    String alt9_2 = "Queda Livre;";
    String alt9_3 = "Princípio da Incerteza;";
    String alt9_4 = "Princípio Fundamental da Dinâmica;";

    String pergunta10 = "Segundo a primeira lei de Newton, é correto afirmar que:";
    String resposta10 = "uma partícula com o vetor velocidade constante tem a força resultante," +
            " agindo sobre ela, nula;";
    String alt10_1 = "uma partícula com o vetor velocidade constante tem a força resultante," +
            " agindo sobre ela, nula;";
    String alt10_2 = "uma partícula com sua velocidade constante tem a força resultante," +
            " agindo sobre ela, não nula;";
    String alt10_3 = "uma partícula com sua velocidade constante tem a força resultante, " +
            "agindo sobre ela, nula;";
    String alt10_4 = "uma partícula com sua velocidade variável tem a força resultante, " +
            "agindo sobre ela, nula;";

    String pergunta11 = "Na ausência de forças ou força resultante nula, um corpo em " +
            "repouso continua em repouso e um corpo em movimento move-se em MRU. Essa lei se " +
            "associa ao conceito de:";
    String resposta11 = "inércia de Galileu;";
    String alt11_1 = "inércia de Galileu;";
    String alt11_2 = "pressão de Pascal;";
    String alt11_3 = "impulso de Aristóteles;";
    String alt11_4 = "empuxo de Arquimedes;";

    String pergunta12 = "A 1ª Lei de Newton afirma que, se a soma de todas as forças atuando " +
            "sobre o corpo é zero, o mesmo:";
    String resposta12 = "apresentará velocidade constante;";
    String alt12_1 = "apresentará velocidade constante;";
    String alt12_2 = "terá um movimento uniformemente variado;";
    String alt12_3 = "apresentará velocidade constante em módulo, mas sua direção pode ser alterada;";
    String alt12_4 = "será desacelerado;";

    String pergunta13 = "Qual lei da Física explica o fato de o cinto de segurança de um " +
            "automóvel garantir a segurança de passageiros numa colisão?";
    String resposta13 = "Princípio da inércia;";
    String alt13_1 = "Princípio da inércia;";
    String alt13_2 = "Princípio fundamental da dinâmica;";
    String alt13_3 = "Ação e Reação;";
    String alt13_4 = "Conservação da energia mecânica;";

    String pergunta14 = "Em plano de inclinação 30&deg; e sem atrito, um corpo é lançado para " +
            "cima com velocidade inicial igual a 3,0 m/s, se  g = 10 m/s<sup>2</sup>, sen 30&deg; =" +
            " 0,50 e cos 30&deg; = 0,87, qual a distancia percorrida pelo corpo?";
    String resposta14 = "90 cm;";
    String alt14_1 = "90 cm;";
    String alt14_2 = "45 cm;";
    String alt14_3 = "30 cm;";
    String alt14_4 = "95 cm;";

    String pergunta15 = "Considere a situação em que um bloco de 5 kg e um bloco de 10 " +
            "kg deslizam, para baixo, por um plano inclinado sem atrito. Pode-se afirmar que:";
    String resposta15 = "ambos têm a mesma aceleração;";
    String alt15_1 = "ambos têm a mesma aceleração;";
    String alt15_2 = "o bloco de 5 kg tem o dobro da aceleração do bloco de 10 kg;";
    String alt15_3 = "o bloco de 10 kg tem o dobro da aceleração do bloco de 5 kg;";
    String alt15_4 = "a aceleração dos blocos depende da força normal do plano sobre eles;";

    String pergunta16 = "Dentre as situações apresentadas a seguir, assinale aquela em que o " +
            "objeto se encontra em equilíbrio";
    String resposta16 = "Um carro movendo-se com velocidade constante em uma estrada reta horizontal;";
    String alt16_1 = "Um carro movendo-se com velocidade constante em uma estrada reta horizontal;";
    String alt16_2 = "Um satélite em órbita circular ao redor da Terra;";
    String alt16_3 = "Uma bola em queda livre para o chão;";
    String alt16_4 = "Um projétil no ponto mais alto de sua trajetória;";

    String pergunta17 = "Um veículo espacial de 1.200 Kg pesa na superfície plana de Marte " +
            "4400 N. Qual o valor da aceleração gravitacional, em m/s<sup>2</sup>, na superfície " +
            "do planeta:";
    String resposta17 = "3,7;";
    String alt17_1 = "3,7;";
    String alt17_2 = "32;";
    String alt17_3 = "9,8;";
    String alt17_4 = "98;";

    String pergunta18 = "Qual par de forças abaixo representa um par de ação e reação?";
    String resposta18 = "A força de atração que a Terra faz sobre um bloco e a força de atração" +
            " que o bloco faz sobre a Terra;";
    String alt18_1 = "A força de atração que a Terra faz sobre um bloco e a força de atração" +
            " que o bloco faz sobre a Terra;";
    String alt18_2 = "O peso do bloco e a reação normal da mesa sobre o bloco;";
    String alt18_3 = "O peso de um navio e o empuxo que a água faz sobre a embarcação;";
    String alt18_4 = "Uma força horizontal puxando um bloco sobre uma mesa e a força de atrito " +
            "da mesa sobre o bloco;";

    String pergunta19 = "Um estudante, analisando a situação de um objeto em repouso sobre " +
            "uma mesa horizontal, chegou a algumas conclusões. Assinale a opção <b>correta</b>.";
    String resposta19 = "O Peso é a força resultante da ação gravitacional sobre todas as " +
            "partes de um corpo;";
    String alt19_1 = "O Peso é a força resultante da ação gravitacional sobre todas as " +
            "partes de um corpo;";
    String alt19_2 = "O Peso e a Normal formam um par Ação e Reação;";
    String alt19_3 = "A Normal e o Peso são sempre iguais;";
    String alt19_4 = "A Normal é uma força de interação à distância;";

    String pergunta20 = "Um estudante com massa de 70 kg está dentro de um elevador em " +
            "uma balança que marca 60 kg. O estudante conclui que o elevador está:";
    String resposta20 = "descendo e aumentando sua velocidade;";
    String alt20_1 = "descendo e aumentando sua velocidade;";
    String alt20_2 = "descendo com velocidade constante;";
    String alt20_3 = "subindo e aumentando a velocidade;";
    String alt20_4 = "subindo com velocidade constante;";

    String pergunta21 = "No teto de um elevador, um dinamômetro acusa 2,0 N o peso de um " +
            "corpo que na verdade tem 1,6 N. Isso acontece porque o elevador está:";
    String resposta21 = "subindo com velocidade crescente;";
    String alt21_1 = "subindo com velocidade crescente;";
    String alt21_2 = "subindo com velocidade constante;";
    String alt21_3 = "descendo com velocidade constante;";
    String alt21_4 = "descendo com velocidade crescente;";

    String pergunta22 = "Um paraquedista salta de um avião e cai em queda livre até sua " +
            "velocidade de queda se tornar constante. A força resultante atuando sobre o " +
            "paraquedista após sua velocidade se tornar constante é:";
    String resposta22 = "nula;";
    String alt22_1 = "nula;";
    String alt22_2 = "vertical e para baixo;";
    String alt22_3 = "vertical e para cima;";
    String alt22_4 = "horizontal e para a direita;";

    String pergunta23 = "Um levantador de peso, com massa corpórea igual a 80Kg é capaz" +
            " de levantar uma carga igual a 2.400N. Ao exercer uma força sobre um dinamômetro " +
            "preso ao teto de uma sala, o valor máximo que esse levantador consegue puxar é:";
    String resposta23 = "800N;";
    String alt23_1 = "800N;";
    String alt23_2 = "2.400N;";
    String alt23_3 = "10N;";
    String alt23_4 = "24.000N;";

    String pergunta24 = "Um corpo de massa 3 kg sobre ação de uma força e com aceleração de" +
            " 2m/s<sup>2</sup>. Qual a intensidade da força?";
    String resposta24 = "6N;";
    String alt24_1 = "6N;";
    String alt24_2 = "3N;";
    String alt24_3 = "4N;";
    String alt24_4 = "7N;";

    String pergunta25 = "O trabalho realizado por uma força conservativa independe da " +
            "trajetória, o que não acontece com as forças dissipativas, cujo trabalho realizado" +
            " depende da trajetória. São bons exemplos de forças conservativas e dissipativas," +
            " respectivamente:";
    String resposta25 = "peso e resistência do ar;";
    String alt25_1 = "peso e resistência do ar;";
    String alt25_2 = "peso e massa;";
    String alt25_3 = "força de contato e força normal;";
    String alt25_4 = "força centrípeta e força centrífuga;";

    String pergunta26 = "Dizemos que trabalho é uma grandeza que mede a quantidade de um" +
            " tipo de energia que se transforma em outro tipo de energia. Dentre as situações" +
            " abaixo, a <b>ÚNICA</b> alternativa em que ocorre a realização de um trabalho é?";
    String resposta26 = "Uma lâmpada acesa no interior de uma residência;";
    String alt26_1 = "Uma lâmpada acesa no interior de uma residência;";
    String alt26_2 = "Uma xícara de café quente colocada sobre uma mesa;";
    String alt26_3 = "Um cubo de gelo colocado dentro de um copo com água;";
    String alt26_4 = "Um ovo no interior de uma vasilha com água quente;";

    String pergunta27 = "Trabalho mecânico é feito quando:";
    String resposta27 = "uma força move um objeto;";
    String alt27_1 = "uma força move um objeto;";
    String alt27_2 = "um objeto se move;";
    String alt27_3 = "uma força é aplicada a um objeto;";
    String alt27_4 = "a energia não se conserva;";

    String pergunta28 = "Ao lançarmos uma bola verticalmente para cima no campo" +
            "gravitacional terrestre, suposto constante, a força peso durante a subida realiza" +
            " um trabalho:";
    String resposta28 = "resistente;";
    String alt28_1 = "resistente;";
    String alt28_2 = "motor;";
    String alt28_3 = "nulo;";
    String alt28_4 = "que independe da velocidade com que a bola foi lançada;";

    String pergunta29 = "Analise as alternativas e assinale a correta.";
    String resposta29 = "O trabalho de forças externas que agem sobre um sólido é igual ao" +
            " incremento de sua energia cinética;";
    String alt29_1 = "O trabalho de forças externas que agem sobre um sólido é igual ao " +
            "incremento de sua energia cinética;";
    String alt29_2 = "No movimento circular uniforme a força centrípeta equilibra a força centrífuga;";
    String alt29_3 = "Impulso é sinônimo de Quantidade de Movimento;";
    String alt29_4 = "No movimento oscilatório de um pêndulo simples são feitos percursos iguais" +
            " em tempos iguais, portanto, o movimento pendular é uniforme;";

    String pergunta30 = "No Brasil, o sistema de transporte depende do uso de " +
            "combustíveis fósseis e de biomassa, cuja energia é convertida em movimento de" +
            " veículos. Para esses combustíveis, a transformação de energia química em energia" +
            " mecânica acontece:";
    String resposta30 = "na combustão, que gera gases quentes para mover os pistões no motor;";
    String alt30_1 = "na combustão, que gera gases quentes para mover os pistões no motor;";
    String alt30_2 = "nos eixos, que transferem torque às rodas e impulsionam o veículo;";
    String alt30_3 = "na ignição, quando a energia elétrica é convertida em trabalho;";
    String alt30_4 = "na exaustão, quando gases quentes são expelidos para trás;";

    String pergunta31 = "O trabalho realizado por uma força conservativa independe da" +
            " trajetória, o que não acontece com as forças dissipativas, cujo trabalho realizado" +
            " depende da trajetória. São bons exemplos de forças conservativas e dissipativas," +
            " respectivamente:";
    String resposta31 = "peso e resistência do ar;";
    String alt31_1 = "peso e massa;";
    String alt31_2 = "peso e resistência do ar;";
    String alt31_3 = "força de contato e força normal;";
    String alt31_4 = "força elástica e força centrípeta;";

    String pergunta32 = "Se a velocidade de um corpo de 2 kg varia de 36 km/h para 72 km/h, o " +
            "trabalho total realizado sobre o corpo é de:";
    String resposta32 = "300 J;";
    String alt32_1 = "50 J;";
    String alt32_2 = "100 J;";
    String alt32_3 = "200 J;";
    String alt32_4 = "300 J;";

    String pergunta33 = "Um elevador sobe com velocidade constante levando uma pessoa em" +
            " seu interior. Quanto ao trabalho realizado pelas forças resultante e normal que " +
            "atuam na pessoa, é CORRETO afirmar que o trabalho da força resultante e o da força" +
            " normal é, respectivamente:";
    String resposta33 = "nulo e positivo;";
    String alt33_1 = "negativo e positivo;";
    String alt33_2 = "positivo e positivo;";
    String alt33_3 = "nulo e negativo;";
    String alt33_4 = "nulo e positivo;";

    String pergunta34 = "Visando à preservação do meio ambiente de forma sustentável, a " +
            "sociedade atual vem aumentando consideravelmente a utilização da energia dos " +
            "ventos, através das turbinas eólicas. Nessa tecnologia, a primeira transformação " +
            "de energia acontece na interação das moléculas do ar com as hélices dos cata-ventos," +
            " transformando a energia cinética de translação das moléculas do ar em energia " +
            "cinética de rotação das hélices. Nessa interação, ";
    String resposta34 = "a variação da quantidade de movimento das moléculas do ar gera uma força " +
            "resultante que atua sobre as hélices;";
    String alt34_1 = "a variação da quantidade de movimento das moléculas do ar gera uma força " +
            "resultante que atua sobre as hélices;";
    String alt34_2 = "a variação do momento angular das moléculas do ar gera uma força resultante " +
            "que atua sobre as hélices;";
    String alt34_3 = "a variação da força resultante exercida pelas moléculas do ar anula o" +
            " momento angular das hélices;";
    String alt34_4 = "a variação da força resultante exercida pelas moléculas do ar anula a" +
            " quantidade de movimento das hélices;";

    String pergunta35 = "Sobre a conservação de energia mecânica e o teorema " +
            "trabalho-energia, <b>ASSINALE</b> a alternativa que contém a informação correta";
    String resposta35 = ";";
    String alt35_1 = "A conservação de energia é válida mesmo quando forças não-conservativas" +
            " atuam no sistema;";
    String alt35_2 = "O teorema trabalho-energia só deve ser aplicado a um sistema que sofre a" +
            " ação de uma força constante;";
    String alt35_3 = "A conservação de energia de um sistema depende da trajetória das " +
            "partí-culas que compõem o sistema;";
    String alt35_4 = "O teorema trabalho-energia fornece o trabalho realizado por uma força," +
            " através da variação da energia cinética;";

    String pergunta36 = "Uma esfera metálica é lançada verticalmente para cima com " +
            "velocidade inicial vo, no vácuo, em ambiente onde a aceleração da gravidade é g." +
            " Considere a energia cinética como Ec , a energia potencial gravitacional como Ep " +
            "e a soma entre elas como a energia mecânica Em. Sobre este sistema é correto afirmar:";
    String resposta36 = "Na metade do tempo de subida a Ec alcança um valor correspondente à " +
            "quarta parte do seu valor inicial;";
    String alt36_1 = "Na metade do tempo de subida a esfera alcança a metade da altura máxima;";
    String alt36_2 = "Entre os instantes 0s e 4s, a energia mecânica aumenta;";
    String alt36_3 = "Na metade do tempo de subida a Ec alcança um valor correspondente à quarta" +
            " parte do seu valor inicial;";
    String alt36_4 = "Entre os instantes 4s e 8s, a Ec diminui, a Ep aumenta e a energia mecânica " +
            "fica constante;";

    String pergunta37 = "Um corpo de massa 200g, inicialmente em repouso, sob ação de uma " +
            "força constante, atinge em 2s a velocidade de 54 km/h. A força que atuou no objeto" +
            " durante esse intervalo de tempo tem valor igual a:";
    String resposta37 = "1,5 N;";
    String alt37_1 = "1500 N;";
    String alt37_2 = "5400 N;";
    String alt37_3 = "5,4 N;";
    String alt37_4 = "1,5 N;";

    String pergunta38 = "Assinale a afirmativa <b>CORRETA:</b>";
    String resposta38 = ";";
    String alt38_1 = "Conforme o princípio de conservação da energia mecânica, a soma da " +
            "variação da energia potencial com a variação da energia cinética de um corpo é " +
            "nula, quando apenas forças conservativas atuam no corpo;";
    String alt38_2 = "Conforme o princípio de conservação da energia mecânica, a soma da energia" +
            " potencial com a energia cinética de um corpo é nula, quando apenas forças" +
            " dissipativas atuam no corpo;";
    String alt38_3 = "A soma da energia potencial com a energia cinética de um corpo é nula, " +
            "quando apenas forças conservativas atuam no corpo;";
    String alt38_4 = "A soma da variação da energia potencial com a variação da energia cinética" +
            " de um corpo é nula, quando apenas forças dissipativas atuam no corpo;";

    String pergunta39 = "São apresentadas a seguir diversas afirmativas sobre o conceito de " +
            "energia. É <b>CORRETO</b> afirmar:";
    String resposta39 = ";";
    String alt39_1 = "O fato de a energia não se conservar justifica a necessidade que temos de" +
            " economizar energia;";
    String alt39_2 = "O calor é uma forma de energia mecânica;";
    String alt39_3 = "Todos os corpos têm energia térmica que aparece na forma de calor quando " +
            "são colocados em ambientes de altas temperaturas;";
    String alt39_4 = "Se forem consideradas todas as suas modalidades, a energia de um sistema" +
            " isolado sempre se conserva;";

    String pergunta40 = "Ao lançarmos uma bola verticalmente para cima no campo " +
            "gravitacional terrestre, suposto constante, a força peso durante a subida realiza" +
            " um trabalho.";
    String resposta40 = "resistente;";
    String alt40_1 = "Nenhuma das opções anteriores;";
    String alt40_2 = "motor;";
    String alt40_3 = "nulo;";
    String alt40_4 = "resistente;";

    public BancoDadosPerguntas(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bancoDadosPerguntas)
    {
        bancoDadosQuizzfis = bancoDadosPerguntas;
        String sql = "CREATE TABLE IF NOT EXISTS " + TB_PERGUNTAS + "(" + CODIGO_PERGUNTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PERGUNTA + " TEXT, " + RESPOSTA + " TEXT, " + ASSERTIVA_A + " TEXT, " + ASSERTIVA_B + " TEXT, " + ASSERTIVA_C + " TEXT, " + ASSERTIVA_D + " TEXT, " + NIVEL_PERGUNTA + " INTEGER);";
        bancoDadosPerguntas.execSQL(sql);
        adicionarQuestoes();
    }

    private void adicionarQuestoes()
    {
        Questao q1 = new Questao(pergunta1, alt1_1 ,alt1_2, alt1_3, alt1_4, resposta1, 0);
        this.addQuestion(q1);
        Questao q2 = new Questao(pergunta2, alt2_1, alt2_2, alt2_3, alt2_4, resposta2, 0);
        this.addQuestion(q2);
        Questao q3 = new Questao(pergunta3, alt3_1, alt3_2, alt3_3, alt3_4, resposta3, 0);
        this.addQuestion(q3);
        Questao q4 = new Questao(pergunta4, alt4_1, alt4_2, alt4_3, alt4_4, resposta4, 0);
        this.addQuestion(q4);
        Questao q5 = new Questao(pergunta5, alt5_1, alt5_2, alt5_3, alt5_4, resposta5, 0);
        this.addQuestion(q5);
        Questao q6 = new Questao(pergunta6, alt6_1, alt6_2, alt6_3, alt6_4, resposta6, 0);
        this.addQuestion(q6);
        Questao q7 = new Questao(pergunta7, alt7_1, alt7_2, alt7_3, alt7_4, resposta7, 0);
        this.addQuestion(q7);
        Questao q8 = new Questao(pergunta8, alt8_1, alt8_2, alt8_3, alt8_4, resposta8, 0);
        this.addQuestion(q8);
        Questao q9 = new Questao(pergunta9, alt9_1, alt9_2, alt9_3, alt9_4, resposta9, 0);
        this.addQuestion(q9);
        Questao q10 = new Questao(pergunta10, alt10_1, alt10_2, alt10_3, alt10_4, resposta10, 0);
        this.addQuestion(q10);
        Questao q11 = new Questao(pergunta11, alt11_1, alt11_2, alt11_3, alt11_4, resposta11, 0);
        this.addQuestion(q11);
        Questao q12 = new Questao(pergunta12, alt12_1 ,alt12_2, alt12_3, alt12_4, resposta12, 0);
        this.addQuestion(q12);
        Questao q13 = new Questao(pergunta13, alt13_1, alt13_2, alt13_3, alt13_4, resposta13, 0);
        this.addQuestion(q13);
        Questao q14 = new Questao(pergunta14, alt14_1, alt14_2, alt14_3, alt14_4, resposta14, 0);
        this.addQuestion(q14);
        Questao q15 = new Questao(pergunta15, alt15_1, alt15_2, alt15_3, alt15_4, resposta15, 0);
        this.addQuestion(q15);
        Questao q16 = new Questao(pergunta16, alt16_1, alt16_2, alt16_3, alt16_4, resposta16, 0);
        this.addQuestion(q16);
        Questao q17 = new Questao(pergunta17, alt17_1, alt17_2, alt17_3, alt17_4, resposta17, 0);
        this.addQuestion(q17);
        Questao q18 = new Questao(pergunta18, alt18_1, alt18_2, alt18_3, alt18_4, resposta18, 0);
        this.addQuestion(q18);
        Questao q19 = new Questao(pergunta19, alt19_1, alt19_2, alt19_3, alt19_4, resposta19, 0);
        this.addQuestion(q19);
        Questao q20 = new Questao(pergunta20, alt20_1, alt20_2, alt20_3, alt20_4, resposta20, 0);
        this.addQuestion(q20);
        Questao q21 = new Questao(pergunta21, alt21_1, alt21_2, alt21_3, alt21_4, resposta21, 0);
        this.addQuestion(q21);
        Questao q22 = new Questao(pergunta22, alt22_1, alt22_2, alt22_3, alt22_4, resposta22, 0);
        this.addQuestion(q22);
        Questao q23 = new Questao(pergunta23, alt23_1, alt23_2, alt23_3, alt23_4, resposta23, 0);
        this.addQuestion(q23);
        Questao q24 = new Questao(pergunta24, alt24_1, alt24_2, alt24_3, alt24_4, resposta24, 0);
        this.addQuestion(q24);
        Questao q25 = new Questao(pergunta25, alt25_1, alt25_2, alt25_3, alt25_4, resposta25, 0);
        this.addQuestion(q25);
        Questao q26 = new Questao(pergunta26, alt26_1 ,alt26_2, alt26_3, alt26_4, resposta26, 0);
        this.addQuestion(q26);
        Questao q27 = new Questao(pergunta27, alt27_1, alt27_2, alt27_3, alt27_4, resposta27, 0);
        this.addQuestion(q27);
        Questao q28 = new Questao(pergunta28, alt28_1, alt28_2, alt28_3, alt28_4, resposta28, 0);
        this.addQuestion(q28);
        Questao q29 = new Questao(pergunta29, alt29_1, alt29_2, alt29_3, alt29_4, resposta29, 0);
        this.addQuestion(q29);
        Questao q30 = new Questao(pergunta30, alt30_1, alt30_2, alt30_3, alt30_4, resposta30, 0);
        this.addQuestion(q30);
        Questao q31 = new Questao(pergunta31, alt31_1, alt31_2, alt31_3, alt31_4, resposta31, 0);
        this.addQuestion(q31);
        Questao q32 = new Questao(pergunta32, alt32_1, alt32_2, alt32_3, alt32_4, resposta32, 0);
        this.addQuestion(q32);
        Questao q33 = new Questao(pergunta33, alt33_1, alt33_2, alt33_3, alt33_4, resposta33, 0);
        this.addQuestion(q33);
        Questao q34 = new Questao(pergunta34, alt34_1, alt34_2, alt34_3, alt34_4, resposta34, 0);
        this.addQuestion(q34);
        Questao q35 = new Questao(pergunta35, alt35_1, alt35_2, alt35_3, alt35_4, resposta35, 0);
        this.addQuestion(q35);
        Questao q36 = new Questao(pergunta36, alt36_1, alt36_2, alt36_3, alt36_4, resposta36, 0);
        this.addQuestion(q36);
        Questao q37 = new Questao(pergunta37, alt37_1 ,alt37_2, alt37_3, alt37_4, resposta37, 0);
        this.addQuestion(q37);
        Questao q38 = new Questao(pergunta38, alt38_1, alt38_2, alt38_3, alt38_4, resposta38, 0);
        this.addQuestion(q38);
        Questao q39 = new Questao(pergunta39, alt39_1, alt39_2, alt39_3, alt39_4, resposta39, 0);
        this.addQuestion(q39);
        Questao q40 = new Questao(pergunta40, alt40_1, alt40_2, alt40_3, alt40_4, resposta40, 0);
        this.addQuestion(q40);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versaoAntiga, int novaVersao)
    {
        bd.execSQL("DROP TABLE IF EXISTS" + TB_PERGUNTAS + ";");
        onCreate(bd);
    }

    public void addQuestion(Questao questao)
    {
        ContentValues values = new ContentValues();
        values.put(PERGUNTA, questao.getQuestao());
        values.put(RESPOSTA, questao.getQuestaoResposta());
        values.put(ASSERTIVA_A, questao.getQuestaoAssertivaA());
        values.put(ASSERTIVA_B, questao.getQuestaoAssertivaB());
        values.put(ASSERTIVA_C, questao.getQuestaoAssertivaC());
        values.put(ASSERTIVA_D, questao.getQuestaoAssertivaD());
        values.put(NIVEL_PERGUNTA, questao.getQuestaoNivelPergunta());
        bancoDadosQuizzfis.insert(TB_PERGUNTAS, null, values);
    }

    public List<Questao> pegarTodasQuestoes()
    {
        List<Questao> listaBancoDadosPerguntasQuestoes = new ArrayList<>();

        String sql = "SELECT * FROM " + TB_PERGUNTAS + ";";
        this.bancoDadosQuizzfis = this.getReadableDatabase();
        Cursor cursor = bancoDadosQuizzfis.rawQuery(sql, null);
        List<Questao> teste = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do
            {
                Questao questao = new Questao();
                List<String> shuffle = new ArrayList<>();

                questao.setQuestao(cursor.getString(1));
                questao.setQuestaoResposta(cursor.getString(2));

                shuffle.add(cursor.getString(3));
                shuffle.add(cursor.getString(4));
                shuffle.add(cursor.getString(5));
                shuffle.add(cursor.getString(6));
                Collections.shuffle(shuffle);

                questao.setQuestaoAssertivaA(shuffle.get(0));
                questao.setQuestaoAssertivaB(shuffle.get(1));
                questao.setQuestaoAssertivaC(shuffle.get(2));
                questao.setQuestaoAssertivaD(shuffle.get(3));

                listaBancoDadosPerguntasQuestoes.add(questao);
            }
            while(cursor.moveToNext());
            for(int i = 0; i < listaBancoDadosPerguntasQuestoes.size(); i++)
            {
                teste.add(listaBancoDadosPerguntasQuestoes.get(i));
            }
        }
        Collections.shuffle(teste);
        return teste;
    }

    public int quantidadePerguntas()
    {
        int row = 0;
        try
        {
            String selectQuery = "SELECT  * FROM tb_perguntas;";
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            row = cursor.getCount();
        }
        catch (Exception e)
        {
        }
        finally
        {
        }
        return row;
    }
}