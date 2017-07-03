package ru.ekabardinsky.tool.dwninja.dw;

import java.util.Map;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transformer.DataType;

public class FlowVarsProcessor implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		Map payload = eventContext.getMessage().getPayload(Map.class);
		DataType dataType = eventContext.getMessage().getDataType();
		
		String name = ((String) payload.get("name")).replace("flowVars.", "");
		String value = (String) payload.get("value");
		String type = (String) payload.get("type");
		
		dataType.setMimeType(type);

		eventContext.getMessage().setInvocationProperty(name, value, dataType);
		return eventContext.getMessage();
	}

}
