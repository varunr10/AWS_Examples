package com.sqsexample;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

/* 		ReceiveMessageWaitTimeSeconds		*/
public class SQSLongPolling {

	public static void main(String args[]) {
		
		AmazonSQS sqsClient = AmazonSQSClientBuilder.defaultClient();
		
		/* Set LongPolling attribute while creating queue 	*/
		CreateQueueRequest createQRequest = new CreateQueueRequest()
				.withQueueName("Autodownload_Queue")
				.addAttributesEntry("ReceiveMessageWaitTimeSeconds","20");
		
		try {
			sqsClient.createQueue(createQRequest);
		}
		catch(AmazonSQSException e) {
			System.out.println(e.getErrorMessage());
		}
		
		
		/*	Set LongPolling attribute to already created Queue	*/
		SetQueueAttributesRequest set_attrs_request = new SetQueueAttributesRequest()
		        .withQueueUrl("Autodownload_Queue")
		        .addAttributesEntry("ReceiveMessageWaitTimeSeconds", "10");
		sqsClient.setQueueAttributes(set_attrs_request);
	
		
		/* Set LongPolling for a ReceiveMessageRequest		*/
		ReceiveMessageRequest receive_request = new ReceiveMessageRequest()
		        .withQueueUrl("Autodownload_Queue")
		        .withWaitTimeSeconds(15);
		ReceiveMessageResult msg =sqsClient.receiveMessage(receive_request);
		System.out.println(msg.getMessages().get(0).getBody());
	}
}
