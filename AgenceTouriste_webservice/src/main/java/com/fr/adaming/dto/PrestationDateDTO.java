package com.fr.adaming.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestationDateDTO {

	@ApiModelProperty(example = "dd/mm/yyyy")
	private String debutPresta;
	@ApiModelProperty(example = "dd/mm/yyyy")
	private String finPresta;
}
