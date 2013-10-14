import java.io.*;
import javax.swing.*;

public class Main {
	public static void main(String args[]) throws Exception {

		// 源文件，必须存在，路径可选
		File sf = new File("/Users/liuhao/Desktop/test.png");

		// 目的文件，因为要向其中写入，指定文件可以不存在，由程序生成
		File df = new File("/Users/liuhao/Desktop/test2.png");
		
		File df2 = new File("/Users/liuhao/Desktop/test3.png");
		new ReadWriteGra(sf, df, df2);
		new UseGra(df2);
	}

}

class ReadWriteGra {
	FileInputStream in = null;
	FileOutputStream out = null;
	
	FileInputStream in2 = null;
	FileOutputStream out2 = null;

	public ReadWriteGra(File sourceFile, File destFile, File destFile2) throws Exception {
		in = new FileInputStream(sourceFile);
		out = new FileOutputStream(destFile, true);

		FileProcess.encryptStream(in, out);
		in2 = new FileInputStream(destFile);
		out2 = new FileOutputStream(destFile2, true);
		FileProcess.decryptStream(in2, out2);
	}
}

class UseGra extends JFrame {
	public UseGra(File picFile) throws Exception {

		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setBounds(600, 200, 400, 370);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel p1 = (JPanel) this.getContentPane();
		p1.setOpaque(false);
		p1.setLayout(null);
		InputStream is = new FileInputStream(picFile);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int b = 0;
		while ((b = is.read()) != -1) {
			baos.write(b);
		}
		ImageIcon image = new ImageIcon(baos.toByteArray());
		JLabel background = new JLabel(image);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		JButton bt = new JButton("Test_Button");
		p1.add(bt);
		bt.setBounds(10, 10, 150, 25);
		validate();
	}
}