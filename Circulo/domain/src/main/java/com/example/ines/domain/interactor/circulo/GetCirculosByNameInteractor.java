package com.example.ines.domain.interactor.circulo;

import com.example.ines.domain.CirculoRepository;
import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.executor.PostExecutionThread;
import com.example.ines.domain.executor.ThreadExecutor;
import com.example.ines.domain.interactor.BaseUseCase;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.Interactor;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class GetCirculosByNameInteractor extends BaseUseCase<List<String>> implements Interactor<Map<String, String>, List<String>> {

    private GetCirculosByNameCallback callback;
    private Map<String , String> name;

    public interface GetCirculosByNameCallback extends DefaultCallback<List<String>> {}

    CirculoRepository.GetCirculosByNameCallback dataCallback = new CirculoRepository.GetCirculosByNameCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(List<String> returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final CirculoRepository repository;
    private final ThreadExecutor executor;

    @Inject
    public GetCirculosByNameInteractor(PostExecutionThread postExecutionThread, ThreadExecutor executor, CirculoRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.getCirculosByName(name.get("date"), dataCallback);
    }

    @Override
    public <R extends DefaultCallback<List<String>>> void execute(Map<String, String> input, R defaultCallback) {
        this.callback = ((GetCirculosByNameCallback) callback);
        this.name = input;
        executor.execute(this);
    }
}
