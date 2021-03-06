
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	public static void main(String[] args) throws IOException {



		// get a datagram socket
		DatagramSocket socket = new DatagramSocket();

		// send request
		byte[] buf = new byte[256];
		InetAddress address = InetAddress.getByName("localhost");
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		socket.send(packet);

		// get response
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);

		// display response
		String received = new String(packet.getData(), 0, packet.getLength());
		System.out.println(received);

		socket.close();
	}
}