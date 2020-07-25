import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

/*   ReDrive Policy   */
public class SQSDeadLetterQueue {

   public static void main(String args[]) {
      //Get DLQ Queue url   
      String dl_queue_url = sqs.getQueueUrl(dl_queue_name)
                               .getQueueUrl();
      // Get ARN of DLQ
      GetQueueAttributesResult queue_attrs = sqs.getQueueAttributes(
              new GetQueueAttributesRequest(dl_queue_url)
                  .withAttributeNames("QueueArn"));
      String dl_queue_arn = queue_attrs.getAttributes().get("QueueArn");

      //Get Main Queue url
      String src_queue_url = sqs.getQueueUrl(src_queue_name)
                                .getQueueUrl();

      // Set dead letter queue with redrive policy on source queue.
      SetQueueAttributesRequest request = new SetQueueAttributesRequest()
              .withQueueUrl(src_queue_url)
              .addAttributesEntry("RedrivePolicy",
                      "{\"maxReceiveCount\":\"5\", \"deadLetterTargetArn\":\""
                      + dl_queue_arn + "\"}");

      sqs.setQueueAttributes(request);
   }
}
