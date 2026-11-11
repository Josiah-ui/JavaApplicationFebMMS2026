// Exercise 5.34 (Table of Binary, Octal and Hexadecimal Numbers)
public class Exercise5_34 {
    public static void main(String[] args) {
        System.out.printf("%-10s%-12s%-8s%-12s%n", "Decimal", "Binary", "Octal", "Hexadecimal");
        for (int number = 1; number <= 256; number++) {
            System.out.printf("%-10d%-12s%-8s%-12s%n", number,
                    Integer.toBinaryString(number),
                    Integer.toOctalString(number),
                    Integer.toHexString(number).toUpperCase());
        }
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Decimal   Binary      Octal   Hexadecimal 
 * 1         1           1       1           
 * 2         10          2       2           
 * 3         11          3       3           
 * 4         100         4       4           
 * 5         101         5       5           
 * 6         110         6       6           
 * 7         111         7       7           
 * 8         1000        10      8           
 * 9         1001        11      9           
 * 10        1010        12      A           
 * 11        1011        13      B           
 * 12        1100        14      C           
 * 13        1101        15      D           
 * 14        1110        16      E           
 * 15        1111        17      F           
 * 16        10000       20      10          
 * 17        10001       21      11          
 * 18        10010       22      12          
 * 19        10011       23      13          
 * 20        10100       24      14          
 * 21        10101       25      15          
 * 22        10110       26      16          
 * 23        10111       27      17          
 * 24        11000       30      18          
 * 25        11001       31      19          
 * 26        11010       32      1A          
 * 27        11011       33      1B          
 * 28        11100       34      1C          
 * 29        11101       35      1D          
 * ... (213 more lines omitted) ...
 * 243       11110011    363     F3          
 * 244       11110100    364     F4          
 * 245       11110101    365     F5          
 * 246       11110110    366     F6          
 * 247       11110111    367     F7          
 * 248       11111000    370     F8          
 * 249       11111001    371     F9          
 * 250       11111010    372     FA          
 * 251       11111011    373     FB          
 * 252       11111100    374     FC          
 * 253       11111101    375     FD          
 * 254       11111110    376     FE          
 * 255       11111111    377     FF          
 * 256       100000000   400     100         
 */
