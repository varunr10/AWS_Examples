package com.snsexample;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishResult;

public class SNSPublishMessage {

	public static void main(String args[]) {
		
		AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();
		
		/* 	Publish a message to SNS topic		*/
		String message = "SNS Test Message. Say Hello";
		String topicARN = "arn:aws:sns:us-east-1:530366236005:MySNSMessageTopic";
		
		PublishResult publishResult = snsClient.publish(topicARN, message, "Welcome to SNS");
		System.out.println(publishResult);
		
	}
}
