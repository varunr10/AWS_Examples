  package com.awslambda.LambdaUsingJava;

/* 
 * 
Sample Class to perform SQS Queue modification operations like
CreateQueue, DeleteQueue, ListQueueUrls, ListQueueUrls using Prefixes and QueueName
*
*/
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.ListQueuesRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;

public class SQSOperations1
{
	private static final String QUEUE_NAME = "FBO_Download_Queue";
	
	public static void main(String args[]) {
		
		/*	Create a SQS Queue by providing Queue Name and attributes	*/
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		CreateQueueRequest create_request = new CreateQueueRequest(QUEUE_NAME)
		        //.addAttributesEntry("DelaySeconds", "60")
		        .addAttributesEntry("MessageRetentionPeriod", "86400");
		try {
		    sqs.createQueue(create_request);
		    System.out.println("Successfully created the SQS Queue"+QUEUE_NAME);
		} catch (AmazonSQSException e) {
		    if (!e.getErrorCode().equals("QueueAlreadyExists")) {
		        System.out.println("Failed sar lel"+e.getErrorMessage());
		    }
		}
		
		/*	List the QueueNames in my profile	*/
		ListQueuesResult lq_result = sqs.listQueues();
		for (String url : lq_result.getQueueUrls()) {
		    System.out.println("Queue url is --> "+url);
		}
		
		/*	List Queue Urls with a Prefix	*/
		String name_prefix = "Chumma";
		lq_result = sqs.listQueues(new ListQueuesRequest(name_prefix));
		for (String url : lq_result.getQueueUrls()) {
		    System.out.println("Queue url with Chumma prefix is --> "+url);
		}
		
		/*	Get Queue URL of a queue	*/
		String queueUrl = sqs.getQueueUrl("ChummaOruQueue").getQueueUrl();
		System.out.println("Url of the queue is "+queueUrl);
		
		/*	Delete a Queue using Queue Url	*/
		sqs.deleteQueue(queueUrl);
		
	}
}
