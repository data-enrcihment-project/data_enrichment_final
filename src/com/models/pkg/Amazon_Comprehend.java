package com.models.pkg;

public class Amazon_Comprehend {

	public static void ComprehendCall()
	{
		/* AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
		 
	        AmazonComprehend comprehendClient =
	            AmazonComprehendClientBuilder.standard()
	                                         .withCredentials(awsCreds)
	                                         .withRegion("region")
	                                         .build();
	                                         
	        final String inputS3Uri = "s3://input bucket/input path";
	        final InputFormat inputDocFormat = InputFormat.ONE_DOC_PER_FILE;
	        final String outputS3Uri = "s3://output bucket/output path";
	        final String dataAccessRoleArn = "arn:aws:iam::account ID:role/data access role";
	        final int numberOfTopics = 10;
	 
	        final StartTopicsDetectionJobRequest startTopicsDetectionJobRequest = new StartTopicsDetectionJobRequest()
	                .withInputDataConfig(new InputDataConfig()
	                        .withS3Uri(inputS3Uri)
	                        .withInputFormat(inputDocFormat))
	                .withOutputDataConfig(new OutputDataConfig()
	                        .withS3Uri(outputS3Uri))
	                .withDataAccessRoleArn(dataAccessRoleArn)
	                .withNumberOfTopics(numberOfTopics);
	 
	        final StartTopicsDetectionJobResult startTopicsDetectionJobResult = comprehendClient.startTopicsDetectionJob(startTopicsDetectionJobRequest);
	 
	        final String jobId = startTopicsDetectionJobResult.getJobId();
	        System.out.println("JobId: " + jobId);
	 
	        final DescribeTopicsDetectionJobRequest describeTopicsDetectionJobRequest = new DescribeTopicsDetectionJobRequest()
	                .withJobId(jobId);
	 
	        final DescribeTopicsDetectionJobResult describeTopicsDetectionJobResult = comprehendClient.describeTopicsDetectionJob(describeTopicsDetectionJobRequest);
	        System.out.println("describeTopicsDetectionJobResult: " + describeTopicsDetectionJobResult);
	 
	        ListTopicsDetectionJobsResult listTopicsDetectionJobsResult = comprehendClient.listTopicsDetectionJobs(new ListTopicsDetectionJobsRequest());
	        System.out.println("listTopicsDetectionJobsResult: " + listTopicsDetectionJobsResult);*/
	}
	
}
