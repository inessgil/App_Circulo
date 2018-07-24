package com.example.ines.domain.interactor.circulo;

import com.example.ines.domain.CirculoRepository;
import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.executor.PostExecutionThread;
import com.example.ines.domain.executor.ThreadExecutor;
import com.example.ines.domain.interactor.BaseUseCase;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.Interactor;

import java.util.Map;

public class RemoveImageInteractor extends BaseUseCase<Void> implements Interactor<Map<String, String>, Void> {

    private Map<String, String> input;

    public interface RemoveImageCallback extends DefaultCallback<Void> {}

    CirculoRepository.RemoveImageCallback dataCallback = new CirculoRepository.RemoveImageCallback() {
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
    private RemoveImageCallback callback;

    public RemoveImageInteractor(PostExecutionThread postExecutionThread, CirculoRepository repository, ThreadExecutor executor) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.removeImage(input.get("date"), input.get("name"), input.get("topic"), input.get("content"), dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Map<String, String> input, R defaultCallback) {
        this.callback = (RemoveImageCallback) defaultCallback;
        this.input = input;
    }
}
