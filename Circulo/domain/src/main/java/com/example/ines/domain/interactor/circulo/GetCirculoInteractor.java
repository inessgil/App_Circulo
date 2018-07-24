package com.example.ines.domain.interactor.circulo;

import com.example.ines.domain.CirculoRepository;
import com.example.ines.domain.entities.Circulo;
import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.executor.PostExecutionThread;
import com.example.ines.domain.executor.ThreadExecutor;
import com.example.ines.domain.interactor.BaseUseCase;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.Interactor;

import java.util.Map;
import javax.inject.Inject;

public class GetCirculoInteractor extends BaseUseCase<Circulo> implements Interactor<Map<String, String>, Circulo> {

    private Map<String , String> name;

    public interface GetCirculoCallback extends DefaultCallback<Circulo>{}

    CirculoRepository.GetCirculoCallback dataCallback = new CirculoRepository.GetCirculoCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Circulo returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final CirculoRepository repository;
    private final ThreadExecutor executor;
    private GetCirculoCallback callback;

    @Inject
    public GetCirculoInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, CirculoRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.getCirculo(name.get("date"), name.get("name"), dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Circulo>> void execute(Map<String, String> input, R defaultCallback) {
        this.callback = ((GetCirculoCallback) defaultCallback);
        this.name = input;
        executor.execute(this);
    }
}
