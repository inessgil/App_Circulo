package com.example.ines.domain.interactor.circulo;


import com.example.ines.domain.entities.Circulo;
import com.example.ines.domain.executor.PostExecutionThread;
import com.example.ines.domain.interactor.BaseUseCase;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.Interactor;

public class GetCirculoInteractor extends BaseUseCase<Circulo> implements Interactor<Integer, Circulo> {

    private GetCirculoCallback callback;

    public interface GetCirculoCallback extends DefaultCallback<Circulo>{}

    public GetCirculoInteractor(PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
    }

    @Override
    public void run() {

    }

    @Override
    public <R extends DefaultCallback<Circulo>> void execute(Integer input, R defaultCallback) {

    }
}
