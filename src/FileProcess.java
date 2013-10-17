import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileProcess {

	private final static byte[] KEY = { (byte)0x01, (byte)0x23, (byte)0x45,(byte)0x67,
            (byte)0x89, (byte)0x01, (byte)0x23, (byte)0x45 };
	
	private final static int KEYLEN = 8;
	private final static int MAGICLEN = 8;
	
	public static void encryptStream(FileInputStream in, FileOutputStream out)
			throws IOException {
		
		for (int i = 0; i < MAGICLEN; i++) {
			out.write((int)Math.random()*10);
		}
		
		byte[] buf1 = new byte[KEYLEN];
		in.read(buf1);
		out.write(encryptByte(buf1));

		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		out.close();
	}

	public static void decryptStream(FileInputStream in, FileOutputStream out) throws IOException {
		in.skip(MAGICLEN);
		
		byte[] buf1 = new byte[KEYLEN];
		in.read(buf1);
		out.write(decryptByte(buf1));

		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		out.close();
	}

	public static byte[] encryptByte(byte[] inbyte) {
		byte[] outbyte = inbyte;
		for (int i = 0; i < KEYLEN; i++) {
			 outbyte[i] = (byte) (outbyte[i]^KEY[i]);
		}
		return inbyte;
	}
	
	public static byte[] decryptByte(byte[] inbyte) {
		byte[] outbyte = inbyte;
		for (int i = 0; i < KEYLEN; i++) {
			 outbyte[i] = (byte) (outbyte[i]^KEY[i]);
		}
		return inbyte;
	}

}
