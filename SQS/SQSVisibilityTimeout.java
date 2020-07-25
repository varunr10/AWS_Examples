package com.sqsexample;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

/* ChangeMessageVisibility	*/
public class SQSVisibilityTimeout {

	
	public static void main(String args[]) {
		
		AmazonSQS sqsClient = AmazonSQSClientBuilder.defaultClient();
		
		/* 			Change Message visibility for a message 		*/
		String QUEUE_URL = sqsClient.getQueueUrl("Autodownload_Queue").getQueueUrl();
		// Get the receipt handle for the first message in the queue
		String receipt = sqsClient.receiveMessage(QUEUE_URL)
		                    .getMessages()
		                    .get(0)
		                    .getReceiptHandle();

		sqsClient.changeMessageVisibility(QUEUE_URL, receipt, 40);
		
		//Process the message
				
		//Delete the message
	}
}
