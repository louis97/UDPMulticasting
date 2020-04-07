import java.io.*;
import java.net.*;
import java.util.*;

public class MultiCastClient {
	static boolean booleano=true;

	public static void main(String[] args) throws IOException {

		MulticastSocket socket = new MulticastSocket(4446);
		InetAddress address = InetAddress.getByName("230.0.0.1");
		socket.joinGroup(address);

		DatagramPacket packet;

		int cuenta=0;
		// every Quote
		while (booleano) {
			try {
				byte[] buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);

				String received = new String(packet.getData(), 0, packet.getLength());
				cuenta++;
				System.out.println(cuenta+" Quote of the Moment: " + received);
			} catch (IOException e) {
				e.printStackTrace();
				booleano = false;
				System.out.println("Se terminó de leer todo el archivo por el cliente");
			}
		}

		socket.leaveGroup(address);
		socket.close();
	}
}
