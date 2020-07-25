package com.awslambda.LambdaUsingJava;

import java.util.List;

/* 
 * 
Sample Class to perform SQS Queue send/receive/delete operations like
SendMessage, SendMessageBatch, ReceiveMessage, DeleteMessage
*
*/
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.Message;

public class SQSOperations2
{
	private static final String QUEUE_NAME = "FBO_Download_Queue";
	private static final String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/530366236005/FBO_Download_Queue";
	public static void main(String args[]) {
		
		/*	SQS Client initialization	*/
		AmazonSQS sqsClient = AmazonSQSClientBuilder.defaultClient();
		
		/*	Send Message to a Queue	  */
		SendMessageRequest sendMessageRequest = new SendMessageRequest()
				.withQueueUrl(QUEUE_URL)
				.withMessageBody("Invoice_700389146")
				.withDelaySeconds(20);
		sqsClient.sendMessage(sendMessageRequest);
		
		/* 	Send Message without SendMessageRequest object 	*/
		sqsClient.sendMessage(QUEUE_URL, "Account_700389146");
		
		/*	Send Messages in a batch to a Queue	*/
		
		SendMessageBatchRequest sendMessageBatchRequest = new SendMessageBatchRequest()
				.withQueueUrl(QUEUE_URL)
				.withEntries(new SendMessageBatchRequestEntry("msg1","This is Message1"),
							 new SendMessageBatchRequestEntry("msg2","This is Message2"),
							 new SendMessageBatchRequestEntry("msg3","This is Message3"),
							 new SendMessageBatchRequestEntry("msg4","This is Message4"),
							 new SendMessageBatchRequestEntry("msg5","This is Message5"));
		sqsClient.sendMessageBatch(sendMessageBatchRequest);	
							 
		String receiptHandle = null;
		
		/* 	Receive Messages by passing Queue url	*/
		List<Message> messages = sqsClient.receiveMessage(QUEUE_URL).getMessages();
		for(Message message: messages) {
			System.out.println("Body is " + message.getBody() + "\n" +
								"MD5 of Body is " + message.getMD5OfBody () + "\n" +
								"Message ID is " + message.getMessageId() + "\n" +
								"Receipt handle is " + message.getReceiptHandle());
			receiptHandle = message.getReceiptHandle();
		}
		if(messages == null) {
			System.out.println("No Messages in the Queue");
		}
		
		/* Delete Messages by passing Receipt Handle and Queue Url  */
		//Using receiptHandle from aboce received Message and deleting it
		sqsClient.deleteMessage(QUEUE_URL,receiptHandle);
	}
}
