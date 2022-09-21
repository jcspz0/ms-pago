package com.diplo.sharedkernel.amqp;

import com.diplo.sharedkernel.amqp.masstransit.HostInfo;
import java.util.List;
import java.util.Map;

public class MasstransitEvent {

	private String MessageId;
	private String CorrelationId;
	private String ConversationId;
	private String InitiatorId;
	private String RequestId;
	private String SourceAddress;
	private String DestinationAddress;
	private String ResponseAddress;
	private String FaultAddress;
	private String ExpirationTime;
	private String SentTime;
	private Map<String, Object> Headers;
	private List<String> messageType;
	private Map<String, Object> message;
	private HostInfo Host;

	public MasstransitEvent() {
		super();
	}

	public List<String> getMessageType() {
		return messageType;
	}

	public void setMessageType(List<String> messageType) {
		this.messageType = messageType;
	}

	public Map<String, Object> getMessage() {
		return message;
	}

	public void setMessage(Map<String, Object> message) {
		this.message = message;
	}

	public MasstransitEvent(
		String messageId,
		String correlationId,
		String conversationId,
		String initiatorId,
		String requestId,
		String sourceAddress,
		String destinationAddress,
		String responseAddress,
		String faultAddress,
		String expirationTime,
		String sentTime,
		Map<String, Object> headers,
		List<String> messageType,
		Map<String, Object> message,
		HostInfo host
	) {
		super();
		MessageId = messageId;
		CorrelationId = correlationId;
		ConversationId = conversationId;
		InitiatorId = initiatorId;
		RequestId = requestId;
		SourceAddress = sourceAddress;
		DestinationAddress = destinationAddress;
		ResponseAddress = responseAddress;
		FaultAddress = faultAddress;
		ExpirationTime = expirationTime;
		SentTime = sentTime;
		Headers = headers;
		this.messageType = messageType;
		this.message = message;
		Host = host;
	}

	public String getMessageId() {
		return MessageId;
	}

	public void setMessageId(String messageId) {
		MessageId = messageId;
	}

	public String getCorrelationId() {
		return CorrelationId;
	}

	public void setCorrelationId(String correlationId) {
		CorrelationId = correlationId;
	}

	public String getConversationId() {
		return ConversationId;
	}

	public void setConversationId(String conversationId) {
		ConversationId = conversationId;
	}

	public String getInitiatorId() {
		return InitiatorId;
	}

	public void setInitiatorId(String initiatorId) {
		InitiatorId = initiatorId;
	}

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}

	public String getSourceAddress() {
		return SourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		SourceAddress = sourceAddress;
	}

	public String getDestinationAddress() {
		return DestinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		DestinationAddress = destinationAddress;
	}

	public String getResponseAddress() {
		return ResponseAddress;
	}

	public void setResponseAddress(String responseAddress) {
		ResponseAddress = responseAddress;
	}

	public String getFaultAddress() {
		return FaultAddress;
	}

	public void setFaultAddress(String faultAddress) {
		FaultAddress = faultAddress;
	}

	public String getExpirationTime() {
		return ExpirationTime;
	}

	public void setExpirationTime(String expirationTime) {
		ExpirationTime = expirationTime;
	}

	public String getSentTime() {
		return SentTime;
	}

	public void setSentTime(String sentTime) {
		SentTime = sentTime;
	}

	public Map<String, Object> getHeaders() {
		return Headers;
	}

	public void setHeaders(Map<String, Object> headers) {
		Headers = headers;
	}

	public HostInfo getHost() {
		return Host;
	}

	public void setHost(HostInfo host) {
		Host = host;
	}
}
