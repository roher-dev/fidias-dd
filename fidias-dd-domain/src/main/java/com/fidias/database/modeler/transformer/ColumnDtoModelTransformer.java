package com.fidias.database.modeler.transformer;

import javax.inject.Named;

import com.fidias.database.modeler.api.dto.TableColumnDto;
import com.fidias.database.modeler.model.ColumnTable;

@Named
public class ColumnDtoModelTransformer extends AbstractDtoModelTransformer<TableColumnDto, ColumnTable> {

	@Override
	protected ColumnTable doTransform(TableColumnDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TableColumnDto doTransform(ColumnTable e, int depth) {
		TableColumnDto dto = new TableColumnDto();
		dto.setDescription(e.getDescription());
		dto.setFk(e.isFk());
		dto.setName(e.getName());
		dto.setNullable(e.isNullable());
		dto.setPk(e.isPk());
		dto.setSize(e.getSize());
		dto.setType(e.getType());
		dto.setUnique(e.isUnique());
		
		return dto;
	}

}
