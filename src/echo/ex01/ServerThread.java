package echo.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {

			// 메세지 받기용 스트림
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// 메세지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			while (true) {
				// 메세지 받기
				String msg = br.readLine();

				if (msg == null) {
					System.out.println("클라이언트 접속 종료");
					break;
				}

				System.out.println("받은메세지:" + msg);
				// 메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}

		} catch (IOException e) {
			System.out.println("에러발생");
		}

	}

}
