package com.lambdaexample;

/* Handler in lambda syntax -->  packageName.className::methodName  */


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/* Class must implement RequestHandler interface and mention type of Input, Output. Object & Object in this case */
public class LambdaExample implements RequestHandler<Object,Object>
{
  /* Handler method for the Lambda Function */
	public Object handleRequest(Object input, Context context) {
  
		System.out.println("Welcome to Sample Lambda function using Java");
		return null;
	}
}
