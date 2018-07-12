package com.example.ines.domain.interactor.circulo;

import com.example.ines.domain.CirculoRepository;
import com.example.ines.domain.entities.Circulo;
import com.example.ines.domain.executor.PostExecutionThread;
import com.example.ines.domain.executor.ThreadExecutor;
import com.example.ines.domain.interactor.BaseUseCase;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.Interactor;

import java.util.Map;

public class GetCirculoInteractor extends BaseUseCase<Circulo> implements Interactor<Map<String, String>, Circulo> {

    private GetCirculoCallback callback;
    private Map<String , String> name;

    public interface GetCirculoCallback extends DefaultCallback<Circulo>{}

    private final CirculoRepository repository;
    private final ThreadExecutor executor;

    @Inject
    public GetCirculoInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, CirculoRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.GetCirculo(name.get("date"), name.get("name"), callback);
    }

    @Override
    public <R extends DefaultCallback<Circulo>> void execute(Map<String, String> input, R defaultCallback) {
        this.callback = ((GetCirculoCallback) callback);
        this.name = input;
        executor.execute(this);
    }
}
