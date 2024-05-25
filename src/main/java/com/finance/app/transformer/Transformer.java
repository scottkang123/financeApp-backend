package com.finance.app.transformer;

import com.finance.app.serializable.DTO;
import com.finance.app.serializable.Model;

public interface Transformer<M extends Model, D extends DTO> {
    M transformDtoM(D dto);
    D transformMtoD(M model);
}
