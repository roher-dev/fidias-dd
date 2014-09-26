package com.fidias.database.modeler.transformer;

import java.util.List;

import com.fidias.database.modeler.api.dto.BasicDto;
import com.fidias.database.modeler.model.Model;
import com.google.common.collect.Lists;

public abstract class AbstractDtoModelTransformer<D extends BasicDto, E extends Model>
    implements DtoModelTransformer<D, E> {

    @Override
    public final E transform(D dto) {
        if (dto == null) {
            return null;
        }
        return this.doTransform(dto);
    }

    @Override
    public D transform(E e) {
        return this.transform(e, Integer.MAX_VALUE);
    }

    @Override
    public D transform(E e, int depth) {
        if (e == null) {
            return null;
        }
        return this.doTransform(e, depth);
    }

    protected abstract E doTransform(D dto);

    protected abstract D doTransform(E e, int depth);


    @Override
    public final List<E> transformAllFrom(List<D> dtos) {
        if (dtos == null) {
            return null;
        }
        List<E> result = Lists.newArrayList();
        
        for(D d : dtos){
        	result.add(this.doTransform(d));
        }
        		
        return result;
    }

    @Override
    public final List<D> transformAllTo(List<E> entities) {
        return this.transformAllTo(entities, Integer.MAX_VALUE);
    }

    @Override
    public final List<D> transformAllTo(List<E> entities, int depth) {
        if (entities == null) {
            return null;
        }
        List<D> result = Lists.newArrayList();
        
        for(E e : entities){
        	result.add(this.doTransform(e, depth));
        }

        return result;
    }

}
