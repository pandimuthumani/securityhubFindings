package com.securityhub.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.securityhub.AWSSecurityHub;
import com.amazonaws.services.securityhub.AWSSecurityHubClientBuilder;
import com.amazonaws.services.securityhub.model.AwsSecurityFinding;
import com.amazonaws.services.securityhub.model.GetFindingsRequest;
import com.amazonaws.services.securityhub.model.GetFindingsResult;
import com.securityhub.demo.dto.GetSecurityFindingDto;

@Service
public class GetFindingService {

	@Autowired
	EmployeeService employeeService;

	public void getFindingService() {
		String nextToken = null;
		do {
			String accessKey = "AKIA6CLDJWRYFE4Z5HHO";
			String secretKey = "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";

			BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

			// Create an AWS Security Hub client
			AWSSecurityHub securityHub = AWSSecurityHubClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion("ap-southeast-1") // Replace
																													// with
																													// your
																													// preferred
																													// region
					.build();

			// Create a request to get the latest SecurityHub findings
			GetFindingsRequest request = new GetFindingsRequest().withMaxResults(100); // limit to 10 findings
			// .withSortCriteria("CreatedAt", "DESCENDING"); // sort by most recent

			// Get the findings
			GetFindingsResult result = securityHub.getFindings(request);
			nextToken = result.getNextToken();

			List<GetSecurityFindingDto> dtos = new ArrayList<>();

			// Process the findings
			for (AwsSecurityFinding finding : result.getFindings()) {

				GetSecurityFindingDto dt = new GetSecurityFindingDto();
				dt.setTitle(finding.getTitle());
				dt.setDescription(finding.getDescription());
				dt.setSeverity(finding.getSeverity().getLabel());
				dt.setFinding_Id(finding.getId());
				dt.setAccount_Id(finding.getAwsAccountId());
				dtos.add(dt);
			}

			try {
				employeeService.createAwsFindings(dtos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (nextToken != null);

	}

}
