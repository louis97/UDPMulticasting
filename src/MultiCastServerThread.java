import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;

public class MultiCastServerThread extends ServerThread {

	public MultiCastServerThread() throws IOException {
		super("MultiCastServerThread");
	}

	public void run() {
		while (masLineas) {
			try {
				byte[] buf = new byte[256];
				//Do not wait for request, sent a quote.
				// construct quote
				String dString = null;
				if (in == null)
					dString = new Date().toString();
				else
					dString = getNextLine();
				buf = dString.getBytes();

				// send it
				InetAddress group = InetAddress.getByName("230.0.0.1");
				DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
				socket.send(packet);

			} catch (IOException e) {
				e.printStackTrace();
				masLineas = false;
			}
		}
		socket.close();
	}

}

