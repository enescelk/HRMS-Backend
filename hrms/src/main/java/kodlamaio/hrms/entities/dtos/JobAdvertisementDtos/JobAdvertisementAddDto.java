package kodlamaio.hrms.entities.dtos.JobAdvertisementDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementAddDto {
	
	private int employerId;
	private int jobPositionId;
	private int cityId;
	private int workTimeId;
	private int workTypeId;
	private String jobDescription;
	private int maxSalary;
	private int minSalary;
	private int numberOfOpenPosition;
	private LocalDate applicationDeadline;
}