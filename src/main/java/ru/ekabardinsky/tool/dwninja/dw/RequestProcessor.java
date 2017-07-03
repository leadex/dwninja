package ru.ekabardinsky.tool.dwninja.dw;

import java.util.Map;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class RequestProcessor implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();

		Map payload = message.getInvocationProperty("payload");
		String expression = message.getInvocationProperty("expression");
		String inputType = (String) payload.get("type");

		String[] split = expression.split("\n");

		for (String statement : split) {
			if(statement.contains("output")) {
				String[] output = statement.replaceAll("%output", "").trim().split(" ");
				message.setInvocationProperty("outputType", output[0]);
			}
		}

		message.setPayload(payload.get("value"));
		message.getDataType().setMimeType(inputType);

		return message;
	}
}
