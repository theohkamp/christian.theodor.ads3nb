package br.com.fecaf.model;

import java.util.Scanner;

public class Triangulo {

    public double base, lado2, lado3, area, perimetro, altura;

    Scanner scanner = new Scanner(System.in);

    public boolean cadastrarTriangulo() {
        System.out.println("/************************/");
        System.out.println("/*   Cadastro Triângulo */");
        System.out.println("/************************/");
        System.out.print("/* Informe o Base:     */");
        base = scanner.nextDouble();
        System.out.print("/* Informe o lado 2:     */");
        lado2 = scanner.nextDouble();
        System.out.print("/* Informe o lado 3:   */");
        lado3 = scanner.nextDouble();
        System.out.print("Informe a Altura: ");
        altura = scanner.nextDouble();
        System.out.println("/* Triângulo Cadastrado com Sucesso !");
        System.out.println("/***********************************/");

        return true;
    }

    public void calcularArea() {
        System.out.println("/*********************************/");
        System.out.println("/*        Calculando Area        */");
        System.out.println("/*********************************/");

        area = (base * altura) / 2;
        System.out.println("A área é: " + area);
        System.out.println("/*********************************/");
    }

    public void calcularPerimetro() {
        System.out.println("/*********************************/");
        System.out.println("/*      Calculando Perimetro     */");
        System.out.println("/*********************************/");

        perimetro = base + lado2 + lado3;
        System.out.println("O perimetro é: " + perimetro);
        System.out.println("/*********************************/");
    }

    // Isosceles / Escaleno / Equilatero
    public void definirTipo() {
        System.out.println("/*********************************/");
        System.out.println("/*        Definindo Tipo         */");
        System.out.println("/*********************************/");

        if (base == lado2 && base == lado3) {

            System.out.println("Este Triângulo é Equilátero ...");

        } else if (base != lado2 && base != lado3 && lado2 != lado3) {

            System.out.println("Este Triângulo é Escaleno ...");

        } else {
            System.out.println("Este Triângulo é Isosceles ...");
        }
        System.out.println("/***************************************/");

    }

    // Triângulo Retângulo

    public boolean definirTrianguloRetangulo() {
        System.out.println("/*********************************/");
        System.out.println("/*        Calculando Arestas     */");
        System.out.println("/*********************************/");

        //Aqui usaremos o Teorema de Pitágora H² = c² + c²

        if (base * base == lado2 * lado2 + lado3 * lado3) {
            System.out.println("É um Triângulo Retângulo ...");
            return true;

        } else if (lado2 * lado2 == base * base + lado3 * lado3) {
            System.out.println("É um Triângulo Retângulo ...");
            return true;

        } else if (lado3 * lado3 == base * base + lado2 * lado2) {
            System.out.println("É um Triângulo Retângulo ...");
            return true;

        } else {
            System.out.println("Não é um Triângulo Retângulo ...");
            return false;
        }
    }

    // Triângulo 3 - 4 - 5

    public boolean definir345() {

        System.out.println("/*********************************/");
        System.out.println("/*        Definindo Lados        */");
        System.out.println("/*********************************/");


        // Aqui ordenamos os valores que recebemos, em maior, menor e meio

        double menor, meio, maior;
        if (base <= lado2 && base <= lado3) {


            menor = base;
            if (lado2 <= lado3) {
                meio = lado2;
                maior = lado3;
            } else {
                meio = lado3;
                maior = lado2;
            }
        } else if (lado2 <= base && lado2 <= lado3) {
            menor = lado2;
            if (base <= lado3) {
                meio = base;
                maior = lado3;
            } else {
                meio = lado3;
                maior = base;
            }
        } else {
            menor = lado3;
            if (base <= lado2) {
                meio = base;
                maior = lado2;
            } else {
                meio = lado2;
                maior = base;
            }
        }

        // Aqui dividimos os valores já em ordem, para sabermos se respeita a proporção
        // Realizaremos também o cálculo do Teorema de Pitágoras novamente, para confirmar que se encaixa na condição de Triângulo Retângulo

        double igualdade1 = meio / menor;
        double igualdade2 = maior / menor;

        if (igualdade1 == 4.0 / 3.0 && igualdade2 == 5.0 / 3.0 && menor * menor + meio * meio == maior * maior) {
            System.out.println("É um Triângulo 3 - 4 - 5 ...");
            return true;
        } else
            System.out.println("Não é um Triângulo 3 - 4 - 5 ...");
            return false;
    }
}

