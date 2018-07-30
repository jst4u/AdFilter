package com.loit.apps.project.palace.socket.protocal;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class BatProtocalCodecFactory implements ProtocolCodecFactory {
	private final BatProtocalEncoder encoder;
	private final BatProtocalDecoder decoder;

	public BatProtocalCodecFactory(Charset charset) {
		encoder = new BatProtocalEncoder(charset);
		decoder = new BatProtocalDecoder(charset);
	}

	public ProtocolEncoder getEncoder(IoSession session) {
		return encoder;
	}

	public ProtocolDecoder getDecoder(IoSession session) {
		return decoder;
	}

}