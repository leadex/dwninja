package ru.ekabardinsky.tool.dwninja.dw;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class RequestProcessor implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();

		String payload = message.getInvocationProperty("payload");
		String expresion = message.getInvocationProperty("expresion");
		String inputType = message.getInvocationProperty("inputType");

		String[] split = expresion.split("\n");

		for (String statement : split) {
			if(statement.contains("output")) {
				String[] output = statement.replaceAll("%output", "").trim().split(" ");
				message.setInvocationProperty("outputType", output[0]);
			}
		}

		message.setPayload(payload);
		message.getDataType().setMimeType(inputType);

		return message;
	}
}
