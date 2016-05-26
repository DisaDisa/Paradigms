import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CalcMD5 {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Scanner in = new Scanner(new File(args[0]));
        MessageDigest md = MessageDigest.getInstance("MD5");
        while (in.hasNextLine()) {
            String FileName = in.nextLine();
            InputStream is = new FileInputStream(FileName);
            byte[] a = new byte[1024];
            int k;
            while ((k = is.read(a)) != -1) {
                md.update(a, 0, k);
            }
            System.out.println(DatatypeConverter.printHexBinary(md.digest()));
            md.reset();
        }
    }
}
