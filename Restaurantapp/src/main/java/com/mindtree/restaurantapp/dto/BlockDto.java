package com.mindtree.restaurantapp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class BlockDto {
	@NotNull(message="block name cannot be empty")
	@Size(min = 3,message = "blockname should be between three and twenty five characters",max = 25)
	private String blockName;

	public BlockDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlockDto(String blockName) {
		super();
		this.blockName = blockName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	
}
