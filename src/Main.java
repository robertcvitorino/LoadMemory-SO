import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        initializeAppication();
    }

    public static void initializeAppication(){
        FileMemory.initializeFile();
        Scanner scanner = new Scanner(System.in);
        int option;
        System.out.println("Deseja ler os endereços de um arquivo ou digitar um endereço?");
        System.out.println("1 - Digitar endereço");
        System.out.println("2 - Ler arquivo");
        while (true) {
            System.out.print("Digite o número da opção desejada: ");
            option = scanner.nextInt();
            if (option == 1 || option == 2) {
                break;
            } else {
                System.out.println("Opção inválida. Por favor, escolha 1 ou 2.");
            }
        }
        if (option == 1) {
            System.out.print("Digite o endereço: ");
            int address = scanner.nextInt();
            MemoryManagement.processAddress(address);
        } else {
            System.out.print("Digite o número de bits do arquivo (16 ou 32): ");
            int bits = scanner.nextInt();
            if (bits != 16 && bits != 32) {
                System.out.println("Número de bits inválido, o programa será encerrado.");
                return;
            }
            int pageSize = 0;
            System.out.println("Qual o tamanho da paginação?");
            System.out.println("1 - 256");
            System.out.println("2 - 512");
            System.out.println("3 - 1024");
            System.out.println("4 - 2048");
            System.out.println("5 - 4096");

            while (true) {
                System.out.print("Digite o número da opção desejada: ");
                option = scanner.nextInt();
                if (option >= 1 && option <= 6) {
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, escolha entre 1 e 6.");
                }
            }
            if (option == 1) {
                pageSize = 256;
            } else if (option == 2) {
                pageSize = 512;
            } else if (option == 3) {
                pageSize = 1024;
            } else if (option == 4) {
                pageSize = 2048;
            } else if (option == 5) {
                pageSize = 4096;
            }
            FileMemory.processFile(bits, pageSize);
        }
    }
}




