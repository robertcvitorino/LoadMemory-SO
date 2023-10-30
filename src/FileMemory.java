import java.io.*;
import java.util.Random;

public class FileMemory {

    public static void processFile(int optionalFile) {
        try {
            FileReader fileReader ;

            if(optionalFile == 16){
                fileReader= new FileReader("addresses_16b.txt");
            }
            else{
                fileReader= new FileReader("addresses_32b.txt");
            }
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                int address = Integer.parseInt(line);
                MemoryManagement.processAddress(address);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processFile( int pageSize,int optionalFile) {
        try {
            FileReader fileReader ;

            if(optionalFile == 16){
                fileReader= new FileReader("addresses_16b.txt");
            }
            else{
                fileReader= new FileReader("addresses_32b.txt");
            }
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            System.out.println("Iniciando a escrita no arquivo output.txt");
            while ((line = bufferedReader.readLine()) != null) {
                int address = Integer.parseInt(line);

                MemoryManagement.processAddressFile(address, pageSize);
            }
            System.out.println("Escrita finalizada com sucesso!");

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static  void initializeFile(){
        // Excluir arquivos output.txt
        File outputFile = new File("output_memory_load.txt");
        if (outputFile.exists()) {
            outputFile.delete();
        }
        // Criar arquivo data_memory.txt com 10000 números aleatórios entre 0 e 100
        // Caso não exista, cria o arquivo
        File dataFile = new File("data_memory.txt");
        if (!dataFile.exists()) {
            try {
                FileWriter fileWriter = new FileWriter(dataFile);
                Random rand = new Random(1);
                for (int i = 0; i < 1000000; i++) {
                    int number = rand.nextInt(101);
                    fileWriter.write(number + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
