// Exercise 5.25 (Prime Numbers)
public class Exercise5_25 {
    private static long divisionTestsHalf = 0;   // trial divisions using the n/2 limit
    private static long divisionTestsSqrt = 0;   // trial divisions using the square-root limit

    public static void main(String[] args) {
        final int LIMIT = 10000;

        // b) all primes less than 10,000, testing divisors up to n/2
        System.out.println("Prime numbers less than " + LIMIT + ":");
        long start = System.nanoTime();
        int primeCount = 0;
        int numbersTested = 0;
        for (int n = 2; n < LIMIT; n++) {
            numbersTested++;
            if (isPrime(n)) {
                primeCount++;
                System.out.printf("%6d", n);
                if (primeCount % 10 == 0)
                    System.out.println();
            }
        }
        long halfTime = System.nanoTime() - start;
        System.out.printf("%n%nNumbers tested (2 through %d): %d%n", LIMIT - 1, numbersTested);
        System.out.printf("Primes found: %d%n", primeCount);

        // c) the same program using the square root of n as the upper limit
        start = System.nanoTime();
        int primeCountSqrt = 0;
        for (int n = 2; n < LIMIT; n++) {
            if (isPrimeSqrt(n))
                primeCountSqrt++;
        }
        long sqrtTime = System.nanoTime() - start;

        System.out.printf("%nUsing n/2 as the limit:      %d primes, %d trial divisions, %.2f ms%n",
                primeCount, divisionTestsHalf, halfTime / 1_000_000.0);
        System.out.printf("Using sqrt(n) as the limit:  %d primes, %d trial divisions, %.2f ms%n",
                primeCountSqrt, divisionTestsSqrt, sqrtTime / 1_000_000.0);
    }

    // a) determines whether n is prime (divisors tested up to n / 2)
    public static boolean isPrime(int n) {
        if (n < 2)
            return false;
        for (int i = 2; i <= n / 2; i++) {
            divisionTestsHalf++;
            if (n % i == 0)
                return false;
        }
        return true;
    }

    // c) determines whether n is prime (divisors tested only up to sqrt(n))
    public static boolean isPrimeSqrt(int n) {
        if (n < 2)
            return false;
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            divisionTestsSqrt++;
            if (n % i == 0)
                return false;
        }
        return true;
    }
}

/*
 * SAMPLE RUN (the actual output below was produced by running this program;
 * random programs give different results each time)
 * ----------------------------------------------------------------
 * Prime numbers less than 10000:
 *      2     3     5     7    11    13    17    19    23    29
 *     31    37    41    43    47    53    59    61    67    71
 *     73    79    83    89    97   101   103   107   109   113
 *    127   131   137   139   149   151   157   163   167   173
 *    179   181   191   193   197   199   211   223   227   229
 *    233   239   241   251   257   263   269   271   277   281
 *    283   293   307   311   313   317   331   337   347   349
 *    353   359   367   373   379   383   389   397   401   409
 *    419   421   431   433   439   443   449   457   461   463
 *    467   479   487   491   499   503   509   521   523   541
 *    547   557   563   569   571   577   587   593   599   601
 *    607   613   617   619   631   641   643   647   653   659
 *    661   673   677   683   691   701   709   719   727   733
 *    739   743   751   757   761   769   773   787   797   809
 *    811   821   823   827   829   839   853   857   859   863
 *    877   881   883   887   907   911   919   929   937   941
 *    947   953   967   971   977   983   991   997  1009  1013
 *   1019  1021  1031  1033  1039  1049  1051  1061  1063  1069
 *   1087  1091  1093  1097  1103  1109  1117  1123  1129  1151
 *   1153  1163  1171  1181  1187  1193  1201  1213  1217  1223
 *   1229  1231  1237  1249  1259  1277  1279  1283  1289  1291
 *   1297  1301  1303  1307  1319  1321  1327  1361  1367  1373
 *   1381  1399  1409  1423  1427  1429  1433  1439  1447  1451
 *   1453  1459  1471  1481  1483  1487  1489  1493  1499  1511
 *   1523  1531  1543  1549  1553  1559  1567  1571  1579  1583
 *   1597  1601  1607  1609  1613  1619  1621  1627  1637  1657
 *   1663  1667  1669  1693  1697  1699  1709  1721  1723  1733
 *   1741  1747  1753  1759  1777  1783  1787  1789  1801  1811
 *   1823  1831  1847  1861  1867  1871  1873  1877  1879  1889
 * ... (86 more lines omitted) ...
 *   9293  9311  9319  9323  9337  9341  9343  9349  9371  9377
 *   9391  9397  9403  9413  9419  9421  9431  9433  9437  9439
 *   9461  9463  9467  9473  9479  9491  9497  9511  9521  9533
 *   9539  9547  9551  9587  9601  9613  9619  9623  9629  9631
 *   9643  9649  9661  9677  9679  9689  9697  9719  9721  9733
 *   9739  9743  9749  9767  9769  9781  9787  9791  9803  9811
 *   9817  9829  9833  9839  9851  9857  9859  9871  9883  9887
 *   9901  9907  9923  9929  9931  9941  9949  9967  9973
 *
 * Numbers tested (2 through 9999): 9998
 * Primes found: 1229
 *
 * Using n/2 as the limit:      1229 primes, 2907639 trial divisions, 198.58 ms
 * Using sqrt(n) as the limit:  1229 primes, 117526 trial divisions, 6.00 ms
 */
