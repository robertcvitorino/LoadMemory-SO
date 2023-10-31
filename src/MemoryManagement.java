import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoryManagement {
    public static int getPageNumber(int address, int pageSize) {
        // calcula os bits do tamanho da página
        int numberOfPageBits = (int) (Math.log(pageSize) / Math.log(2));

        //máscara dos  bits menos significativos
        int mask = (1 << numberOfPageBits) - 1;

        // desloca para a direita para encontrat  o número da página
        int pageNumber = address >> numberOfPageBits;

        // bit relevantes
        int pageNumberWithMask = pageNumber & mask;

        return pageNumberWithMask;
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