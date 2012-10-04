package com.hts.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import com.hts.entities.Hotel;

public class NetworkServiceImpl {
	/**
	 * Supposed to scan all IP in local network to simplify system configuraton.
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static byte[] getAllIPsInLanNetwork() throws UnknownHostException,
			SocketException {
//TODO: some nonsense here. Need to rewrite from scratch.
		Enumeration<NetworkInterface> iFaces = NetworkInterface
				.getNetworkInterfaces();
		while (iFaces.hasMoreElements()) {

			NetworkInterface iIface = iFaces.nextElement();
			Enumeration<InetAddress> inetAdds = iIface.getInetAddresses();
			while (inetAdds.hasMoreElements()) {
				InetAddress address =inetAdds.nextElement(); 
				byte[] ip = address.getAddress();
				System.out.print(address.getHostAddress()+"\n");
				for (int i = 1; i <= 254; i++) {
					ip[3] = (byte) i;
					try {
						InetAddress addr = InetAddress.getByAddress(ip);
						try {

							if (addr.isReachable(100))
								System.out.print(addr.getHostAddress()
										+ " is valid\n");
//							else
//								System.out.print(addr.getHostAddress()
//										+ " wrong address\n");
						} catch (IOException e) {
							e.printStackTrace();
						}

					} catch (UnknownHostException e) {
						e.printStackTrace();
					}

				}

			}

		}

		return null;
	}
}
