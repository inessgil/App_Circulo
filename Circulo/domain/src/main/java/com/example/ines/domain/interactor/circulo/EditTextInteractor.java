package com.example.ines.domain.interactor.circulo;

import com.example.ines.domain.CirculoRepository;
import com.example.ines.domain.exception.ErrorBundle;
import com.example.ines.domain.executor.PostExecutionThread;
import com.example.ines.domain.executor.ThreadExecutor;
import com.example.ines.domain.interactor.BaseUseCase;
import com.example.ines.domain.interactor.DefaultCallback;
import com.example.ines.domain.interactor.Interactor;

import java.util.Map;

import javax.inject.Inject;

public class EditTextInteractor extends BaseUseCase<Void> implements Interactor<Map<String, String>, Void> {

    private Map<String, String> input;

    public interface EditTextCallback extends DefaultCallback<Void> {}

    CirculoRepository.EditTextCallback dataCallback = new CirculoRepository.EditTextCallback() {
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
    private EditTextCallback callback;

    @Inject
    public EditTextInteractor(PostExecutionThread postExecutionThread, CirculoRepository repository, ThreadExecutor executor) {
        super(postExecutionThread);
        this.repository = repository;
        this.executor = executor;
    }

    @Override
    public void run() {
        repository.editText(input.get("date"), input.get("name"), input.get("topic"), input.get("content"), dataCallback);
    }

    @Override
    public <R extends DefaultCallback<Void>> void execute(Map<String, String> input, R defaultCallback) {
        this.callback = ((EditTextCallback) defaultCallback);
        this.input = input;
        executor.execute(this);
    }
}
