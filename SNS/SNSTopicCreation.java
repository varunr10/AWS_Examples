package com.snsexample;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class SNSTopicCreation {

	public static void main(String args[]) {
		
		/*		Create SNSClient		*/
		AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();
		
		/* 		Create SNS Topic 	*/
		snsClient.createTopic("MySNSMessageTopic");
		
	}
}
