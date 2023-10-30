import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoryManagement {
    public static int getPageNumber(int address, int pageSize) {

        int mask = (1 << (int)(Math.log(pageSize) / Math.log(2))) - 1;

        return (address >> (int)(Math.log(pageSize) / Math.log(2))) & mask;
    }

    public static int getOffset(int address, int pageSize) {
        return address & (pageSize - 1);
    }

    public static void processAddress(int address) {
        processAddress(address, 0);
    }
    public static void processAddress(int address, int pageSize) {
        int pageSizeFinal = (pageSize == 0) ? 4096 : pageSize;
        int pageNumber = getPageNumber(address, pageSizeFinal);
        int offset = getOffset(address, pageSizeFinal);

        try {
            FileReader fileReader = new FileReader("data_memory.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int i = 0;
            int value = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (i == offset) {
                    value = Integer.parseInt(line);
                    break;
                }
                i++;
            }
            bufferedReader.close();
            System.out.print("O endereço " + address + " contém:\n\tnúmero da página = " + pageNumber + "\n\tdeslocamento = " + offset + "\n\tValor lido: " + value + "\n");
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processAddressFile(int address, int pageSize) {
        int pageSizeFinal = (pageSize == 0) ? 4096 : pageSize;
        int pageNumber = getPageNumber(address, pageSizeFinal);
        int offset = getOffset(address, pageSizeFinal);

        try {
            FileReader fileReader = new FileReader("data_memory.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int i = 0;
            int value = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (i == offset) {
                    value = Integer.parseInt(line);
                    break;
                }
                i++;
            }
            bufferedReader.close();

            FileWriter fileWriter = new FileWriter("output_memory_load.txt", true);
            fileWriter.write("O endereço " + address + " contém:\n\tnúmero da página = " + pageNumber + "\n\tdeslocamento = " + offset + "\n\tValor lido: " + value + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}