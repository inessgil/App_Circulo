package com.example.ines.domain.interactor.circulo;

import com.example.ines.domain.CirculoRepository;
import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.executor.PostExecutionThread;
import com.example.ines.domain.executor.ThreadExecutor;
import com.example.ines.domain.interactor.BaseUseCase;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.Interactor;

import java.util.Map;

public class RemoveCirculoInteractor extends BaseUseCase<Void> implements Interactor<Map<String, String>, Void> {

    private RemoveCirculoCallback callback;
    private Map<String, String> input;

    public interface RemoveCirculoCallback extends DefaultCallback<Void> {}

    CirculoRepository.RemoveCirculoCallback dataCallback = new CirculoRepository.RemoveCirculoCallback() {
        @Override
        public void onError(ErrorBundle errorBundle) {
            notifyOnError(errorBundle, callback);
        }

        @Override
        public void onSuccess(Void returnParam) {
            notifyOnSuccess(returnParam, callback);
        }
    };

    private final CirculoRepository repository;
    private final ThreadExecutor executor;

    public RemoveCirculoInteractor(PostExecutionThread postExecutionThread, CirculoRepository repository, ThreadExecutor executor) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.removeCirculo(input.get("date"), input.get("name"), dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Map<String, String> input, R defaultCallback) {
        this.callback = (RemoveCirculoCallback) defaultCallback;
        this.input = input;
    }
}
