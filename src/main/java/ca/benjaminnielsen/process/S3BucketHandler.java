package ca.benjaminnielsen.process;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.IOException;
import java.io.InputStream;

public class S3BucketHandler {
    private final S3Event event;
    private final AmazonS3 client;

    public S3BucketHandler(S3Event event) {
        this.event = event;
        this.client = AmazonS3ClientBuilder.defaultClient();
    }

    public InputStream getCsvInputStream() throws IOException {
        S3EventNotification.S3EventNotificationRecord record = this.event.getRecords().get(0);
        String scrBucketName = record.getS3().getBucket().getName();
        String srcKey = record.getS3().getObject().getUrlDecodedKey();


        S3Object s3Object;
        try {
            s3Object = client.getObject(new GetObjectRequest(scrBucketName, srcKey));
        } catch (AmazonClientException ace) {
            throw new IOException("Unable to retrieve object from S3: " + ace, ace);
        }

        return s3Object.getObjectContent();
    }


}
