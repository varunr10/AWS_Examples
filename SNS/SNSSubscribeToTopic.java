package com.snsexample;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SNSSubscribeToTopic{

	public static void main(String args[]) {
		
		/*		Create SNSClient		*/
		AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();
		
		/* 	  Create a subscription to Topic  	*/
		String phoneNumber = "+9176588002";
        String topicARN = "arn:aws:sns:us-east-1:530366236005:MySNSMessageTopic";
        
        SubscribeRequest subscribeRequest = new SubscribeRequest(topicARN, "sms", phoneNumber);
        SubscribeResult subscribeResult = snsClient.subscribe(subscribeRequest);
        System.out.println(subscribeResult);
        
	}
}
