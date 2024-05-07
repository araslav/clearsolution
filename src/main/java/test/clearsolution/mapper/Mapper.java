package test.clearsolution.mapper;

public interface Mapper<C, M, D> {
    M toModel(C dto);

    D toDto(M model);
}
