package com.fr.adaming.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mohamed
 *
 */
@Getter
@Setter
public class PrestationDateDTO {

	@ApiModelProperty(example = "dd/mm/yyyy")
	private String debutPresta;
	@ApiModelProperty(example = "dd/mm/yyyy")
	private String finPresta;
}
