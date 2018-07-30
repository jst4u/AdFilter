package com.loit.apps.project.palace.socket.protocal;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class EquProtocalCodecFactory implements ProtocolCodecFactory {
	private final EquProtocalEncoder encoder;
	private final EquProtocalDecoder decoder;

	public EquProtocalCodecFactory(Charset charset) {
		encoder = new EquProtocalEncoder(charset);
		decoder = new EquProtocalDecoder(charset);
	}

	public ProtocolEncoder getEncoder(IoSession session) {
		return encoder;
	}

	public ProtocolDecoder getDecoder(IoSession session) {
		return decoder;
	}

}