package com.loit.apps.project.palace.socket;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loit.apps.project.palace.socket.protocal.BatProtocalCodecFactory;
import com.loit.core.commom.SysConfig;

public class Server extends Thread {

	private static Logger logger = LoggerFactory.getLogger(Server.class);
	private static int PORT = Integer.parseInt(SysConfig.pros.getProperty("palace.bat.port", "23"));
	public static IoAcceptor acceptor = null;

	public void run() {
		try {
			Server.acceptor = new NioSocketAcceptor();
			Server.acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new BatProtocalCodecFactory(Charset.forName("UTF-8"))));
			Server.acceptor.getSessionConfig().setReadBufferSize(2048);
			Server.acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30);
			Server.acceptor.setHandler(new ServerHandler());
			Server.acceptor.bind(new InetSocketAddress(PORT));
			logger.info("服务端启动成功...     端口号为：" + PORT);
		} catch (Exception e) {
			logger.error("服务端启动异常...." + new Date().toLocaleString());
			e.printStackTrace();
			Server.acceptor.dispose(true);
		}
	}

	public static void main(String[] args) {
		new Server().start();
	}

}
