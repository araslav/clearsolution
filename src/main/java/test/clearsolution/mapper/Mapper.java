package test.clearsolution.mapper;

import java.util.Map;

public interface Mapper<C, M, D> {
    M toModel(C dto);

    D toDto(M model);

    C updateByFields(M model, Map<String, Object> fieldMap);
}
