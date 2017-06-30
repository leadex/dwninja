package ru.ekabardinsky.tool.dwninja.dw;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class ResponseProcessor implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();

		String outputType = message.getInvocationProperty("outputType");
		message.getDataType().setMimeType(outputType);

		return message;
	}

}
