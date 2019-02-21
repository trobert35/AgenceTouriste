package com.fr.adaming.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Thomas R
 *
 */
@Getter
@Setter
public class BookingDTO {

	private String prenomUser;
	private String nomUser;

	@ApiModelProperty(example = "dd/mm/yyyy")
	private String debutPresta;
	@ApiModelProperty(example = "dd/mm/yyyy")
	private String finPresta;
}
